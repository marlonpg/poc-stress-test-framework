package com.gambasoftware.poc.websocket.model;

import java.util.Map;

public class Message {
    private String content;
    private Map<String, Map<String, String>> metrics;

    public Message() {}

    public Message(String content) {
        this.content = content;
    }

    public Message(Map<String, Map<String, String>> metrics) {
        this.metrics = metrics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Map<String, String>> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Map<String, String>> metrics) {
        this.metrics = metrics;
    }
}
