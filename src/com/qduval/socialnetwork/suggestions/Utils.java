package com.qduval.socialnetwork.suggestions;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Utils {
    static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
