package com.qduval.socialnetwork.suggestions;

import com.qduval.socialnetwork.suggestions.async.AsyncApi;
import com.qduval.socialnetwork.suggestions.async.IAccessProfileInfoAsync;
import com.qduval.socialnetwork.suggestions.sync.IAccessProfileInfo;
import com.qduval.socialnetwork.suggestions.sync.SyncApi;

public class Api {
    public static ISuggestPosts suggestPosts(IAccessProfileInfo profileInfo, ICacheSuggestions cache) {
        return new PostSuggestions(cache, SyncApi.suggestions(profileInfo));
    }

    public static ISuggestPosts suggestPostsAsync(IAccessProfileInfoAsync profileInfo, ICacheSuggestions cache) {
        return new PostSuggestions(cache, AsyncApi.suggestions(profileInfo));
    }
}
