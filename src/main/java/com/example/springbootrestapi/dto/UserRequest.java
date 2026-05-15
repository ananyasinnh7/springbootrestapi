package com.example.springbootrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Request DTO
 * 
 * Data Transfer Object for user creation and update requests.
 * Contains validation annotations to ensure data integrity.
 * 
 * @author Ananya
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number should not exceed 20 characters")
    private String phone;

    @Size(max = 500, message = "Address should not exceed 500 characters")
    private String address;

}
