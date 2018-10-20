package com.cosysoft.randprim;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * todo: description
 */
class TraceHolderImpl<ValueType> implements TraceHolder<ValueType> {

    @Getter
    private final Map<String, ValueType> tracedValues = new LinkedHashMap<>();

    @Setter
    @NonNull
    private String withoutLabelPrefix = "NO_LABEL_";

    @Setter
    @NonNull
    private String allLabelsPrefix = "";

    @Setter
    private boolean allowLabelsOverride = false;

    @Override
    public void saveTrace(final Optional<String> label, final ValueType value) {
        final String traceLabel = this.createTraceLabel(label);
        this.validateTraceLabel(traceLabel);
        this.tracedValues.put(traceLabel, value);
    }

    private void validateTraceLabel(final String traceLabel) {
        if (this.allowLabelsOverride) {
            return;
        }

        final boolean alreadyHaveThatLabel = this.tracedValues.containsKey(traceLabel);
        if (!alreadyHaveThatLabel) {
            return;
        }

        throw new IllegalStateException(
            "Label overriding detected. Please, check uniqueness of your labels "
                + "or allow label overriding by setAllowLabelsOverride(true)"
        );
    }

    private String createTraceLabel(final Optional<String> label) {
        final String traceLabel = label.orElseGet(() -> {
            final int noLabelIndex = this.tracedValues.size() + 1;
            return this.withoutLabelPrefix + noLabelIndex;
        });
        return this.allLabelsPrefix + traceLabel;
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final String label, final Class<ReturnType> type) {
        return Optional.ofNullable(
            this.tracedValues.get(
                this.createTraceLabel(Optional.of(label))
            )
        ).map(type::cast);
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final Integer index, final Class<ReturnType> type) {
        final Collection<ValueType> values = this.tracedValues.values();

        ValueType value = null;
        int i = index;
        for(ValueType v: values) if (--i < 0) value = v;

        return Optional.ofNullable(value).map(type::cast);
    }
}
