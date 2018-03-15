package com.qduval.socialnetwork.core;

import java.util.Objects;

public class ProfileId {
    private final long id;

    public ProfileId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileId profileId = (ProfileId) o;
        return id == profileId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProfileId{" +
                "id=" + id +
                '}';
    }
}
