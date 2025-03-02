package com.gambasoftware.poc.service;

import com.gambasoftware.poc.model.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

    private final SimpMessagingTemplate messagingTemplate;

    public MasterService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String message) {
        messagingTemplate.convertAndSend("/topic/workload", new Message("Master says: " + message));
    }
}