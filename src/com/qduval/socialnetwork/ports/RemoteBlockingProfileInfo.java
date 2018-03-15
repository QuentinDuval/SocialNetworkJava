package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.core.IAccessProfileInfo;
import com.qduval.socialnetwork.core.PostSummary;
import com.qduval.socialnetwork.core.ProfileId;
import com.qduval.socialnetwork.core.Topic;

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
    public Future<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        Iterable<ProfileId> friends = cache.friendsOf(profileId);
        if (friends != null)
            return CompletableFuture.completedFuture(friends);

        System.out.println("Query for friends of " + profileId);
        sleep();
        friends = realData.friendsOf(profileId);
        for (ProfileId profile : friends)
            cache.addFriends(profileId, profile);
        return CompletableFuture.completedFuture(friends);
    }

    @Override
    public Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        Set<Topic> topics = cache.favoriteTopicsOf(profileId);
        if (topics != null)
            return CompletableFuture.completedFuture(topics);

        System.out.println("Query for topics of " + profileId);
        sleep();
        topics = realData.favoriteTopicsOf(profileId);
        for (Topic topic : topics)
            cache.addSubjects(profileId, topic);
        return CompletableFuture.completedFuture(topics);
    }

    @Override
    public Future<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        Iterable<PostSummary> posts = cache.lastPostsOf(profileId);
        if (posts != null)
            return CompletableFuture.completedFuture(posts);

        System.out.println("Query for posts of " + profileId);
        sleep();
        posts = realData.lastPostsOf(profileId);
        for (PostSummary post : posts)
            cache.addPosts(profileId, post);
        return CompletableFuture.completedFuture(posts);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
