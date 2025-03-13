package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;

import java.util.Map;

public class WorkerService {
    public Map<String, Map<String, String>> start(DefaultWorkload workload){
        //2. Define the com.gambasoftware.poc.Test scenario which is basically the number of parallel users you want to simulate
        TestScenario scenario = TestScenario.TestScenarioBuilder.aTestScenario()
                .withWorkload(workload)
                //TODO get this data from workflow or change the model to have more info
                .withDurationInSeconds(3)
                //TODO get this data from workflow or change the model to have more info
                .withNumberOfUsers(2)
                .build();

        //3. Define the Generator you want to use
        StressTestGenerator loadGenerator = new DefaultTestGenerator();

        TestRunner runner = TestRunner.TestRunnerBuilder.aTestRunner(loadGenerator).build();
        runner.run(scenario);

        return loadGenerator.getMetrics();
    }
}
