package com.cosysoft.randprim;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * todo: description
 */
public class TraceBuilder<ReturnType extends TraceType, TraceType> implements LazyBuilder<ReturnType> {

    private Supplier<ReturnType> supplier;

    @Getter(AccessLevel.PROTECTED)
    private TraceHolder<TraceType> traceHolder;

    @Setter(AccessLevel.PROTECTED)
    private String label = null;

    @Getter(AccessLevel.PROTECTED)
    private boolean isTraceIt = true;

    protected Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    TraceBuilder(@NonNull final ReturnType alternative, @NonNull final TraceHolder<TraceType> traceHolder) {
        this.traceHolder = traceHolder;
        this.supplier = () -> alternative;
    }

    TraceBuilder(@NonNull final Supplier<ReturnType> alternative, @NonNull final TraceHolder<TraceType> traceHolder) {
        this.traceHolder = traceHolder;
        this.supplier = alternative;
    }

    TraceBuilder(@NonNull final TraceHolder<TraceType> traceHolder) {
        this.traceHolder = traceHolder;
    }


    public TraceBuilder<ReturnType, TraceType> traceAs(@NonNull final String label) {
        this.label = label;
        return this;
    }

    public TraceBuilder<ReturnType, TraceType> isTraceIt(final boolean isTraceIt) {
        this.isTraceIt = isTraceIt;
        return this;
    }

    public ReturnType get() {
        final ReturnType value = this.supplier.get();
        if (this.isTraceIt) {
            this.traceHolder.saveTrace(this.getLabel(), value);
        }
        return value;
    }
}
