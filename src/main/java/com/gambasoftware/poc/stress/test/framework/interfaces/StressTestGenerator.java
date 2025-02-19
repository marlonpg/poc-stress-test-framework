package com.gambasoftware.poc.stress.test.framework.interfaces;

public interface StressTestGenerator {
    void start();
    void stop();
    void setWorkload(Workload workload);
    void setParallelThreads(int parallelThreads);
    //todo add something to be able to add diff behaviors in part of the execution for observability or any other purpose
}