package com.example.springbootrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Entry Point
 * 
 * This class serves as the entry point for the Spring Boot application.
 * It enables auto-configuration and component scanning for the application.
 * 
 * @author Ananya
 * @version 1.0.0
 * @since 2026-05-15
 */
@SpringBootApplication
public class SpringBootRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApiApplication.class, args);
    }

}
