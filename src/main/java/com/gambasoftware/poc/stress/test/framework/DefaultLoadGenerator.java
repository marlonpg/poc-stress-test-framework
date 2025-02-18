package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;
import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DefaultLoadGenerator implements StressTestGenerator {
    private final Logger logger = LoggerFactory.getLogger(DefaultLoadGenerator.class);
    private Workload workload;
    private ExecutorService executorService;
    private boolean isRunning = false;
    private long parallelThreads;

    @Override
    public void setWorkload(Workload workload) {
        this.workload = workload;
    }

    @Override
    public void setParallelThreads(long parallelThreads) {
        this.parallelThreads = parallelThreads;
    }

    @Override
    public void start() {
        logger.info("Test Start");
        if (workload == null) {
            throw new IllegalStateException("Workload must be set before starting the load generator.");
        }
        isRunning = true;
        executorService = Executors.newCachedThreadPool();


        for (int i = 0; i < parallelThreads; i++) {
            executorService.submit(() -> {
                while (isRunning) {
                    try {
                        workload.execute();
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