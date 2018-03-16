package com.qduval.socialnetwork.suggestions;

public interface IComputePostSuggestions {
    Iterable<PostSummary> suggestedPostsFor(ProfileId profileId);
}
