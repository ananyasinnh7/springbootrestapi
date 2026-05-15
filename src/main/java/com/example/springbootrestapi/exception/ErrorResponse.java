package com.example.springbootrestapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Error Response DTO
 * 
 * Standardized error response format for API errors.
 * 
 * @author Ananya
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private int status;

    private String message;

    private String error;

    private LocalDateTime timestamp;

    private String path;

    private List<FieldError> fieldErrors;

    /**
     * Field-level error details
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
    }

}
