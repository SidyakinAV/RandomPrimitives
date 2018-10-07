package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

/**
 * todo: description
 */
public class RandomObjectWithTraceImpl extends BaseTrace<Object> implements RandomObject<Object, TraceBuilder<Object>> {

    private final RandomObject<Object, Object> randomGenerator;
    private final TraceHolder<Integer> traceHolder = new TraceHolderImpl<>();
    private final TraceReporter traceReporter = new TraceReporterImpl(this.traceHolder);

    public RandomObjectWithTraceImpl() {
        this.randomGenerator = new RandomObjectImpl();
    }

    public RandomObjectWithTraceImpl(@NonNull final Random randomGenerator) {
        this.randomGenerator = new RandomObjectImpl(randomGenerator);
    }

    @Override
    public TraceBuilder<Object> getRandomOf(final Object... objects)
        throws IllegalArgumentException
    {
        return this.getBuilder(
            () -> this.randomGenerator.getRandomOf(objects)
        );
    }

    @Override
    public TraceBuilder<Object> getRandomResult(final Supplier<Object>... suppliers)
        throws IllegalArgumentException
    {
        return this.getBuilder(
            () -> this.randomGenerator.getRandomResult(suppliers)
        );
    }

    @Override
    public TraceBuilder<Object> getRandomOf(final Collection<Object> objects)
        throws IllegalArgumentException
    {
        return this.getBuilder(
            () -> this.randomGenerator.getRandomOf(objects)
        );
    }

    @Override
    public TraceBuilder<Object> getRandomResult(final Collection<Supplier<Object>> suppliers)
        throws IllegalArgumentException
    {
        return this.getBuilder(
            () -> this.randomGenerator.getRandomResult(suppliers)
        );
    }
}
