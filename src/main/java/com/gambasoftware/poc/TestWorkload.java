package com.gambasoftware.poc;

import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;

import java.util.HashMap;
import java.util.Map;

public class TestWorkload implements Workload {
    @Override
    public void execute() {
        System.out.println("Testing workload!!");
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<>();
    }
}
