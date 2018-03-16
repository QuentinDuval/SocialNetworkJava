package com.qduval.socialnetwork.suggestions.sync;

public class Api {
    public static ISuggestPosts suggestions(IAccessProfileInfo profileInfo) {
        return new PostSuggestion(profileInfo);
    }
}
