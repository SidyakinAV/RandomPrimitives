package com.cosysoft.randprim;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * todo: description
 */
public abstract class BaseTraceAlternative<ValuesType> extends BaseTrace<ValuesType> {

    protected TraceAlternativeBuilder<ValuesType> getBuilder(@NonNull final Supplier<ValuesType> randomIntSupplier) {
        final TraceAlternativeBuilder<ValuesType> builder = new TraceAlternativeBuilder<ValuesType>(randomIntSupplier, this.getTraceHolder());
        return builder;
    }
}
