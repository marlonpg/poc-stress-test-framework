package com.gambasoftware.poc.stress.test.framework.interfaces;

import java.util.Map;

public interface StressTestGenerator {
    void start();
    void stop();
    void setWorkload(Workload workload);
    void setParallelThreads(int parallelThreads);
    Map<String, Map<String, String>> getMetrics();
    //todo add something to be able to add diff behaviors in part of the execution for observability or any other purpose
}