package com.qduval.socialnetwork.core;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Utils {
    static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
