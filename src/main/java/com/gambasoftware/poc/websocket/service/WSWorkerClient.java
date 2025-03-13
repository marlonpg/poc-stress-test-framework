package com.gambasoftware.poc.websocket.service;

import com.gambasoftware.poc.stress.test.framework.DefaultWorkload;
import com.gambasoftware.poc.websocket.model.Message;
import com.gambasoftware.poc.stress.test.framework.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WSWorkerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WSWorkerClient.class);

    @Value("${masterUrl}")
    private String masterUrl;
    private StompSession stompSession;

    public void connectToMaster() {
        // creating SockJs and WebSocket client
        List<Transport> transports = Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        //Configure the message converter
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        //Open connection with master
        String masterWebSocketUrl = "ws://localhost:8080/ws";
        stompClient.connectAsync(masterWebSocketUrl, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                LOGGER.info("Worker connected to master={}", masterUrl);
                stompSession = session;

                session.subscribe("/topic/workload", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return DefaultWorkload.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        DefaultWorkload workload = (DefaultWorkload) payload;
                        LOGGER.info("Worker received message={}", workload.toString());
                        WorkerService workerService = new WorkerService();
                        Map<String, Map<String, String>> metrics = workerService.start(workload);

                        sendMessageToMaster(metrics);

                    }
                });
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                LOGGER.error("WebSocket error", exception);
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                LOGGER.error("Transport error", exception);
            }
        });
    }
    public void sendMessageToMaster(Map<String, Map<String, String>> message) {
        if (stompSession != null && stompSession.isConnected()) {
            stompSession.send("/app/result", new Message(message));
            LOGGER.info("Worker sent message to master message={}", message);
        } else {
            LOGGER.info("Worker is not connected to the master.");
        }
    }
}