package com.cosysoft.randprim;

/**
 * Random integers.
 */
interface RandomIntPrimitives<ReturnValue> {

    /**
     * @return random non-negative integer x: 0 <= x <= {@link Integer#MAX_VALUE}.
     */
    ReturnValue getRandomAbsInt();

    /**
     * @param max the upper bound (exclusive). Must be 0 < max <= {@link Integer#MAX_VALUE}.
     * @return random non-negative integer x: 0 <= x < max <= {@link Integer#MAX_VALUE}.
     */
    ReturnValue getRandomAbsIntTo(int max);

    /**
     * @param min the lower bound (inclusive). Must be 0 <= min <= {@link Integer#MAX_VALUE}.
     * @return random non-negative integer x: 0 <= min <= x <= {@link Integer#MAX_VALUE}.
     */
    ReturnValue getRandomAbsIntFrom(int min);

    /**
     * @param min the lower bound (inclusive). Must be 0 <= min < max.
     * @param max the upper bound (exclusive). Must be min < max <= {@link Integer#MAX_VALUE}.
     * @return random non-negative integer x: 0 <= min <= x < max <= {@link Integer#MAX_VALUE}.
     */
    ReturnValue getRandomAbsIntFromTo(int min, int max);

    /**
     * @return random integer x: {@link Integer#MIN_VALUE} <= x <= {@link Integer#MAX_VALUE}.
     */
    ReturnValue getRandomAnyPossibleInt();
}
