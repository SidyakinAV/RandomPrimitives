package com.cosysoft.randprim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * todo: description
 */
public class RandomPrimitives implements
    RandomIntPrimitives<TraceAlternativeBuilder<Integer>>,
    RandomObject<Object, TraceBuilder<Object>>,
    Tracing
{
    private RandomPrimitivesWithTraceAlternativeImpl ints;
    private RandomObjectWithTraceImpl objects;

    public RandomPrimitives() {
        this.ints = new RandomPrimitivesWithTraceAlternativeImpl();
    }

    public RandomPrimitives(final Random randomGenerator) {
        this.ints = new RandomPrimitivesWithTraceAlternativeImpl(randomGenerator);
    }

    @Override
    public TraceAlternativeBuilder<Integer> getRandomAbsInt() {
        return this.ints.getRandomAbsInt();
    }

    @Override
    public TraceAlternativeBuilder<Integer> getRandomAbsIntTo(final int max) {
        return this.ints.getRandomAbsIntTo(max);
    }

    @Override
    public TraceAlternativeBuilder<Integer> getRandomAbsIntFrom(final int min) {
        return this.ints.getRandomAbsIntFrom(min);
    }

    @Override
    public TraceAlternativeBuilder<Integer> getRandomAbsIntFromTo(final int min, final int max) {
        return this.ints.getRandomAbsIntFromTo(min, max);
    }

    @Override
    public TraceAlternativeBuilder<Integer> getRandomAnyPossibleInt() {
        return this.ints.getRandomAnyPossibleInt();
    }

    @Override
    public TraceBuilder<Object> getRandomOf(final Object... objects) throws IllegalArgumentException {
        return this.objects.getRandomOf(objects);
    }

    @Override
    public TraceBuilder<Object> getRandomResult(final Supplier<Object>... suppliers)
        throws IllegalArgumentException
    {
        return this.objects.getRandomResult(suppliers);
    }

    @Override
    public TraceBuilder<Object> getRandomOf(final Collection<Object> objects)
        throws IllegalArgumentException
    {
        return this.objects.getRandomOf(objects);
    }

    @Override
    public TraceBuilder<Object> getRandomResult(final Collection<Supplier<Object>> suppliers)
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
}
