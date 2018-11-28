package com.cosysoft.randprim;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

/**
 * todo: description
 */
class RandomObjectImpl {
    private final RandomIntPrimitives<Integer> randomIntGenerator;

    public RandomObjectImpl() {
        this.randomIntGenerator = new RandomIntPrimitivesImpl();
    }

    public RandomObjectImpl(final Random randomGenerator) {
        this.randomIntGenerator = new RandomIntPrimitivesImpl(randomGenerator);
    }

    public <ReturnType> ReturnType getRandomOf(final ReturnType... objects)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(objects);
        final int randomIndex = randomIntGenerator.getRandomAbsIntTo(objects.length);
        return objects[randomIndex];
    }

    public <ReturnType> ReturnType getRandomResult(final Supplier<ReturnType>... suppliers)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(suppliers);
        final int randomIndex = this.randomIntGenerator.getRandomAbsIntTo(suppliers.length);
        return suppliers[randomIndex].get();
    }

    public <ReturnType> ReturnType getRandomOf(final Collection<ReturnType> objects)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(objects);

        int randomIndex = randomIntGenerator.getRandomAbsIntTo(objects.size());
        for(ReturnType t: objects) if (--randomIndex < 0) return t;

        throw new RuntimeException("randomIndex > objects.size()");
    }

    public <ReturnType> ReturnType getRandomResult(final Collection<Supplier<ReturnType>> suppliers)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(suppliers);
        int randomIndex = randomIntGenerator.getRandomAbsIntTo(suppliers.size());
        for(Supplier<ReturnType> t: suppliers) if (--randomIndex < 0) return t.get();

        throw new RuntimeException("randomIndex > objects.size()");
    }
}
