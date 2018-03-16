package com.qduval.socialnetwork.suggestions.sync;

import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class Api {
    public static ISuggestPosts suggestions(IAccessProfileInfo profileInfo) {
        return new PostSuggestion(profileInfo);
    }
}
