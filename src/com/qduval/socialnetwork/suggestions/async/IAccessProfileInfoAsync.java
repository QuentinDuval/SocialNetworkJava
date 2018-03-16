package com.qduval.socialnetwork.suggestions.async;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.Set;
import java.util.concurrent.Future;

public interface IAccessProfileInfoAsync {
    Future<Iterable<ProfileId>> friendsOf(ProfileId profileId);
    Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId);
    Future<Iterable<PostSummary>> lastPostsOf(ProfileId profileId);
}
