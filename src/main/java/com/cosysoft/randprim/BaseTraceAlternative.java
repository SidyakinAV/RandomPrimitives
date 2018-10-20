package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
public abstract class BaseTraceAlternative<ValuesType> extends BaseTrace<ValuesType> {

    protected TraceAlternativeBuilder<ValuesType, ValuesType> getBuilder(@NonNull final Supplier<ValuesType> randomIntSupplier) {
        final TraceAlternativeBuilder<ValuesType, ValuesType> builder = new TraceAlternativeBuilder<>(randomIntSupplier, this.getTraceHolder());
        return builder;
    }
}
