package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.sync.IAccessProfileInfo;
import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class LocalProfileInfo implements IAccessProfileInfo {
    private final ProfileRepository profiles;

    public LocalProfileInfo(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    @Override
    public Iterable<ProfileId> friendsOf(ProfileId profileId) {
        return profiles.friendsOf(profileId);
    }

    @Override
    public Set<Topic> favoriteTopicsOf(ProfileId profileId) {
        return profiles.favoriteTopicsOf(profileId);
    }

    @Override
    public Iterable<PostSummary> lastPostsOf(ProfileId profileId) {
        return profiles.lastPostsOf(profileId);
    }
}
