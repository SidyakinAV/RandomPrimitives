package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.Random;

/**
 * todo: description
 */
class RandomPrimitivesWithTraceAlternativeImpl
    extends BaseTraceAlternative<Integer>
    implements RandomIntPrimitives<TraceAlternativeBuilder<Integer>>
{
    private final RandomIntPrimitives<Integer> randomIntGenerator;

    public RandomPrimitivesWithTraceAlternativeImpl() {
        this.randomIntGenerator = new RandomIntPrimitivesImpl();
    }

    public RandomPrimitivesWithTraceAlternativeImpl(@NonNull final Random randomGenerator) {
        this.randomIntGenerator = new RandomIntPrimitivesImpl(randomGenerator);
    }

    public TraceAlternativeBuilder<Integer> getRandomAbsInt() {
        return this.getBuilder(this.randomIntGenerator::getRandomAbsInt);
    }

    public TraceAlternativeBuilder<Integer> getRandomAbsIntTo(final int max) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntTo(max)
        );
    }

    public TraceAlternativeBuilder<Integer> getRandomAbsIntFrom(final int min) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntFrom(min)
        );
    }

    public TraceAlternativeBuilder<Integer> getRandomAbsIntFromTo(final int min, final int max) {
        return this.getBuilder(
            () -> this.randomIntGenerator.getRandomAbsIntFromTo(min, max)
        );
    }

    public TraceAlternativeBuilder<Integer> getRandomAnyPossibleInt() {
        return this.getBuilder(this.randomIntGenerator::getRandomAnyPossibleInt);
    }
}