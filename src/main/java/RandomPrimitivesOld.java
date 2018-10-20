import lombok.NonNull;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, который генерирует простые рандомные значения для тестов.
 */
public final class RandomPrimitivesOld {

    /**
     * Генератор случайных чисел.
     */
    private static final ThreadLocalRandom RANDOM =
            ThreadLocalRandom.current();

    /**
     * Нет необходимости в конструкторах.
     */
    private RandomPrimitivesOld() {
    }

    /**
     * @param objects объекты для случайного выбора.
     * @param <ObjectsType> тип объектов.
     * @return случайный объект из заданных объектов.
     * @throws IllegalArgumentException если objects пуст.
     */
    public static <ObjectsType> ObjectsType getRandomOf(final ObjectsType... objects)
            throws IllegalArgumentException {
        RandomPrimitivesOld.validateVarArgNotEmpty(objects);
        final Integer randomIndex = RandomPrimitivesOld.getRandomAbsIntTo(objects.length);
        return objects[randomIndex];
    }

    /**
     * @param max максимальное возвращаемое значение.
     * @return случайное положительное целое число, 0 <= x < max.
     */
    public static Integer getRandomAbsIntTo(final int max) {
        return Math.abs(RANDOM.nextInt(max));
    }

    /**
     * @param min минимальное возвращаемое значение.
     * @param max верхняя граница возвращаемого значения.
     * @return случайное положительное целое число, min <= x < max.
     */
    public static Integer getRandomAbsInt(final int min, final int max) {
        return Math.abs(RANDOM.nextInt(min, max));
    }

    /**
     * @param min минимальное возвращаемое значение.
     * @return случайное положительное целое число, min <= x < {@link Integer#MAX_VALUE}.
     */
    public static Integer getRandomAbsIntFrom(final int min) {
        return getRandomAbsInt(min, Integer.MAX_VALUE);
    }

    /**
     * @param existingInts коллекция с уже существующими числами.
     * @param max          верхняя граница возвращаемого значения.
     * @return случайный положительное целое число 0 <= x < max, отсутствующее в заданной коллекции.
     * Возвращаемое значение также автоматически добавляется в existingInts.
     */
    public static Integer getNewRandomInt(final Collection<Integer> existingInts, final Integer max) {
        if (max == existingInts.size()) {
            throw new RuntimeException("Использованы все возможные значения в заданном диапазоне.");
        }

        Integer newInt;
        do {
            newInt = RandomPrimitivesOld.getRandomAbsIntTo(max);
        } while (existingInts.contains(newInt));
        existingInts.add(newInt);

        return newInt;
    }

    /**
     * @return случайный положительный Short.
     */
    public static Short getRandomShort() {
        return (short) Math.abs(RANDOM.nextInt(Short.MAX_VALUE + 1));
    }

    /**
     * @param max максимальное возвращаемое значение.
     * @return случайное число, 0 <= x < max.
     */
    public static Short getRandomShort(final Short max) {
        return (short) Math.abs(RANDOM.nextInt(max));
    }

    /**
     * @return случайный положительный Float.
     */
    public static Float getRandomFloat() {
        return Math.abs(RANDOM.nextFloat());
    }

    /**
     * @return случайный положительный Double.
     */
    public static Double getRandomDouble() {
        return Math.abs(RANDOM.nextDouble());
    }

    /**
     * @param max      верхняя граница возвращаемого значения.
     * @param accuracy точность возвращаемого значения.
     * @return случайный положительный Double, 0 <= x < max.
     */
    public static Double getRandomDouble(final double max,
                                         final int accuracy) {
        final Double randomValue = Math.abs(RANDOM.nextDouble(max));
        return new BigDecimal(randomValue).setScale(accuracy, RoundingMode.UP).doubleValue();
    }

    /**
     * @return случайный положительный Long.
     */
    public static Long getRandomLong() {
        return Math.abs(RANDOM.nextLong());
    }

    /**
     * @param max максимальное возвращаемое значение.
     * @return случайное число, 0 <= x < max.
     */
    public static Long getRandomLong(final Long max) {
        return Math.abs(RANDOM.nextLong(max));
    }

    /**
     * @return случайная строка.
     */
    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

    /**
     * @param length длина желаемой строки.
     * @return случайная строка.
     */
    public static String getRandomString(final int length) {
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                        .build();
        final String randomString = randomStringGenerator.generate(length);
        return randomString;
    }

    /**
     * @return случайный Boolean.
     */
    public static Boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * @param enumClass класс enum-а.
     * @param <T>       тип enum-а.
     * @return случайная enum-константа заданного типа.
     */
    public static <T extends Enum<T>> T getRandomEnum(@NonNull final Class<T> enumClass) {
        int i = RandomPrimitivesOld.getRandomAbsIntTo(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[i];
    }

    /**
     * @param enums множество enum-констант.
     * @param <T>   тип enum-а.
     * @return случайная enum-константа из заданного набора.
     * @throws IllegalArgumentException если enums пуст.
     */
    public static <T extends Enum<T>> T getRandomEnum(@NonNull final T... enums)
            throws IllegalArgumentException {
        RandomPrimitivesOld.validateVarArgNotEmpty(enums);
        final T randomEnum = RandomPrimitivesOld.getRandomEnum(new HashSet<>(Arrays.asList(enums)));
        return randomEnum;
    }

    /**
     * @param enums коллекция enum-констант.
     * @param <T>   тип enum-а.
     * @return случайная enum-константа из заданного набора.
     */
    public static <T extends Enum<T>> T getRandomEnum(@NonNull final Collection<T> enums) {
        int i = RandomPrimitivesOld.getRandomAbsIntTo(enums.size());
        return enums.stream().skip(i).findFirst().get();
    }

    /**
     * @param enums enum-константы.
     * @param <T>   тип enum-а.
     * @return случайная enum-константа из заданного набора.
     * @throws IllegalArgumentException если enums пуст.
     */
    public static <T extends Enum<T>> T getRandomEnum(@NonNull final Set<T> enums)
            throws IllegalArgumentException {
        int i = RandomPrimitivesOld.getRandomAbsIntTo(enums.size());
        return new ArrayList<>(enums).get(i);
    }


    /**
     * @param excludedEnumsArray множество enum констант, которые метод возвращать не будет.
     * @param <T>                тип enum-а.
     * @return случайная enum-константа заданного типа, кроме заданных.
     * @throws IllegalArgumentException если excludedEnumsArray пуст или если кол-во элментов в нем
     *                                  совпадает с количеством enum-констант.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T getRandomEnumExceptFor(@NonNull final T... excludedEnumsArray)
            throws IllegalArgumentException {
        RandomPrimitivesOld.validateVarArgNotEmpty(excludedEnumsArray);
        final T randomEnum = RandomPrimitivesOld.getRandomEnumExceptFor(new HashSet<>(Arrays.asList(excludedEnumsArray)));
        return randomEnum;
    }

    /**
     * @param excludedEnumsCollection enum константы, которые метод возвращать не будет.
     * @param <T>                     тип enum-а.
     * @return случайная enum-константа заданного типа, кроме заданных.
     * @throws IllegalArgumentException если excludedEnumsArray пуст или если кол-во элментов в нем
     *                                  совпадает с количеством enum-констант.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T getRandomEnumExceptFor(@NonNull final Set<T> excludedEnumsCollection)
            throws IllegalArgumentException {
        if (excludedEnumsCollection.isEmpty()) {
            throw new IllegalArgumentException("enum collection is empty");
        }
        final T enumObject = excludedEnumsCollection.stream().findFirst().get();
        final Class<T> enumClass = (Class<T>) enumObject.getClass();

        final T[] allEnums = enumClass.getEnumConstants();
        if (allEnums.length == excludedEnumsCollection.size()) {
            throw new IllegalArgumentException("You exclude all enums");
        }

        final List<T> includedEnums = Stream.of(allEnums)
                .filter(e -> !excludedEnumsCollection.contains(e))
                .collect(Collectors.toList());
        int i = RandomPrimitivesOld.getRandomAbsIntTo(includedEnums.size());
        return includedEnums.get(i);
    }

    /**
     * Проверят, что varargs-параметры не пусты.
     * @param params массив каких либо объектов.
     * @throws IllegalArgumentException при провале валидации.
     */
    private static void validateVarArgNotEmpty(final Object[] params) throws IllegalArgumentException {
        if (params.length == 0) {
            throw new IllegalArgumentException("you must provide not empty list of parameters");
        }
    }
}