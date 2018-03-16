package com.qduval.socialnetwork.suggestions.async;

import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class Api {
    public static ISuggestPosts suggestions(IAccessProfileInfoAsync profileInfo) {
        return new PostSuggestionAsync(profileInfo);
    }
}
