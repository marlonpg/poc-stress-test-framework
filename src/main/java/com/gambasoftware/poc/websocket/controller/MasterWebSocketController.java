package com.gambasoftware.poc.websocket.controller;

import com.gambasoftware.poc.websocket.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MasterWebSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterWebSocketController.class);


    @MessageMapping("/result")
    @SendTo("/topic/master-messages")
    public String handleWorkerMessage(Message message) {
       LOGGER.info("Master received message from worker with content={} ", message.getContent());
        return "Master received: " + message.getContent();
    }
}