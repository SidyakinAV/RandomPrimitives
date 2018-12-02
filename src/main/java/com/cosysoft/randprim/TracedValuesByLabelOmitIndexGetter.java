package com.cosysoft.randprim;

import java.util.Optional;

/**
 * todo: description
 */
interface TracedValuesByLabelOmitIndexGetter extends TracedValuesByLabelWithIndexGetter {
    default <ReturnType> Optional<ReturnType> getTracedValue(String label, Class<ReturnType> type) {
        return this.getTracedValue(label, 0, type);
    }

    default <ReturnType> ReturnType getTracedValueOrDie(String label, Class<ReturnType> type) {
        final Optional<ReturnType> tracedValue = this.getTracedValue(label, type);
        return tracedValue.orElseThrow(() ->
            new IllegalStateException(
                String.format("There is no traced value for label '%s'", label)
            )
        );
    }
}
