package com.gambasoftware.poc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatusController {

    @Value("${app.mode}")
    private String nodeRole;

    @GetMapping("/node-status")
    public Map<String, String> getNodeStatus() {
        return Map.of(
                "status", "success",
                "nodeRole", nodeRole
        );
    }
}
