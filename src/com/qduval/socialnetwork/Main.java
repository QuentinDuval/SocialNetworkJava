package com.qduval.socialnetwork;

import com.qduval.socialnetwork.core.ISuggestPosts;
import com.qduval.socialnetwork.core.ProfileId;
import com.qduval.socialnetwork.ports.LocalProfileApi;
import com.qduval.socialnetwork.ports.RemoteProfileApi;

public class Main {
    public static void main(String[] args) {
        exampleOfClientCode();
    }

    private static void exampleOfClientCode() {

        ISuggestPosts local = LocalProfileApi.suggestions();
        System.out.println(local.suggestedPostsFor(new ProfileId(1)));
        System.out.println(local.suggestedPostsFor(new ProfileId(2)));

        ISuggestPosts remote = RemoteProfileApi.suggestions();
        System.out.println(remote.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remote.suggestedPostsFor(new ProfileId(2)));

        ISuggestPosts remoteAsync = RemoteProfileApi.suggestionsAsync();
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(2)));
    }
}
