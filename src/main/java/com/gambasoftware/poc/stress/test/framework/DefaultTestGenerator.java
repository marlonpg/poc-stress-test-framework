package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;
import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DefaultTestGenerator implements StressTestGenerator {
    private final Logger logger = LoggerFactory.getLogger(DefaultTestGenerator.class);
    private Workload workload;
    private ExecutorService executorService;
    private boolean isRunning = false;
    private int parallelThreads;
    private Map<String, Map<String, String>> metricsResult = new ConcurrentHashMap<>();

    @Override
    public void setWorkload(Workload workload) {
        this.workload = workload;
    }

    @Override
    public void setParallelThreads(int parallelThreads) {
        this.parallelThreads = parallelThreads;
    }

    @Override
    public Map<String, Map<String, String>> getMetrics() {
        return metricsResult;
    }

    @Override
    public void start() {
        logger.info("com.gambasoftware.poc.Test Start");
        if (workload == null) {
            throw new IllegalStateException("Workload must be set before starting the load generator.");
        }
        metricsResult = new ConcurrentHashMap<>();
        isRunning = true;
        executorService = Executors.newFixedThreadPool(parallelThreads);


        for (int i = 0; i < parallelThreads; i++) {
            int finalI = i;
            executorService.submit(() -> {
                while (isRunning) {
                    try {
                        long started = System.currentTimeMillis();
                        workload.execute();
                        metricsResult.put(started + " | " + Thread.currentThread().getName(), workload.getParams());
                    } catch (Exception e) {
                        logger.error("Error during execution of the test.", e);
                    }
                }
            });
        }
    }

    @Override
    public void stop() {
        isRunning = false;
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}