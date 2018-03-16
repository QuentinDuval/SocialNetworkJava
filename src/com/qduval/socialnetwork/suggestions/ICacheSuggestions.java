package com.qduval.socialnetwork.suggestions;

public interface ICacheSuggestions {
    void saveSuggestions(ProfileId profileId, Iterable<PostSummary> suggestions);
    Iterable<PostSummary> loadSuggestions(ProfileId profileId);
}
