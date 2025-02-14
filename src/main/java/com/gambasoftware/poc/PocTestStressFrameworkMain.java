package com.gambasoftware.poc;

import com.gambasoftware.poc.stress.test.framework.DefaultLoadGenerator;
import com.gambasoftware.poc.stress.test.framework.TestRunner;
import com.gambasoftware.poc.stress.test.framework.TestScenario;
import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;
import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PocTestStressFrameworkMain {
    private static final Logger logger = LogManager.getLogger(PocTestStressFrameworkMain.class);
    public static void main(String[] args) {
        Workload workload = () -> {
            logger.info("Calling a API or any other thing...");
        };

        TestScenario scenario = TestScenario.TestScenarioBuilder.aTestScenario()
                .withWorkload(workload)
                .withDurationInSeconds(111)
                .withNumberOfUsers(1)
                .build();

        StressTestGenerator loadGenerator = new DefaultLoadGenerator();

        TestRunner runner = TestRunner.TestRunnerBuilder.aTestRunner(loadGenerator).build();
        runner.run(scenario);
    }
}
