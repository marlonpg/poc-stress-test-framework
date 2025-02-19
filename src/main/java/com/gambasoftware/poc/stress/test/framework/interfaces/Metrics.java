package com.gambasoftware.poc.stress.test.framework.interfaces;

import java.util.Map;

public interface Metrics {
    void saveMetric(String metricName, String key, String value);
    Map<String, String> getMetrics(String metricName);
}
