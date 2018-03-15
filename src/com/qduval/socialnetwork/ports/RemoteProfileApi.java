package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.core.ISuggestPosts;
import com.qduval.socialnetwork.core.Api;

public class RemoteProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestions(new RemoteBlockingProfileInfo(LocalProfileApi.fakeDb()));
    }

    public static ISuggestPosts suggestionsAsync() {
        return Api.suggestionsAsync(new RemoteAsyncProfileInfo(LocalProfileApi.fakeDb()));
    }
}
