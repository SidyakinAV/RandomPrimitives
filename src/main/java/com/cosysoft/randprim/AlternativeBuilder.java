package com.cosysoft.randprim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * todo: description
 */
class AlternativeBuilder<Type> {
    private List<Supplier<Type>> alternatives = new ArrayList<>();

    AlternativeBuilder(final Type alternative) {
        this.alternatives.add(() -> alternative);
    }

    AlternativeBuilder(final Supplier<Type> alternative) {
        this.alternatives.add(alternative);
    }

    AlternativeBuilder<Type> or(Type alternative) {
        this.alternatives.add(() -> alternative);
        return this;
    }

    AlternativeBuilder<Type> or(final Supplier<Type> alternative) {
        this.alternatives.add(alternative);
        return this;
    }

    AlternativeBuilder<Type> or(Type... alternatives) {
        final List<Supplier<Type>> alternativesList = Arrays.asList(alternatives).stream()
            .map(type -> {
                Supplier<Type> supplier = () -> type;
                return supplier;
            })
            .collect(Collectors.toList());
        this.alternatives.addAll(alternativesList);
        return this;
    }

    AlternativeBuilder<Type> or(final Supplier<Type>... alternatives) {
        this.alternatives.addAll(Arrays.asList(alternatives));
        return this;
    }

    Type get() {
        return new RandomObjectImpl().getRandomResult(this.alternatives);
    }
}