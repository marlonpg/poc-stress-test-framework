package com.gambasoftware.poc.stress.test.framework;

import com.gambasoftware.poc.TestWorkload;
import com.gambasoftware.poc.stress.test.framework.interfaces.StressTestGenerator;
import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;

public class WorkerService {
    public void start(TestWorkload workload){
        //2. Define the com.gambasoftware.poc.Test scenario which is basically the number of parallel users you want to simulate
        TestScenario scenario = TestScenario.TestScenarioBuilder.aTestScenario()
                .withWorkload(workload)
                //TODO get this data from workflow or change the model to have more info
                .withDurationInSeconds(10)
                //TODO get this data from workflow or change the model to have more info
                .withNumberOfUsers(2)
                .build();

        //3. Define the Generator you want to use
        StressTestGenerator loadGenerator = new DefaultTestGenerator();

        TestRunner runner = TestRunner.TestRunnerBuilder.aTestRunner(loadGenerator).build();
        runner.run(scenario);
    }
}
