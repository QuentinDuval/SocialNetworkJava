package com.qduval.socialnetwork;

import com.qduval.socialnetwork.ports.LocalProfileApi;
import com.qduval.socialnetwork.ports.RemoteAsyncProfileApi;
import com.qduval.socialnetwork.ports.RemoteBlockingProfileApi;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;
import com.qduval.socialnetwork.suggestions.ProfileId;

public class Main {
    public static void main(String[] args) {
        exampleOfClientCode();
    }

    private static void exampleOfClientCode() {

        ISuggestPosts local = LocalProfileApi.suggestions();
        System.out.println(local.suggestedPostsFor(new ProfileId(1)));
        System.out.println(local.suggestedPostsFor(new ProfileId(2)));

        ISuggestPosts remote = RemoteBlockingProfileApi.suggestions();
        System.out.println(remote.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remote.suggestedPostsFor(new ProfileId(2)));

        ISuggestPosts remoteAsync = RemoteAsyncProfileApi.suggestions();
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(2)));
    }
}
