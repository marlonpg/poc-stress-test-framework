package com.gambasoftware.poc.websocket.service;

import com.gambasoftware.poc.stress.test.framework.DefaultWorkload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSMasterService {

    private final SimpMessagingTemplate messagingTemplate;

    public WSMasterService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(DefaultWorkload workload) {
        messagingTemplate.convertAndSend("/topic/workload", workload);
    }
}