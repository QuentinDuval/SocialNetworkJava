package com.qduval.socialnetwork.suggestions.sync;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;

public interface ISuggestPosts {
    Iterable<PostSummary> suggestedPostsFor(ProfileId profileId);
}
