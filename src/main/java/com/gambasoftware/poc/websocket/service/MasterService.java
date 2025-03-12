package com.gambasoftware.poc.websocket.service;

import com.gambasoftware.poc.TestWorkload;
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