package com.qduval.socialnetwork.suggestions.async;

public class Api {
    public static ISuggestPostsAsync suggestions(IAccessProfileInfoAsync profileInfo) {
        return new PostSuggestionAsync(profileInfo);
    }
}
