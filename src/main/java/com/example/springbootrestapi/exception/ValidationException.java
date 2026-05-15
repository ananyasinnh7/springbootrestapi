package com.example.springbootrestapi.exception;

/**
 * Validation Exception
 * 
 * Thrown when input data validation fails.
 * 
 * @author Ananya
 * @version 1.0.0
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
