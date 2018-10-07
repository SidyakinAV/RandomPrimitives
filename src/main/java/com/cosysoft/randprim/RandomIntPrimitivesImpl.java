package com.cosysoft.randprim;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random integers.
 */
class RandomIntPrimitivesImpl implements RandomIntPrimitives<Integer> {

    private static Random randomGenerator;

    public RandomIntPrimitivesImpl() {
        RandomIntPrimitivesImpl.randomGenerator = ThreadLocalRandom.current();
    }

    public RandomIntPrimitivesImpl(Random randomGenerator) {
        RandomIntPrimitivesImpl.randomGenerator = randomGenerator;
    }

    void stubRandomGenerator(final ThreadLocalRandom randomGenerator) {
        RandomIntPrimitivesImpl.randomGenerator = randomGenerator;
    }

    @Override
    public Integer getRandomAbsInt() {
        return Math.abs(RandomIntPrimitivesImpl.randomGenerator.nextInt());
    }

    @Override
    public Integer getRandomAbsIntTo(final int max) {
        return Math.abs(RandomIntPrimitivesImpl.randomGenerator.nextInt(max));
    }

    @Override
    public Integer getRandomAbsIntFrom(final int min) {
        if (min < 0) throw new IllegalArgumentException("min must be non-negative");

        int max = Integer.MAX_VALUE;

        if (min == max) return max;
        if (min == 0) return this.getRandomAbsIntTo(max) + this.getRandomAbsIntTo(2);
        return this.getRandomAbsIntTo(max - min + 1) + min;
    }

    @Override
    public Integer getRandomAbsIntFromTo(final int min, final int max) {
        if (min < 0 || min >= max) throw new IllegalArgumentException("min must be 0 <= min < max");
        if (min == 0) return this.getRandomAbsIntTo(max);
        return this.getRandomAbsIntTo(max - min + 1) + min;
    }

    @Override
    public Integer getRandomAnyPossibleInt() {
        return RandomIntPrimitivesImpl.randomGenerator.nextInt();
    }
}
