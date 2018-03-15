package com.qduval.socialnetwork.core;

public interface ISuggestPosts {
    Iterable<PostSummary> suggestedPostsFor(ProfileId profileId);
}
