package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;

public class TestScenario {
    private Workload workload;
    private int numberOfUsers;
    private long durationInSeconds;

    public Workload getWorkload() {
        return workload;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public long getDurationInSeconds() {
        return durationInSeconds;
    }

    public static final class TestScenarioBuilder {
        private Workload workload;
        private int numberOfUsers;
        private long durationInSeconds;

        private TestScenarioBuilder() {
        }

        public static TestScenarioBuilder aTestScenario() {
            return new TestScenarioBuilder();
        }

        public TestScenarioBuilder withWorkload(Workload workload) {
            this.workload = workload;
            return this;
        }

        public TestScenarioBuilder withNumberOfUsers(int numberOfUsers) {
            this.numberOfUsers = numberOfUsers;
            return this;
        }

        public TestScenarioBuilder withDurationInSeconds(long durationInSeconds) {
            this.durationInSeconds = durationInSeconds;
            return this;
        }

        public TestScenario build() {
            TestScenario testScenario = new TestScenario();
            testScenario.workload = this.workload;
            testScenario.numberOfUsers = this.numberOfUsers;
            testScenario.durationInSeconds = this.durationInSeconds;
            return testScenario;
        }
    }
}
