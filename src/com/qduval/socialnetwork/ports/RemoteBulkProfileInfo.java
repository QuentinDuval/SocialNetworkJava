package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;
import com.qduval.socialnetwork.suggestions.bulk.Fetch;
import com.qduval.socialnetwork.suggestions.bulk.FetchError;
import com.qduval.socialnetwork.suggestions.bulk.IAccessProfileInfoBulk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteBulkProfileInfo implements IAccessProfileInfoBulk {
    private final ProfileRepository realData;

    private final ConcurrentHashMap<ProfileId, CompletableFuture<ArrayList<ProfileId>>> cachedFriends = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<ProfileId, CompletableFuture<HashSet<Topic>>> cachedTopics = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<ProfileId, CompletableFuture<ArrayList<PostSummary>>> cachedLastPosts = new ConcurrentHashMap<>();

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
            try {
                return cachedFriends.get(profileId).get();
            } catch (Exception e) {
                throw new FetchError(e.getMessage());
            }
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
            try {
                return cachedTopics.get(profileId).get();
            } catch (Exception e) {
                throw new FetchError(e.getMessage());
            }
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
            try {
                return cachedLastPosts.get(profileId).get();
            } catch (Exception e) {
                throw new FetchError(e.getMessage());
            }
        }
    }

    private class Fetched<T> implements Fetch<T> {
        private final CompletableFuture<T> future;

        public Fetched(CompletableFuture<T> future) {
            this.future = future;
        }

        @Override
        public T get() throws FetchError {
            try {
                return future.get();
            } catch (Exception e) {
                throw new FetchError(e.getMessage());
            }
        }
    }

    @Override
    synchronized public Fetch<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        CompletableFuture<ArrayList<ProfileId>> friends = cachedFriends.get(profileId);
        if (friends != null)
            return new Fetched(friends);
        return new FriendToFetch(profileId);
    }

    @Override
    synchronized public Fetch<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        CompletableFuture<HashSet<Topic>> topics = cachedTopics.get(profileId);
        if (topics != null)
            return new Fetched(topics);
        return new TopicToFetch(profileId);
    }

    @Override
    synchronized public Fetch<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        CompletableFuture<ArrayList<PostSummary>> posts = cachedLastPosts.get(profileId);
        if (posts != null)
            return new Fetched(posts);
        return new PostToFetch(profileId);
    }

    synchronized private void onFetch() {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Query for topics of " + profileId);
            sleep();
            return realData.favoriteTopicsOf(profileId);
        })

        // private final HashSet<ProfileId> toFetchFriends = new HashSet<>();
        // private final HashSet<ProfileId> toFetchTopics = new HashSet<>();
        // private final HashSet<ProfileId> toFetchPosts = new HashSet<>();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
