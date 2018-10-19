package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
public class TraceAlternativeBuilder<Type> extends TraceBuilder<Type> {
    private AlternativeBuilder<Type> alternativeBuilder;

    TraceAlternativeBuilder(final Type alternative, TraceHolder<Type> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    TraceAlternativeBuilder(final Supplier<Type> alternative, TraceHolder<Type> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    public TraceAlternativeBuilder<Type> traceAs(@NonNull final String label) {
        this.setLabel(label);
        return this;
    }

    @Override
    public TraceAlternativeBuilder<Type> isTraceIt(final boolean isTraceIt) {
        super.isTraceIt(isTraceIt);
        return this;
    }

    public TraceAlternativeBuilder<Type> or(final Type alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<Type> or(final Supplier<Type> alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<Type> or(final Type... alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public TraceAlternativeBuilder<Type> or(final Supplier<Type>... alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    public Type get() {
        final Type value = this.alternativeBuilder.get();
        if (this.isTraceIt()) {
            this.getTraceHolder().saveTrace(this.getLabel(), value);
        }
        return value;
    }
}
