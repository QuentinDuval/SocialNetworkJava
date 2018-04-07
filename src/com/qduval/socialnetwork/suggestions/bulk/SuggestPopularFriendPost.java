package com.qduval.socialnetwork.suggestions.bulk;

import com.qduval.socialnetwork.suggestions.IComputePostSuggestions;
import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qduval.socialnetwork.suggestions.Utils.stream;

class SuggestPopularFriendPost implements IComputePostSuggestions {
    private final IAccessProfileInfoBulk profileInfo;

    SuggestPopularFriendPost(IAccessProfileInfoBulk profileInfo) {
        this.profileInfo = profileInfo;
    }

    @Override
    public Iterable<PostSummary> suggestedPostsFor(ProfileId profileId) {
        try {
            Fetch<Iterable<ProfileId>> friendIds = friendsOf(profileId);
            Set<Topic> topics = favoriteTopicsOf(profileId).get();
            Stream<PostSummary> interestingPosts = stream(friendIds.get())
                    .parallel()
                    .flatMap(friendId -> lastPostsOf(friendId))
                    .filter(post -> post.isAbout(topics));
            return mostLiked(3, interestingPosts);
        } catch (FetchError e) {
            return Arrays.asList();
        }
    }

    private Fetch<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        return profileInfo.friendsOf(profileId);
    }

    private Fetch<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        return profileInfo.favoriteTopicsOf(profileId);
    }

    private Stream<PostSummary> lastPostsOf(ProfileId profileId) {
        try {
            return stream(profileInfo.lastPostsOf(profileId).get());
        } catch (FetchError e) {
            return stream(Arrays.asList());
        }
    }

    private List<PostSummary> mostLiked(int count, Stream<PostSummary> posts) {
        return posts
                .sorted(Collections.reverseOrder(Comparator.comparing(PostSummary::getLikesCount)))
                .limit(count)
                .collect(Collectors.toList());
    }
}
