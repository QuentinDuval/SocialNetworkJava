package com.qduval.socialnetwork.suggestions.bulk;

public interface Fetch<T> {
    T get() throws FetchError;

    // TODO - with map, we can avoid errors
}
