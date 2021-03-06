package com.qduval.socialnetwork;

import com.qduval.socialnetwork.ports.LocalProfileApi;
import com.qduval.socialnetwork.ports.RemoteAsyncProfileApi;
import com.qduval.socialnetwork.ports.RemoteBlockingProfileApi;
import com.qduval.socialnetwork.ports.RemoteBulkProfileApi;
import com.qduval.socialnetwork.suggestions.ISuggestPosts;
import com.qduval.socialnetwork.suggestions.ProfileId;

public class Main {
    public static void main(String[] args) {
        exampleOfClientCode();
    }

    private static void exampleOfClientCode() {

        /*
        System.out.println("----------------");
        System.out.println("LOCAL API");
        System.out.println("----------------");

        ISuggestPosts local = LocalProfileApi.suggestions();
        System.out.println(local.suggestedPostsFor(new ProfileId(1)));
        System.out.println(local.suggestedPostsFor(new ProfileId(1)));
        System.out.println(local.suggestedPostsFor(new ProfileId(2)));

        System.out.println("----------------");
        System.out.println("REMOTE API");
        System.out.println("----------------");

        ISuggestPosts remote = RemoteBlockingProfileApi.suggestions();
        System.out.println(remote.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remote.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remote.suggestedPostsFor(new ProfileId(2)));
        */

        System.out.println("----------------");
        System.out.println("REMOTE ASYNC API");
        System.out.println("----------------");

        ISuggestPosts remoteAsync = RemoteAsyncProfileApi.suggestions();
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteAsync.suggestedPostsFor(new ProfileId(2)));

        System.out.println("----------------");
        System.out.println("BULK ASYNC API");
        System.out.println("----------------");

        ISuggestPosts remoteBulk = RemoteBulkProfileApi.suggestions();
        System.out.println(remoteBulk.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteBulk.suggestedPostsFor(new ProfileId(1)));
        System.out.println(remoteBulk.suggestedPostsFor(new ProfileId(2)));
    }
}
