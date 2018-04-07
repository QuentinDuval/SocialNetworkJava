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

    private final HashMap<ProfileId, CompletableFuture<ArrayList<ProfileId>>> cachedFriends = new HashMap<>();
    private final HashMap<ProfileId, CompletableFuture<HashSet<Topic>>> cachedTopics = new HashMap<>();
    private final HashMap<ProfileId, CompletableFuture<ArrayList<PostSummary>>> cachedLastPosts = new HashMap<>();

    private final HashSet<ProfileId> toFetchFriends = new HashSet<>();
    private final HashSet<ProfileId> toFetchTopics = new HashSet<>();
    private final HashSet<ProfileId> toFetchPosts = new HashSet<>();

    public RemoteBulkProfileInfo(ProfileRepository realData) {
        this.realData = realData;
    }

    private class FriendToFetch implements Fetch<Iterable<ProfileId>> {
        private final ProfileId profileId;

        public FriendToFetch(ProfileId profileId) {
            this.profileId = profileId;
        }

        @Override
        public Iterable<ProfileId> get() throws FetchError {
            if (!cachedFriends.containsKey(profileId)) {
                // TODO - you could fetch stuff here... but you cannot easily group parallel computations (order counts)
                // TODO - you can only do something like introducing delays
            }

            try {
                return cachedFriends.get(profileId).get();
            } catch (Exception e) {
                throw new FetchError(e.getMessage());
            }
        }
    }

    @Override
    synchronized public Fetch<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        return null;
    }

    @Override
    synchronized public Fetch<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        return null;
    }

    @Override
    synchronized public Fetch<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        return null;
    }
}
