package com.qduval.socialnetwork.core;

import java.util.Objects;

public class Topic {
    private final String subject;

    public Topic(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic1 = (Topic) o;
        return Objects.equals(subject, topic1.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
