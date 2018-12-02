package com.cosysoft.randprim;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
public abstract class BaseTrace<ValuesType> implements Tracing, TracedValuesGetter {
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
}
