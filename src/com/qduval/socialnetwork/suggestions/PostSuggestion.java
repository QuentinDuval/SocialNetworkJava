package com.qduval.socialnetwork.suggestions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                // .parallel() // Easy parallelism
                .flatMap(friendId -> lastPostsOf(friendId))
                .filter(post -> post.isAbout(topics));
        return mostLiked(3, interestingPosts);
    }

    private Iterable<ProfileId> friendsOf(ProfileId profileId) {
        try {
            return profileInfo.friendsOf(profileId).get();
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    private Set<Topic> favoriteTopicsOf(ProfileId profileId) {
        try {
            return profileInfo.favoriteTopicsOf(profileId).get();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    private Stream<PostSummary> lastPostsOf(ProfileId profileId) {
        try {
            return stream(profileInfo.lastPostsOf(profileId).get());
        } catch (Exception e) {
            return stream(Arrays.asList());
        }
    }

    private List<PostSummary> mostLiked(int count, Stream<PostSummary> posts) {
        return posts
                .sorted((lhs, rhs) -> rhs.getLikesCount() - lhs.getLikesCount())
                .limit(count)
                .collect(Collectors.toList());
    }
}
