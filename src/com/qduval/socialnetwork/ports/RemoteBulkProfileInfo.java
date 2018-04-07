package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;
import com.qduval.socialnetwork.suggestions.bulk.Fetch;
import com.qduval.socialnetwork.suggestions.bulk.FetchError;
import com.qduval.socialnetwork.suggestions.bulk.IAccessProfileInfoBulk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RemoteBulkProfileInfo implements IAccessProfileInfoBulk {
    private final ProfileRepository realData;

    private final HashMap<ProfileId, ArrayList<ProfileId>> cachedFriends = new HashMap<>();
    private final HashMap<ProfileId, HashSet<Topic>> cachedTopics = new HashMap<>();
    private final HashMap<ProfileId, ArrayList<PostSummary>> cachedLastPosts = new HashMap<>();

    private final HashSet<ProfileId> toFetchFriends = new HashSet<>();
    private final HashSet<ProfileId> toFetchTopics = new HashSet<>();
    private final HashSet<ProfileId> toFetchPosts = new HashSet<>();

    public RemoteBulkProfileInfo(ProfileRepository realData) {
        this.realData = realData;
    }

    // TODO - You cannot easily group parallel computation like this... you need to introduce delays...

    private class FriendToFetch implements Fetch<Iterable<ProfileId>> {
        private final ProfileId profileId;

        public FriendToFetch(ProfileId profileId) {
            this.profileId = profileId;
        }

        @Override
        public Iterable<ProfileId> get() throws FetchError {
            if (!cachedFriends.containsKey(profileId))
                onFetch();
            return cachedFriends.get(profileId);
        }
    }

    private class TopicToFetch implements Fetch<Set<Topic>> {
        private final ProfileId profileId;

        public TopicToFetch(ProfileId profileId) {
            this.profileId = profileId;
        }

        @Override
        public Set<Topic> get() throws FetchError {
            if (!cachedTopics.containsKey(profileId))
                onFetch();
            return cachedTopics.get(profileId);
        }
    }

    private class PostToFetch implements Fetch<Iterable<PostSummary>> {
        private final ProfileId profileId;

        public PostToFetch(ProfileId profileId) {
            this.profileId = profileId;
        }

        @Override
        public Iterable<PostSummary> get() throws FetchError {
            if (!cachedLastPosts.containsKey(profileId))
                onFetch();
            return cachedLastPosts.get(profileId);
        }
    }

    private class Fetched<T> implements Fetch<T> {
        private final T value;

        public Fetched(T value) {
            this.value = value;
        }

        @Override
        public T get() throws FetchError {
            return value;
        }
    }

    @Override
    synchronized public Fetch<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        ArrayList<ProfileId> friends = cachedFriends.get(profileId);
        if (friends != null)
            return new Fetched(friends);
        toFetchFriends.add(profileId);
        return new FriendToFetch(profileId);
    }

    @Override
    synchronized public Fetch<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        HashSet<Topic> topics = cachedTopics.get(profileId);
        if (topics != null)
            return new Fetched(topics);
        toFetchTopics.add(profileId);
        return new TopicToFetch(profileId);
    }

    @Override
    synchronized public Fetch<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        ArrayList<PostSummary> posts = cachedLastPosts.get(profileId);
        if (posts != null)
            return new Fetched(posts);
        toFetchPosts.add(profileId);
        return new PostToFetch(profileId);
    }

    synchronized private void onFetch() {
        if (!toFetchFriends.isEmpty())
            System.out.println("Query for friends of " + toFetchFriends);

        if (!toFetchTopics.isEmpty())
            System.out.println("Query for favorite topics of " + toFetchTopics);

        if (!toFetchPosts.isEmpty())
            System.out.println("Query for last posts of " + toFetchPosts);

        sleep();

        for (ProfileId profileId : toFetchFriends)
            cachedFriends.put(profileId, realData.friendsOf(profileId));
        toFetchFriends.clear();

        for (ProfileId profileId : toFetchTopics)
            cachedTopics.put(profileId, realData.favoriteTopicsOf(profileId));
        toFetchTopics.clear();

        for (ProfileId profileId : toFetchPosts)
            cachedLastPosts.put(profileId, realData.lastPostsOf(profileId));
        toFetchPosts.clear();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
