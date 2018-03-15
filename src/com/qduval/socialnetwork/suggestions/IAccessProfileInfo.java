package com.qduval.socialnetwork.suggestions;

import java.util.Set;
import java.util.concurrent.Future;

public interface IAccessProfileInfo {
    Future<Iterable<ProfileId>> friendsOf(ProfileId profileId);
    Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId);
    Future<Iterable<PostSummary>> lastPostsOf(ProfileId profileId);
}
