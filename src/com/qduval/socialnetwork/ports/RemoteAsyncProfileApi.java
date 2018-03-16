package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.async.Api;
import com.qduval.socialnetwork.suggestions.async.ISuggestPostsAsync;

public class RemoteAsyncProfileApi {
    public static ISuggestPostsAsync suggestions() {
        return Api.suggestions(new RemoteAsyncProfileInfo(LocalProfileApi.fakeDb()));
    }
}
