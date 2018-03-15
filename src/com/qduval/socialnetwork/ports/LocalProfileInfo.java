package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.core.IAccessProfileInfo;
import com.qduval.socialnetwork.core.PostSummary;
import com.qduval.socialnetwork.core.ProfileId;
import com.qduval.socialnetwork.core.Topic;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class LocalProfileInfo implements IAccessProfileInfo {
    private final ProfileRepository profiles;

    public LocalProfileInfo(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    @Override
    public Future<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        return CompletableFuture.completedFuture(profiles.friendsOf(profileId));
    }

    @Override
    public Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        return CompletableFuture.completedFuture(profiles.favoriteTopicsOf(profileId));
    }

    @Override
    public Future<Iterable<PostSummary>> lastPostsOf(ProfileId profileId) {
        return CompletableFuture.completedFuture(profiles.lastPostsOf(profileId));
    }
}
