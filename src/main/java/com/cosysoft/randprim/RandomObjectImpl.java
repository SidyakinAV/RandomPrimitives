package com.cosysoft.randprim;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

/**
 * todo: description
 */
class RandomObjectImpl implements RandomObject<Object, Object> {
    private final RandomIntPrimitives<Integer> randomIntGenerator;

    public RandomObjectImpl() {
        this.randomIntGenerator = new RandomIntPrimitivesImpl();
    }

    public RandomObjectImpl(Random randomGenerator) {
        this.randomIntGenerator = new RandomIntPrimitivesImpl(randomGenerator);
    }

    public Object getRandomOf(final Object... objects)
        throws IllegalArgumentException
    {
        this.validateVarArgNotEmpty(objects);
        final int randomIndex = randomIntGenerator.getRandomAbsIntTo(objects.length);
        return objects[randomIndex];
    }

    public Object getRandomResult(final Supplier<Object>... suppliers)
        throws IllegalArgumentException
    {
        this.validateVarArgNotEmpty(suppliers);
        final int randomIndex = this.randomIntGenerator.getRandomAbsIntTo(suppliers.length);
        return suppliers[randomIndex].get();
    }

    public Object getRandomOf(final Collection<Object> objects)
        throws IllegalArgumentException
    {
        this.validateVarArgNotEmpty(objects);

        int randomIndex = randomIntGenerator.getRandomAbsIntTo(objects.size());
        for(Object t: objects) if (--randomIndex < 0) return t;

        throw new RuntimeException("randomIndex > objects.size()");
    }

    public Object getRandomResult(final Collection<Supplier<Object>> suppliers)
        throws IllegalArgumentException
    {
        this.validateVarArgNotEmpty(suppliers);
        int randomIndex = randomIntGenerator.getRandomAbsIntTo(suppliers.size());
        for(Supplier<Object> t: suppliers) if (--randomIndex < 0) return t.get();

        throw new RuntimeException("randomIndex > objects.size()");
    }

    private void validateVarArgNotEmpty(final Object[] params) throws IllegalArgumentException {
        if (params.length == 0) {
            this.throwNotEmptyException();
        }
    }

    private void validateVarArgNotEmpty(final Collection params) throws IllegalArgumentException {
        if (params.isEmpty()) {
            this.throwNotEmptyException();
        }
    }

    private void throwNotEmptyException() {
        throw new IllegalArgumentException("you must provide not empty list of parameters");
    }
}
