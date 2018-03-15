package com.qduval.socialnetwork.ports;

import com.qduval.socialnetwork.suggestions.*;

public class LocalProfileApi {
    public static ISuggestPosts suggestions() {
        return Api.suggestions(new LocalProfileInfo(fakeDb()));
    }

    static ProfileRepository fakeDb() {
        return new ProfileRepository() {{
            addFriends(profile(1), profile(2), profile(3));
            addFriends(profile(2), profile(1), profile(3));
            addFriends(profile(3), profile(1), profile(2));

            addSubjects(profile(1), subject("C++"), subject("Java"));
            addSubjects(profile(2), subject("C++"), subject("Haskell"));
            addSubjects(profile(3), subject("Clojure"), subject("Haskell"));

            addPosts(profile(1), post("C++", 15), post("Java", 10));
            addPosts(profile(2), post("Haskell", 15), post("C++", 5), post("C++", 10));
            addPosts(profile(3), post("Java", 20), post("Haskell", 5), post("Java", 5));
        }};
    }

    private static ProfileId profile(long profileId) {
        return new ProfileId(profileId);
    }

    private static PostSummary post(String subject, int likes) {
        return new PostSummary(subject(subject), likes);
    }

    private static Topic subject(String subject) {
        return new Topic(subject);
    }
}
