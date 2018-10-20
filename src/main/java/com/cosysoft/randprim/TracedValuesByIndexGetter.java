package com.cosysoft.randprim;

import java.util.Optional;

/**
 * todo: description
 */
interface TracedValuesByIndexGetter {
    <ReturnType> Optional<ReturnType> getTracedValue(Integer index, Class<ReturnType> type);

    default <ReturnType> ReturnType getTracedValueOrDie(Integer index, Class<ReturnType> type) {
        final Optional<ReturnType> tracedValue = this.getTracedValue(index, type);
        return tracedValue.orElseThrow(() -> new IllegalStateException("There is no traced value for index " + index));
    }
}
