package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

/**
 * todo: description
 */
public class RandomObjectWithTraceAlternativeImpl implements Tracing, TracedValuesGetter {

    private final RandomObjectImpl randomGenerator;
    private final TraceHolder<Object> traceHolder = new TraceHolderImpl<>();
    private final TraceReporter traceReporter = new TraceReporterImpl(this.traceHolder);

    public RandomObjectWithTraceAlternativeImpl() {
        this.randomGenerator = new RandomObjectImpl();
    }

    public RandomObjectWithTraceAlternativeImpl(@NonNull final Random randomGenerator) {
        this.randomGenerator = new RandomObjectImpl(randomGenerator);
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomOf(final ReturnType... objects)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<ReturnType, Object>(
            () -> this.randomGenerator.getRandomOf(objects),
            this.traceHolder
        );
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomResult(final Supplier<ReturnType>... suppliers)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<ReturnType, Object>(
            () -> this.randomGenerator.getRandomResult(suppliers),
            this.traceHolder
        );
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomOf(final Collection<ReturnType> objects)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<ReturnType, Object>(
            () -> this.randomGenerator.getRandomOf(objects),
            this.traceHolder
        );
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomResult(final Collection<Supplier<ReturnType>> suppliers)
        throws IllegalArgumentException
    {
        return new TraceAlternativeBuilder<ReturnType, Object>(
            () -> this.randomGenerator.getRandomResult(suppliers),
            this.traceHolder
        );
    }

    @Override
    public String getTracingReport() {
        return this.traceReporter.getTracingReport();
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

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final String label, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValue(label, type);
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final Integer index, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValue(index, type);
    }
}
