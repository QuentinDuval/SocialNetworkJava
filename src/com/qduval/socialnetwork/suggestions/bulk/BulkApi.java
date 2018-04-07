package com.qduval.socialnetwork.suggestions.bulk;

import com.qduval.socialnetwork.suggestions.IComputePostSuggestions;

public class BulkApi {
    public static IComputePostSuggestions suggestions(IAccessProfileInfoBulk profileInfo) {
        return new SuggestPopularFriendPost(profileInfo);
    }
}
