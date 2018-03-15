package com.qduval.socialnetwork.core;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qduval.socialnetwork.core.Utils.stream;

class PostSuggestionAsync implements ISuggestPosts {
    private final IAccessProfileInfo profileInfo;

    PostSuggestionAsync(IAccessProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    public Iterable<PostSummary> suggestedPostsFor(ProfileId profileId) {
        try {
            Future<Iterable<ProfileId>> friendIds = friendsOf(profileId);
            Set<Topic> topics = favoriteTopicsOf(profileId).get();
            Stream<PostSummary> interestingPosts = stream(friendIds.get())
                    .parallel()
                    .flatMap(friendId -> lastPostsOf(friendId))
                    .filter(post -> post.isAbout(topics));
            return mostLiked(3, interestingPosts);
        } catch (InterruptedException e) {
            return Arrays.asList();
        } catch (ExecutionException e) {
            return Arrays.asList();
        }
    }

    private Future<Iterable<ProfileId>> friendsOf(ProfileId profileId) {
        return profileInfo.friendsOf(profileId);
    }

    private Future<Set<Topic>> favoriteTopicsOf(ProfileId profileId) {
        return profileInfo.favoriteTopicsOf(profileId);
    }

    private Stream<PostSummary> lastPostsOf(ProfileId profileId) {
        try {
            return stream(profileInfo.lastPostsOf(profileId).get());
        } catch (InterruptedException e) {
            return stream(Arrays.asList());
        } catch (ExecutionException e) {
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
