package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.UserRequest;
import com.example.springbootrestapi.dto.UserResponse;
import com.example.springbootrestapi.entity.User;
import com.example.springbootrestapi.exception.ResourceNotFoundException;
import com.example.springbootrestapi.exception.ValidationException;
import com.example.springbootrestapi.mapper.UserMapper;
import com.example.springbootrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service
 * 
 * Contains business logic for user operations.
 * Handles validation, data transformation, and repository interactions.
 * 
 * @author Ananya
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Create a new user
     * 
     * @param userRequest the user request DTO
     * @return UserResponse DTO of created user
     * @throws ValidationException if email already exists
     */
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating user with email: {}", userRequest.getEmail());

        // Check if email already exists
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.warn("User with email {} already exists", userRequest.getEmail());
            throw new ValidationException("User with email " + userRequest.getEmail() + " already exists");
        }

        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }

    /**
     * Get all users
     * 
     * @return List of UserResponse DTOs
     */
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get user by ID
     * 
     * @param id the user ID
     * @return UserResponse DTO of the user
     * @throws ResourceNotFoundException if user not found
     */
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        log.info("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new ResourceNotFoundException("User not found with ID: " + id);
                });

        return userMapper.toResponse(user);
    }

    /**
     * Update an existing user
     * 
     * @param id the user ID
     * @param userRequest the user request DTO
     * @return UserResponse DTO of updated user
     * @throws ResourceNotFoundException if user not found
     * @throws ValidationException if new email already exists for another user
     */
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        log.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new ResourceNotFoundException("User not found with ID: " + id);
                });

        // Check if new email is being used by another user
        if (!user.getEmail().equals(userRequest.getEmail()) &&
                userRepository.existsByEmail(userRequest.getEmail())) {
            log.warn("User with email {} already exists", userRequest.getEmail());
            throw new ValidationException("User with email " + userRequest.getEmail() + " already exists");
        }

        User updatedUser = userMapper.updateEntity(userRequest, user);
        User savedUser = userRepository.save(updatedUser);

        log.info("User updated successfully with ID: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }

    /**
     * Delete a user
     * 
     * @param id the user ID
     * @throws ResourceNotFoundException if user not found
     */
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            log.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }

}
