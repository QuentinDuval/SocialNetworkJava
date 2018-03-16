package com.qduval.socialnetwork.suggestions.sync;

import com.qduval.socialnetwork.suggestions.IComputePostSuggestions;

public class SyncApi {
    public static IComputePostSuggestions suggestions(IAccessProfileInfo profileInfo) {
        return new SuggestPopularFriendPost(profileInfo);
    }
}
