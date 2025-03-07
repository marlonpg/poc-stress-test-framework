package com.gambasoftware.poc.stress.test.framework.interfaces;

import java.util.Map;

public interface Workload {
    void execute();
    Map<String, String> getParams();
}
