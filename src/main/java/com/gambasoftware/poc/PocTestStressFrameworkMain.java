package com.gambasoftware.poc;

import com.gambasoftware.poc.stress.test.framework.DefaultTestGenerator;
import com.gambasoftware.poc.stress.test.framework.TestRunner;
import com.gambasoftware.poc.stress.test.framework.TestScenario;
import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PocTestStressFrameworkMain {
    private static final Logger logger = LogManager.getLogger(PocTestStressFrameworkMain.class);
    public static void main(String[] args) {

        //1. Define the workload which is basically whatever you want to stress test.
        TestWorkload workload = new TestWorkload();

        //2. Define the com.gambasoftware.poc.Test scenario which is basically the number of parallel users you want to simulate
        TestScenario scenario = TestScenario.TestScenarioBuilder.aTestScenario()
                .withWorkload(workload)
                .withDurationInSeconds(10)
                .withNumberOfUsers(2)
                .build();

        //3. Define the Generator you want to use
        StressTestGenerator loadGenerator = new DefaultTestGenerator();

        TestRunner runner = TestRunner.TestRunnerBuilder.aTestRunner(loadGenerator).build();
        runner.run(scenario);
    }
}
