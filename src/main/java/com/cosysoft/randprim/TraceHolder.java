package com.cosysoft.randprim;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * todo: description
 */
interface TraceHolder<ValueType> extends TracingSettings {
    void saveTrace(Optional<String> label, ValueType value);
    Map<String, ValueType> getTracedValues();

    Optional<ValueType> getTracedValue(String label, int index);
    Collection<ValueType> getTracedValues(String label);
}
