package com.cosysoft.randprim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * todo: description
 */
public class RandomPrimitivesImpl implements
    RandomIntPrimitives<TraceAlternativeBuilder<Integer, Integer>>,
    Tracing,
    TracedValuesByLabelOmitIndexGetter
{
    private RandomIntPrimitivesWithTraceAlternativeImpl ints;
    private RandomObjectWithTraceAlternativeImpl objects;

    public RandomPrimitivesImpl() {
        this.ints = new RandomIntPrimitivesWithTraceAlternativeImpl();
        this.objects = new RandomObjectWithTraceAlternativeImpl();
    }

    public RandomPrimitivesImpl(final Random randomGenerator) {
        this.ints = new RandomIntPrimitivesWithTraceAlternativeImpl(randomGenerator);
    }

    @Override
    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsInt() {
        return this.ints.getRandomAbsInt();
    }

    @Override
    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntTo(final int max) {
        return this.ints.getRandomAbsIntTo(max);
    }

    @Override
    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntFrom(final int min) {
        return this.ints.getRandomAbsIntFrom(min);
    }

    @Override
    public TraceAlternativeBuilder<Integer, Integer> getRandomAbsIntFromTo(final int min, final int max) {
        return this.ints.getRandomAbsIntFromTo(min, max);
    }

    @Override
    public TraceAlternativeBuilder<Integer, Integer> getRandomAnyPossibleInt() {
        return this.ints.getRandomAnyPossibleInt();
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomOf(final ReturnType... objects) throws IllegalArgumentException {
        return this.objects.getRandomOf(objects);
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomResult(final Supplier<ReturnType>... suppliers)
        throws IllegalArgumentException
    {
        return this.objects.getRandomResult(suppliers);
    }

    public <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomOf(final Collection<ReturnType> objects)
        throws IllegalArgumentException
    {
        return this.objects.getRandomOf(objects);
    }

    public  <ReturnType> TraceAlternativeBuilder<ReturnType, Object> getRandomResult(final Collection<Supplier<ReturnType>> suppliers)
        throws IllegalArgumentException
    {
        return this.objects.getRandomResult(suppliers);
    }



    @Override
    public String getTracingReport() {
        final String report = this.getTraceReporters().stream()
            .map(TraceReporter::getTracingReport)
            .reduce((s1, s2) -> s1 + s2)
            .get();
        return report;
    }

    @Override
    public void setWithoutLabelPrefix(final String prefix) {
        this.getTracingSettingsHolders().forEach(
            tracingSettings -> tracingSettings.setWithoutLabelPrefix(prefix)
        );
    }

    @Override
    public void setAllLabelsPrefix(final String prefix) {
        this.getTracingSettingsHolders().forEach(
            tracingSettings -> tracingSettings.setAllLabelsPrefix(prefix)
        );
    }

    @Override
    public void setAllowLabelsOverride(final boolean isAllow) {
        this.getTracingSettingsHolders().forEach(
            tracingSettings -> tracingSettings.setAllowLabelsOverride(isAllow)
        );
    }

    private List<TraceReporter> getTraceReporters() {
        final ArrayList<TraceReporter> traceReporters = new ArrayList<>();
        traceReporters.add(this.ints);
        traceReporters.add(this.objects);
        return traceReporters;
    }

    private List<TracingSettings> getTracingSettingsHolders() {
        final ArrayList<TracingSettings> tracingSettingsHolders = new ArrayList<>();
        tracingSettingsHolders.add(this.ints);
        tracingSettingsHolders.add(this.objects);
        return tracingSettingsHolders;
    }

    @Override
    public <ReturnType> Optional<ReturnType> getTracedValue(final String label, final int index, final Class<ReturnType> type) {
        return this.getPresentTracedValuesStream(label, index, type)
            .reduce((a, b) -> {
                throw new IllegalStateException(
                    String.format("Multiple values are traced with label: '%s'."
                        + " You must fix labels duplication", label)
                );
            })
            .orElse(Optional.empty());
    }

    private <ReturnType> Stream<Optional<ReturnType>> getPresentTracedValuesStream(final String label,
                                                                                   final int index,
                                                                                   final Class<ReturnType> type)
    {
        final Optional<ReturnType> tracedValueFromObjects = this.objects.getTracedValue(label, index, type);
        final Optional<ReturnType> tracedValueFromInts = this.ints.getTracedValue(label, index, type);
        return Stream.of(
            tracedValueFromObjects,
            tracedValueFromInts
        )
            .filter(Optional::isPresent);
    }
}
