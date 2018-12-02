package com.cosysoft.randprim;

import java.util.Collection;
import java.util.Optional;

/**
 * todo: description
 */
interface TracedValuesByLabelWithIndexGetter {
    <ReturnType> Optional<ReturnType> getTracedValue(String label, int index, Class<ReturnType> type);
    <ReturnType> Collection<ReturnType> getTracedValues(String label, Class<ReturnType> type);

    default <ReturnType> ReturnType getTracedValueOrDie(String label, int index, Class<ReturnType> type) {
        final Optional<ReturnType> tracedValue = this.getTracedValue(label, index, type);
        return tracedValue.orElseThrow(() -> new IllegalStateException("There is no traced value for index " + index));
    }
}
