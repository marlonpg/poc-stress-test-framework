package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.Metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMetrics implements Metrics {

    private final Map<String, Map<String, String>> metricsMap = new ConcurrentHashMap<>();

    @Override
    public void saveMetric(String metricName, String key, String value) {
        Map<String, String> map = metricsMap.get(metricName);
        if(map !=null) {
            map.put(key, value);
        } else {
            Map<String, String> keyValue = new HashMap<>();
            keyValue.put(key, value);
            metricsMap.put(metricName, keyValue);
        }
    }

    @Override
    public Map<String, String> getMetrics(String metricName) {
        return metricsMap.get(metricName);
    }
}
