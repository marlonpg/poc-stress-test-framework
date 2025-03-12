package com.gambasoftware.poc.websocket.controller;

import com.gambasoftware.poc.TestWorkload;
import com.gambasoftware.poc.websocket.service.MasterService;
import com.gambasoftware.poc.websocket.service.WorkerWebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatusController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @Value("${app.mode}")
    private String mode;

    @Autowired
    private MasterService masterService;

    @Autowired(required = false)
    private WorkerWebSocketClient workerWebSocketClient;

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam String message) {
        LOGGER.info(Map.of("status", "success", "nodeRole", mode).toString());

        if ("worker".equals(mode)) {
            LOGGER.info("worker sending to master");
            workerWebSocketClient.sendMessageToMaster(message);
        } else {
            LOGGER.info("master sending to worker");
            TestWorkload workload = new TestWorkload();
            masterService.sendMessage(workload);
            LOGGER.info("master sending to worker finished");
        }
        return "Completed";
    }
}
