package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.sync.IAccessProfileInfo;
import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class RemoteBlockingProfileInfo implements IAccessProfileInfo {
    private final ProfileRepository realData;
    private final ProfileRepository cache;

    RemoteBlockingProfileInfo(ProfileRepository realData) {
        this.realData = realData;
        this.cache = new ProfileRepository();
    }

    @Override
    public Iterable<ProfileId> friendsOf(ProfileId profileId) {
        Iterable<ProfileId> friends = cache.friendsOf(profileId);
        if (friends != null)
            return friends;

        System.out.println("Query for friends of " + profileId);
        sleep();
        friends = realData.friendsOf(profileId);
        for (ProfileId profile : friends)
            cache.addFriends(profileId, profile);
        return friends;
    }

    @Override
    public Set<Topic> favoriteTopicsOf(ProfileId profileId) {
        Set<Topic> topics = cache.favoriteTopicsOf(profileId);
        if (topics != null)
            return topics;

        System.out.println("Query for topics of " + profileId);
        sleep();
        topics = realData.favoriteTopicsOf(profileId);
        for (Topic topic : topics)
            cache.addSubjects(profileId, topic);
        return topics;
    }

    @Override
    public Iterable<PostSummary> lastPostsOf(ProfileId profileId) {
        Iterable<PostSummary> posts = cache.lastPostsOf(profileId);
        if (posts != null)
            return posts;

        System.out.println("Query for posts of " + profileId);
        sleep();
        posts = realData.lastPostsOf(profileId);
        for (PostSummary post : posts)
            cache.addPosts(profileId, post);
        return posts;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
