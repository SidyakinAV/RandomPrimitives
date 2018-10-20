package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.Random;

/**
 * todo: description
 */
class RandomIntPrimitivesWithTraceAlternativeImpl
    extends BaseTraceAlternative<Integer>
    implements RandomIntPrimitives<TraceAlternativeBuilder<Integer, Integer>>
{
    private final RandomIntPrimitives<Integer> randomIntGenerator;

    RandomIntPrimitivesWithTraceAlternativeImpl() {
        this.randomIntGenerator = new RandomIntPrimitivesImpl();
    }

    RandomIntPrimitivesWithTraceAlternativeImpl(@NonNull final Random randomGenerator) {
        this.randomIntGenerator = new RandomIntPrimitivesImpl(randomGenerator);
    }

    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsInt() {
        return this.getBuilder(this.randomIntGenerator::getRandomAbsInt);
    }

    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntTo(final int max) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntTo(max)
        );
    }

    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntFrom(final int min) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntFrom(min)
        );
    }

    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntFromTo(final int min, final int max) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntFromTo(min, max)
        );
    }

    public TraceAlternativeBuilder<Integer, Integer> getRandomAnyPossibleInt() {
        return this.getBuilder(this.randomIntGenerator::getRandomAnyPossibleInt);
    }
}