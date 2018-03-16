package com.qduval.socialnetwork.suggestions;

class PostSuggestions implements ISuggestPosts {
    private final ICacheSuggestions cache;
    private final IComputePostSuggestions algorithm;

    PostSuggestions(ICacheSuggestions cache, IComputePostSuggestions algorithm) {
        this.cache = cache;
        this.algorithm = algorithm;
    }

    @Override
    public Iterable<PostSummary> suggestedPostsFor(ProfileId profileId) {
        Iterable<PostSummary> posts = cache.loadSuggestions(profileId);
        if (posts != null)
            return posts;

        posts = algorithm.suggestedPostsFor(profileId);
        cache.saveSuggestions(profileId, posts);
        return posts;
    }

    @Override
    public void resetSuggestionOf(ProfileId profileId) {
        cache.saveSuggestions(profileId, null);
    }
}
