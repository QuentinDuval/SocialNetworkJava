package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.ICacheSuggestions;
import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;

import java.util.HashMap;

class SuggestionCache implements ICacheSuggestions {
    private final HashMap<ProfileId, Iterable<PostSummary>> cache;

    public SuggestionCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void saveSuggestions(ProfileId profileId, Iterable<PostSummary> suggestions) {
        cache.put(profileId, suggestions);
    }

    @Override
    public Iterable<PostSummary> loadSuggestions(ProfileId profileId) {
        return cache.get(profileId);
    }
}
