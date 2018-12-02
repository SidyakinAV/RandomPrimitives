package com.cosysoft.randprim;

import java.util.Collection;
import java.util.Optional;

/**
 * todo: description
 */
public interface GenericTracedValuesGetter<ValueType> {
    Optional<ValueType> getTracedValue(String label, int index);
    Collection<ValueType> getTracedValues(String label);
}
