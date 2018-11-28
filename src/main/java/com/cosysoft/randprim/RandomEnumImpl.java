package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Chooses random enum-constants.
 */
public class RandomEnumImpl {
    private final RandomIntPrimitives<Integer> randomIntGenerator;

    public RandomEnumImpl() {
        this.randomIntGenerator = new RandomIntPrimitivesImpl();
    }
    public RandomEnumImpl(final Random randomGenerator) {
        this.randomIntGenerator = new RandomIntPrimitivesImpl(randomGenerator);
    }

    /**
     * @param enumClass enum's class.
     * @param <T> enum's generic type.
     * @return random enum-constant of provided type.
     * @throws IllegalArgumentException if enumClass has no enum constants.
     */
    public <T extends Enum<T>> T getRandomEnum(@NonNull final Class<T> enumClass) {
        final int amount = enumClass.getEnumConstants().length;
        if (amount < 1) {
            throw new IllegalArgumentException("There is no enum constants in " + enumClass);
        }
        int i = this.randomIntGenerator.getRandomAbsIntTo(amount);
        return enumClass.getEnumConstants()[i];
    }

    /**
     * @param enums array of enum constants.
     * @param <T> enum's generic type.
     * @return random enum-constant from provided array.
     * @throws IllegalArgumentException if enums is empty.
     */
    public <T extends Enum<T>> T getRandomEnum(@NonNull final T... enums)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(enums);
        final T randomEnum = this.getRandomEnum(new HashSet<>(Arrays.asList(enums)));
        return randomEnum;
    }

    /**
     * @param enums collection of enum constants.
     * @param <T> enum's generic type.
     * @return random enum-constant from provided collection.
     * @throws IllegalArgumentException if enums is empty.
     */
    public <T extends Enum<T>> T getRandomEnum(@NonNull final Collection<T> enums)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(enums);
        int i = this.randomIntGenerator.getRandomAbsIntTo(enums.size());
        return new ArrayList<>(enums).get(i);
    }

    /**
     * @param excludedEnums array of enum constants, that will not be returned by method.
     * @param <T> enum's generic type.
     * @return random enum-constant except for provided.
     * @throws IllegalArgumentException if excludedEnums is empty
     * or if excludedEnums contains all possible enum constants.
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getRandomEnumExceptFor(@NonNull final T... excludedEnums)
        throws IllegalArgumentException
    {
        VarArgsValidator.validateVarArgNotEmpty(excludedEnums);
        final T randomEnum = this.getRandomEnumExceptFor(new HashSet<>(Arrays.asList(excludedEnums)));
        return randomEnum;
    }

    /**
     * @param excludedEnums array of enum constants, that will not be returned by method.
     * @param <T> enum's generic type.
     * @return random enum-constant except for provided.
     * @throws IllegalArgumentException if excludedEnums is empty
     * or if excludedEnums contains all possible enum constants.
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getRandomEnumExceptFor(@NonNull final Collection<T> excludedEnums)
        throws IllegalArgumentException
    {
        final HashSet<T> excludedEnumsSet = new HashSet<>(excludedEnums);
        if (excludedEnumsSet.isEmpty()) {
            throw new IllegalArgumentException("enum collection is empty");
        }
        final T enumObject = excludedEnumsSet.stream().findFirst().get();
        final Class<T> enumClass = (Class<T>) enumObject.getClass();

        final T[] allEnums = enumClass.getEnumConstants();
        if (allEnums.length == excludedEnumsSet.size()) {
            throw new IllegalArgumentException("You exclude all enums");
        }

        final List<T> includedEnums = Stream.of(allEnums)
            .filter(e -> !excludedEnumsSet.contains(e))
            .collect(Collectors.toList());
        int i = this.randomIntGenerator.getRandomAbsIntTo(includedEnums.size());
        return includedEnums.get(i);
    }
}
