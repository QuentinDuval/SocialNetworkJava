package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.Api;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class RemoteBlockingProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestPosts(new RemoteBlockingProfileInfo(LocalProfileApi.fakeDb()), new SuggestionCache());
    }
}
