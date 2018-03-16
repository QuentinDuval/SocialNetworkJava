package com.qduval.socialnetwork.suggestions.sync;

import com.qduval.socialnetwork.suggestions.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qduval.socialnetwork.suggestions.Utils.stream;

class PostSuggestion implements ISuggestPosts {
    private final IAccessProfileInfo profileInfo;

    PostSuggestion(IAccessProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    public Iterable<PostSummary> suggestedPostsFor(ProfileId profileId) {
        Iterable<ProfileId> friendIds = friendsOf(profileId);
        Set<Topic> topics = favoriteTopicsOf(profileId);
        Stream<PostSummary> interestingPosts = stream(friendIds)
                .flatMap(friendId -> lastPostsOf(friendId))
                .filter(post -> post.isAbout(topics));
        return mostLiked(3, interestingPosts);
    }

    private Iterable<ProfileId> friendsOf(ProfileId profileId) {
        return profileInfo.friendsOf(profileId);
    }

    private Set<Topic> favoriteTopicsOf(ProfileId profileId) {
        return profileInfo.favoriteTopicsOf(profileId);
    }

    private Stream<PostSummary> lastPostsOf(ProfileId profileId) {
        return stream(profileInfo.lastPostsOf(profileId));
    }

    private List<PostSummary> mostLiked(int count, Stream<PostSummary> posts) {
        return posts
                .sorted(Comparator.comparing(PostSummary::getLikesCount))
                .limit(count)
                .collect(Collectors.toList());
    }
}
