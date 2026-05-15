package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.dto.UserRequest;
import com.example.springbootrestapi.dto.UserResponse;
import com.example.springbootrestapi.entity.User;
import org.springframework.stereotype.Component;

/**
 * User Mapper
 * 
 * Converts between User Entity and DTOs.
 * Handles data transformation for API requests and responses.
 * 
 * @author Ananya
 * @version 1.0.0
 */
@Component
public class UserMapper {

    /**
     * Convert UserRequest DTO to User Entity
     * 
     * @param userRequest the request DTO
     * @return User entity
     */
    public User toEntity(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());

        return user;
    }

    /**
     * Convert User Entity to UserResponse DTO
     * 
     * @param user the user entity
     * @return UserResponse DTO
     */
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    /**
     * Update existing User Entity with data from UserRequest DTO
     * 
     * @param userRequest the request DTO
     * @param user the existing user entity
     * @return updated User entity
     */
    public User updateEntity(UserRequest userRequest, User user) {
        if (userRequest == null) {
            return user;
        }

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());

        return user;
    }

}
