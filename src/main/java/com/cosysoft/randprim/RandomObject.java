package com.cosysoft.randprim;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * todo: description
 */
public interface RandomObject<ParamsType, ReturnType> {
    ReturnType getRandomOf(final ParamsType... objects)
        throws IllegalArgumentException;

    ReturnType getRandomResult(final Supplier<ParamsType>... suppliers)
        throws IllegalArgumentException;

    ReturnType getRandomOf(final Collection<ParamsType> objects)
        throws IllegalArgumentException;

    ReturnType getRandomResult(final Collection<Supplier<ParamsType>> suppliers)
        throws IllegalArgumentException;
}
