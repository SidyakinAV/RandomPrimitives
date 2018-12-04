package com.cosysoft.randprim;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * todo: description
 */
public interface LazyBuilder<Type> {

    Type next();

    default Collection<Type> next(final int amount) {
        return Stream.generate(this::next).limit(amount).collect(Collectors.toList());
    }

    default Collection<Type> next(final int amount, final Predicate<Type> matcher) {
        return Stream.generate(this::next).filter(matcher).limit(amount).collect(Collectors.toList());
    }

    default Stream<Type> stream() {
        return Stream.generate(this::next);
    }
}
