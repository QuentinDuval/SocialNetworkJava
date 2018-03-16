package com.qduval.socialnetwork.suggestions;

public interface ISuggestPosts {
    Iterable<PostSummary> suggestedPostsFor(ProfileId profileId);
    void resetSuggestionOf(ProfileId profileId);
}
