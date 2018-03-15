package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.PostSummary;
import com.qduval.socialnetwork.suggestions.ProfileId;
import com.qduval.socialnetwork.suggestions.Topic;

import java.util.*;

public class ProfileRepository {
    private final HashMap<ProfileId, ArrayList<ProfileId>> friends = new HashMap<>();
    private final HashMap<ProfileId, HashSet<Topic>> subjects = new HashMap<>();
    private final HashMap<ProfileId, ArrayList<PostSummary>> lastPosts = new HashMap<>();

    public ArrayList<ProfileId> friendsOf(ProfileId profileId) {
        return friends.get(profileId);
    }

    public HashSet<Topic> favoriteTopicsOf(ProfileId profileId) {
        return subjects.get(profileId);
    }

    public ArrayList<PostSummary> lastPostsOf(ProfileId profileId) {
        return lastPosts.get(profileId);
    }

    void addFriends(ProfileId userId, ProfileId... friendIds) {
        ArrayList<ProfileId> foundFriends = friends.get(userId);
        if (null == foundFriends) {
            foundFriends = new ArrayList<>();
            friends.put(userId, foundFriends);
        }
        foundFriends.addAll(Arrays.asList(friendIds));
    }

    void addSubjects(ProfileId userId, Topic... topics) {
        HashSet<Topic> foundFriends = this.subjects.get(userId);
        if (null == foundFriends) {
            foundFriends = new HashSet<>();
            this.subjects.put(userId, foundFriends);
        }
        foundFriends.addAll(Arrays.asList(topics));
    }

    void addPosts(ProfileId userId, PostSummary... postSummaries) {
        ArrayList<PostSummary> foundPosts = this.lastPosts.get(userId);
        if (null == foundPosts) {
            foundPosts = new ArrayList<>();
            this.lastPosts.put(userId, foundPosts);
        }
        foundPosts.addAll(Arrays.asList(postSummaries));
    }
}
