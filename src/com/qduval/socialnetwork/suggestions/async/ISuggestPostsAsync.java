package com.qduval.socialnetwork.suggestions.async;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;

public interface ISuggestPostsAsync {
    Iterable<PostSummary> suggestedPostsFor(ProfileId profileId);
}
