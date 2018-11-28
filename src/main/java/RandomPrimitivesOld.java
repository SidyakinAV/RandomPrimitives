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