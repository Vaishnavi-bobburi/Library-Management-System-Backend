package com.example.librarymanagementbackend.exception;

/**
 * Thrown when a requested Book (or other resource) cannot be found.
 * Handled globally by GlobalExceptionHandler and translated into a 404 response.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
