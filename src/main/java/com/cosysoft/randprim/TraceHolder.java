package com.cosysoft.randprim;

import java.util.Map;
import java.util.Optional;

/**
 * todo: description
 */
interface TraceHolder<ValueType> extends TracingSettings, TracedValuesGetter {
    void saveTrace(Optional<String> label, ValueType value);
    Map<String, ValueType> getTracedValues();
}
