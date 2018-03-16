package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.async.IAccessProfileInfoAsync;
import com.qduval.socialnetwork.suggestions.sync.IAccessProfileInfo;
import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.function.Function;

class RemoteAsyncProfileInfo implements IAccessProfileInfoAsync {
    private final ProfileRepository realData;
    private final ConcurrentHashMap<ProfileId, CompletableFuture<ArrayList<ProfileId>>> cachedFriends = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<ProfileId, CompletableFuture<HashSet<Topic>>> cachedTopics = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<ProfileId, CompletableFuture<ArrayList<PostSummary>>> cachedLastPosts = new ConcurrentHashMap<>();

    RemoteAsyncProfileInfo(ProfileRepository realData) {
        this.realData = realData;
    }

    private synchronized CompletableFuture<ArrayList<ProfileId>> cachedFriendsOf(ProfileId profileId) {
        return cachedFriends.get(profileId);
    }

    private synchronized CompletableFuture<HashSet<Topic>> cachedFavoriteTopicsOf(ProfileId profileId) {
        return cachedTopics.get(profileId);
    }

    private synchronized CompletableFuture<ArrayList<PostSummary>> cachedLastPostsOf(ProfileId profileId) {
        return cachedLastPosts.get(profileId);
    }

    @Override
    public Future<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        CompletableFuture<ArrayList<ProfileId>> friends = cachedFriendsOf(profileId);
        if (friends == null) {
            cachedFriends.put(profileId, CompletableFuture.supplyAsync(() -> {
                System.out.println("Query for friends of " + profileId);
                sleep();
                return realData.friendsOf(profileId);
            }));
        }
        return cachedFriendsOf(profileId).thenApply(Function.identity());
    }

    @Override
    public Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        CompletableFuture<HashSet<Topic>> topics = cachedFavoriteTopicsOf(profileId);
        if (topics == null) {
            cachedTopics.put(profileId, CompletableFuture.supplyAsync(() -> {
                System.out.println("Query for topics of " + profileId);
                sleep();
                return realData.favoriteTopicsOf(profileId);
            }));
        }
        return cachedFavoriteTopicsOf(profileId).thenApply(Function.identity());
    }

    @Override
    public Future<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        CompletableFuture<ArrayList<PostSummary>> posts = cachedLastPostsOf(profileId);
        if (posts == null) {
            cachedLastPosts.put(profileId, CompletableFuture.supplyAsync(() -> {
                System.out.println("Query for posts of " + profileId);
                sleep();
                return realData.lastPostsOf(profileId);
            }));
        }
        return cachedLastPostsOf(profileId).thenApply(Function.identity());
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
