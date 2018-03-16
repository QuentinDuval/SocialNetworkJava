package com.qduval.socialnetwork.suggestions.async;

import com.qduval.socialnetwork.suggestions.IComputePostSuggestions;

public class AsyncApi {
    public static IComputePostSuggestions suggestions(IAccessProfileInfoAsync profileInfo) {
        return new SuggestPopularFriendPost(profileInfo);
    }
}
