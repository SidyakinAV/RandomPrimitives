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
class TraceBuilder<Type> implements LazyBuilder<Type> {

    private Supplier<Type> supplier;

    @Getter(AccessLevel.PROTECTED)
    private TraceHolder<Type> traceHolder;

    @Setter(AccessLevel.PROTECTED)
    private String label = null;

    @Getter(AccessLevel.PROTECTED)
    private boolean isTraceIt = true;

    protected Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    TraceBuilder(@NonNull final Type alternative, @NonNull final TraceHolder<Type> traceHolder) {
        this.traceHolder = traceHolder;
        this.supplier = () -> alternative;
    }

    TraceBuilder(@NonNull final Supplier<Type> alternative, @NonNull final TraceHolder<Type> traceHolder) {
        this.traceHolder = traceHolder;
        this.supplier = alternative;
    }

    TraceBuilder(@NonNull final TraceHolder<Type> traceHolder) {
        this.traceHolder = traceHolder;
    }


    TraceBuilder<Type> traceAs(@NonNull final String label) {
        this.label = label;
        return this;
    }

    TraceBuilder<Type> isTraceIt(final boolean isTraceIt) {
        this.isTraceIt = isTraceIt;
        return this;
    }

    public Type get() {
        final Type value = this.supplier.get();
        if (this.isTraceIt) {
            this.traceHolder.saveTrace(this.getLabel(), value);
        }
        return value;
    }
}
