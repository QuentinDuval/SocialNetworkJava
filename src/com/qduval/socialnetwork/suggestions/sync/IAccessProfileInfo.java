package com.qduval.socialnetwork.suggestions.sync;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.Set;
import java.util.concurrent.Future;

public interface IAccessProfileInfo {
    Iterable<ProfileId> friendsOf(ProfileId profileId);
    Set<Topic> favoriteTopicsOf(ProfileId profileId);
    Iterable<PostSummary> lastPostsOf(ProfileId profileId);
}
