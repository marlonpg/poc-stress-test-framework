package com.gambasoftware.poc.websocket.controller;

import com.gambasoftware.poc.stress.test.framework.DefaultWorkload;
import com.gambasoftware.poc.websocket.service.WSMasterService;
import com.gambasoftware.poc.websocket.service.WSWorkerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestAPIController.class);

    @Value("${app.mode}")
    private String mode;

    @Autowired
    private WSMasterService WSMasterService;

    @Autowired(required = false)
    private WSWorkerClient WSWorkerClient;

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam String message) {
        LOGGER.info(Map.of("status", "success", "nodeRole", mode).toString());

        if ("worker".equals(mode)) {
            LOGGER.info("worker sending to master");
            WSWorkerClient.sendMessageToMaster(new HashMap<>());
        } else {
            LOGGER.info("master sending to worker");
            DefaultWorkload workload = new DefaultWorkload();
            WSMasterService.sendMessage(workload);
            LOGGER.info("master sending to worker finished");
        }
        return "Completed";
    }
}
