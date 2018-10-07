package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
class TraceAlternativeBuilder<Type> extends TraceBuilder<Type> {
    private AlternativeBuilder<Type> alternativeBuilder;

    public TraceAlternativeBuilder(final Type alternative, TraceHolder<Type> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    TraceAlternativeBuilder(final Supplier<Type> alternative, TraceHolder<Type> traceHolder) {
        super(traceHolder);
        this.alternativeBuilder = new AlternativeBuilder<>(alternative);
    }

    TraceAlternativeBuilder<Type> trace(@NonNull final String label) {
        this.setLabel(label);
        return this;
    }

    TraceAlternativeBuilder<Type> or(final Type alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    TraceAlternativeBuilder<Type> or(final Supplier<Type> alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    TraceAlternativeBuilder<Type> or(final Type... alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    TraceAlternativeBuilder<Type> or(final Supplier<Type>... alternative) {
        this.alternativeBuilder.or(alternative);
        return this;
    }

    Type get() {
        final Type value = this.alternativeBuilder.get();
        if (this.isTraceIt()) {
            this.getTraceHolder().saveTrace(this.getLabel(), value);
        }
        return value;
    }
}
