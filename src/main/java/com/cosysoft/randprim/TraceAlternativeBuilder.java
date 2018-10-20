package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
public class TraceAlternativeBuilder<ReturnType extends TraceType, TraceType> extends TraceBuilder<ReturnType, TraceType> {
    private AlternativeBuilder<ReturnType> alternativeBuilder;

    TraceAlternativeBuilder(final ReturnType alternative, TraceHolder<TraceType> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    TraceAlternativeBuilder(final Supplier<ReturnType> alternative, TraceHolder<TraceType> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> traceAs(@NonNull final String label) {
        this.setLabel(label);
        return this;
    }

    @Override
    public TraceAlternativeBuilder<ReturnType, TraceType> isTraceIt(final boolean isTraceIt) {
        super.isTraceIt(isTraceIt);
        return this;
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> or(final ReturnType alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> or(final ReturnType... alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> or(final Supplier<ReturnType> alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> orResult(final Supplier<ReturnType>... alternative) {
        this.alternativeBuilder.orResult(alternative);
        return this;
    }

    public TraceAlternativeBuilder<ReturnType, TraceType> orNull() {
        this.alternativeBuilder.orNull();
        return this;
    }

    public ReturnType get() {
        final ReturnType value = this.alternativeBuilder.get();
        if (this.isTraceIt()) {
            this.getTraceHolder().saveTrace(this.getLabel(), value);
        }
        return value;
    }
}
