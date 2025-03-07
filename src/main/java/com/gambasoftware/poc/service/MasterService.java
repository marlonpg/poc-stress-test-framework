package com.gambasoftware.poc.service;

import com.gambasoftware.poc.TestWorkload;
import com.gambasoftware.poc.stress.test.framework.interfaces.Workload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

    private final SimpMessagingTemplate messagingTemplate;

    public MasterService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(TestWorkload workload) {
        messagingTemplate.convertAndSend("/topic/workload", workload);
    }
}