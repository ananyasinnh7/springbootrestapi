package com.example.springbootrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller
 * 
 * Provides health check endpoint for monitoring and orchestration.
 * Used by Kubernetes liveness and readiness probes.
 * 
 * @author Ananya
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class HealthController {

    /**
     * Health check endpoint
     * GET /api/health
     * 
     * @return ResponseEntity with health status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        log.info("Health check endpoint called");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is running successfully");
        return ResponseEntity.ok(response);
    }

}
