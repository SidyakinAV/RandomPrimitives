package com.cosysoft.randprim;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * todo: description
 */
public abstract class BaseTrace<ValuesType> implements Tracing, TracedValuesGetter, GenericTracedValuesGetter<ValuesType> {
    @Getter(AccessLevel.PROTECTED)
    private final TraceHolder<ValuesType> traceHolder = new TraceHolderImpl<>();

    @Getter(AccessLevel.PROTECTED)
    private final TraceReporter traceReporter = new TraceReporterImpl(this.traceHolder);

    protected TraceBuilder<ValuesType, ValuesType> getBuilder(@NonNull final Supplier<ValuesType> randomIntSupplier) {
        final TraceBuilder<ValuesType, ValuesType> builder = new TraceBuilder<>(randomIntSupplier, this.traceHolder);
        return builder;
    }

    @Override
    public String getTracingReport() {
        return this.traceReporter.getTracingReport();
    }

    @Override
    public void setWithoutLabelPrefix(@NonNull final String prefix) {
        this.getTraceHolder().setWithoutLabelPrefix(prefix);
    }

    @Override
    public void setAllLabelsPrefix(@NonNull final String prefix) {
        this.getTraceHolder().setAllLabelsPrefix(prefix);
    }

    @Override
    public void setAllowLabelsOverride(final boolean isAllow) {
        this.getTraceHolder().setAllowLabelsOverride(isAllow);
    }

    @Override
    public Optional<ValuesType> getTracedValue(final String label, final int index) {
        return this.traceHolder.getTracedValue(label, index);
    }

    @Override
    public Collection<ValuesType> getTracedValues(final String label) {
        return this.traceHolder.getTracedValues(label);
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final String label, final int index, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValue(label, index).map(type::cast);
    }

    @Override
    public <ReturnType> Collection<ReturnType> getTracedValues(final String label, final Class<ReturnType> type) {
        return this.traceHolder.getTracedValues(label).stream().map(type::cast).collect(Collectors.toList());
    }
}
