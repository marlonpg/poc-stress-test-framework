package com.gambasoftware.poc.stress.test.framework.interfaces;

import java.util.Map;

public interface Workload {
    Map<String, String> execute();
    Map<String, String> getParams();
}
