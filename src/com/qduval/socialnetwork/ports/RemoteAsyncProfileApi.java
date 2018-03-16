package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.Api;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class RemoteAsyncProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestPostsAsync(new RemoteAsyncProfileInfo(LocalProfileApi.fakeDb()), new SuggestionCache());
    }
}
