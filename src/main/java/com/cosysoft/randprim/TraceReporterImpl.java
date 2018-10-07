package com.cosysoft.randprim;

import lombok.NonNull;

/**
 * todo: description
 */
public class TraceReporterImpl implements TraceReporter {
    private TraceHolder<?> traceHolder;
    public TraceReporterImpl(@NonNull final TraceHolder<?> traceHolder) {
        this.traceHolder = traceHolder;
    }

    @Override
    public String getTracingReport() {
        final StringBuilder reportBuilder = new StringBuilder();
        traceHolder.getTracedValues().forEach((key, value) ->
            reportBuilder.append(key + ": " + value + "\n")
        );
        return reportBuilder.toString();
    }
}
