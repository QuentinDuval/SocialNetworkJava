package com.qduval.socialnetwork.core;

public class Api {
    public static ISuggestPosts suggestions(IAccessProfileInfo profileInfo) {
        return new PostSuggestion(profileInfo);
    }

    public static ISuggestPosts suggestionsAsync(IAccessProfileInfo profileInfo) {
        return new PostSuggestionAsync(profileInfo);
    }
}
