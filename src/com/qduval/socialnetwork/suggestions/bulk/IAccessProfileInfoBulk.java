package com.qduval.socialnetwork.suggestions.bulk;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.Set;
import java.util.concurrent.Future;

public interface IAccessProfileInfoBulk {
    Fetch<Iterable<ProfileId>> friendsOf(ProfileId profileId);
    Fetch<Set<Topic>> favoriteTopicsOf(ProfileId profileId);
    Fetch<Iterable<PostSummary>> lastPostsOf(ProfileId profileId);
}
