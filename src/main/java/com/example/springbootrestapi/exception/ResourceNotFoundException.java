package com.example.springbootrestapi.exception;

/**
 * Resource Not Found Exception
 * 
 * Thrown when a requested resource is not found in the database.
 * 
 * @author Ananya
 * @version 1.0.0
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
