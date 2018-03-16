package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.ISuggestPosts;
import com.qduval.socialnetwork.suggestions.async.Api;

public class RemoteAsyncProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestions(new RemoteAsyncProfileInfo(LocalProfileApi.fakeDb()));
    }
}
