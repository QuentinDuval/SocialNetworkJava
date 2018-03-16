package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.sync.Api;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class RemoteBlockingProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestions(new RemoteBlockingProfileInfo(LocalProfileApi.fakeDb()));
    }
}
