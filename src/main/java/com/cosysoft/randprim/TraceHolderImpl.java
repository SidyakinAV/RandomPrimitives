package com.cosysoft.randprim;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * todo: description
 */
class TraceHolderImpl<ValueType> implements TraceHolder<ValueType> {

    @Getter
    private final Map<Pair<String, Long>, ValueType> tracedValues = new LinkedHashMap<>();

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
        this.tracedValues.put(this.createNewTraceKey(traceLabel), value);
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

    private Pair<String, Long> createNewTraceKey(final String label) {
        final long lastIndex = this.getLastIndex(label);
        return this.getTraceKey(label, lastIndex + 1);
    }

    private Pair<String, Long> getTraceKey(final String label, final long index) {
        final long lastIndex = this.getLastIndex(label);
        return Pair.of(label, lastIndex);
    }

    private long getLastIndex(@NonNull final String traceLabel) {
        return this.tracedValues
            .keySet()
            .stream()
            .map(Pair::getKey)
            .filter(s -> s.equals(traceLabel))
            .count();
    }

    @Override
    public Optional<ValueType> getTracedValue(final String label, final int index) {
        return Optional.ofNullable(
            this.tracedValues.get(
                this.getTraceKey(label, index)
            )
        );
    }

    @Override
    public Collection<ValueType> getTracedValues(final String label) {
        final long lastIndex = this.getLastIndex(label);
        final List<ValueType> collect = LongStream
            .range(0, lastIndex)
            .mapToObj(index -> this.getTraceKey(label, index))
            .map(this.tracedValues::get)
            .collect(Collectors.toList());
        return collect;
    }
}
