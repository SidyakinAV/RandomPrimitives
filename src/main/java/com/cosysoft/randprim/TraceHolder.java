package com.cosysoft.randprim;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Optional;

/**
 * todo: description
 */
interface TraceHolder<ValueType> extends TracingSettings, GenericTracedValuesGetter<ValueType> {
    void saveTrace(Optional<String> label, ValueType value);
    Map<Pair<String, Long>, ValueType> getTracedValues();
}
