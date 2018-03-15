package com.qduval.socialnetwork.core;

import java.util.Set;

public class PostSummary {
    private final Topic topic;
    private final int likesCount;

    public PostSummary(Topic topic, int likes) {
        this.topic = topic;
        this.likesCount = likes;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public Topic getTopic() {
        return topic;
    }

    public boolean isAbout(Set<Topic> topics) {
        return topics.contains(getTopic());
    }

    @Override
    public String toString() {
        return "PostSummary{" +
                "topic=" + topic +
                ", likesCount=" + likesCount +
                '}';
    }
}
