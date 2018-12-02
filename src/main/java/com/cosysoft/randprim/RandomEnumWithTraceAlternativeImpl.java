package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * todo: description
 */
public class RandomEnumWithTraceAlternativeImpl implements Tracing, TracedValuesGetter {
    private final RandomEnumImpl randomGenerator;
    private final TraceHolder<Enum> traceHolder = new TraceHolderImpl<>();
    private final TraceReporter traceReporter = new TraceReporterImpl(this.traceHolder);

    public RandomEnumWithTraceAlternativeImpl() {
        this.randomGenerator = new RandomEnumImpl();
    }

    public RandomEnumWithTraceAlternativeImpl(@NonNull final Random randomGenerator) {
        this.randomGenerator = new RandomEnumImpl(randomGenerator);
    }

    public <T extends Enum<T>> TraceAlternativeBuilder<T, Enum> getRandomEnum(@NonNull final Class<T> enumClass) {
        return new TraceAlternativeBuilder<T, Enum>(
            () -> this.randomGenerator.getRandomEnum(enumClass),
            this.traceHolder
        );
    }

    public <T extends Enum<T>> TraceAlternativeBuilder<T, Enum> getRandomEnum(@NonNull final T... enums)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<T, Enum>(
            () -> this.randomGenerator.getRandomEnum(enums),
            this.traceHolder
        );
    }

    public <T extends Enum<T>> TraceAlternativeBuilder<T, Enum> getRandomEnum(@NonNull final Collection<T> enums)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<T, Enum>(
            () -> this.randomGenerator.getRandomEnum(enums),
            this.traceHolder
        );
    }

    public <T extends Enum<T>> TraceAlternativeBuilder<T, Enum> getRandomEnumExceptFor(
        @NonNull final T... excludedEnums
    )
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<T, Enum>(
            () -> this.randomGenerator.getRandomEnumExceptFor(excludedEnums),
            this.traceHolder
        );
    }

    public <T extends Enum<T>> TraceAlternativeBuilder<T, Enum> getRandomEnumExceptFor(
        @NonNull final Collection<T> excludedEnums
    )
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<T, Enum>(
            () -> this.randomGenerator.getRandomEnumExceptFor(excludedEnums),
            this.traceHolder
        );
    }

    @Override
    public String getTracingReport() {
        return this.traceReporter.getTracingReport();
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final String label, final int index, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValue(label, index).map(type::cast);
    }

    @Override
    public <ReturnType> Collection<ReturnType> getTracedValues(final String label, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValues(label).stream().map(type::cast).collect(Collectors.toList());
    }

    @Override
    public void setWithoutLabelPrefix(final String prefix) {
        this.traceHolder.setWithoutLabelPrefix(prefix);
    }

    @Override
    public void setAllLabelsPrefix(final String prefix) {
        this.traceHolder.setAllLabelsPrefix(prefix);
    }

    @Override
    public void setAllowLabelsOverride(final boolean isAllow) {
        this.traceHolder.setAllowLabelsOverride(isAllow);
    }
}
