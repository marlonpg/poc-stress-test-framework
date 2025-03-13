package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.Metrics;
import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;

public class TestRunner {
    private StressTestGenerator stressTestGenerator;

    public void run(TestScenario scenario) {
        //todo adding logs and observability

        stressTestGenerator.setWorkload(scenario.getWorkload());
        stressTestGenerator.setParallelThreads(scenario.getNumberOfUsers());
        stressTestGenerator.start();

        try {
            Thread.sleep(scenario.getDurationInSeconds() * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        stressTestGenerator.stop();

        //todo get collected metrics and generate a report
    }

    public static final class TestRunnerBuilder {
        private StressTestGenerator stressTestGenerator;
        private Metrics metrics;

        private TestRunnerBuilder(StressTestGenerator stressTestGenerator) {
            this.stressTestGenerator = stressTestGenerator;
        }

        public static TestRunnerBuilder aTestRunner(StressTestGenerator stressTestGenerator) {
            return new TestRunnerBuilder(stressTestGenerator);
        }

        public TestRunnerBuilder withStressTestGenerator(StressTestGenerator stressTestGenerator) {
            this.stressTestGenerator = stressTestGenerator;
            return this;
        }

        public TestRunner build() {
            TestRunner testRunner = new TestRunner();
            testRunner.stressTestGenerator = this.stressTestGenerator;
            return testRunner;
        }
    }
}
