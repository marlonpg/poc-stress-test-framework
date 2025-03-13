package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DefaultWorkload implements Workload {
    private final Logger logger = LoggerFactory.getLogger(DefaultWorkload.class);

    private Map<String, String> metrics = new HashMap<>();

    @Override
    public Map<String, String> execute() {
        long startTime = System.currentTimeMillis();
        logger.info("startTime={}", startTime);

        double random = Math.random() * 1000;

        try {
            Thread.sleep((int) random);
            logger.info("random={}", random);
        } catch (InterruptedException e) {
            logger.error("Thread failed to sleep", e);
        }

        long endTime = System.currentTimeMillis();
        logger.info("endTime={}", endTime);
        long duration = endTime - startTime;
        logger.info("Execution took {}ms", duration);
        metrics.put("duration", String.valueOf(duration));
        return metrics;
    }

    @Override
    public Map<String, String> getParams() {
        return metrics;
    }
}
