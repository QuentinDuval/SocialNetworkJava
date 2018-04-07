package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.Api;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;

public class RemoteBulkProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestPostsBulk(new RemoteBulkProfileInfo(LocalProfileApi.fakeDb()), new SuggestionCache());
    }
}
