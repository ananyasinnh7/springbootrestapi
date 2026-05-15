package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.UserRequest;
import com.example.springbootrestapi.dto.UserResponse;
import com.example.springbootrestapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User REST Controller
 * 
 * Provides REST endpoints for user management operations.
 * All endpoints follow RESTful conventions.
 * 
 * Base URL: /api/users
 * 
 * @author Ananya
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    /**
     * Create a new user
     * POST /api/users
     * 
     * @param userRequest the user request DTO
     * @return ResponseEntity with created user and 201 status
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("POST request to create user");
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all users
     * GET /api/users
     * 
     * @return ResponseEntity with list of users and 200 status
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("GET request to fetch all users");
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by ID
     * GET /api/users/{id}
     * 
     * @param id the user ID
     * @return ResponseEntity with user and 200 status
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.info("GET request to fetch user with ID: {}", id);
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Update an existing user
     * PUT /api/users/{id}
     * 
     * @param id the user ID
     * @param userRequest the user request DTO
     * @return ResponseEntity with updated user and 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest) {
        log.info("PUT request to update user with ID: {}", id);
        UserResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a user
     * DELETE /api/users/{id}
     * 
     * @param id the user ID
     * @return ResponseEntity with 204 No Content status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("DELETE request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
