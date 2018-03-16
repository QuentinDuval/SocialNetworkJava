package com.qduval.socialnetwork.suggestions;

public interface ICacheSuggestions {
    void saveSuggestions(Iterable<PostSummary> suggestions);
    Iterable<PostSummary> loadSuggestions();
}
