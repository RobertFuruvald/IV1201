package com.server.backend.exception;

/**
 * Custom exception class for situations where an application creation attempt
 * is made but an application already exists for the user.
 */
public class ApplicationAlreadyExistsError extends RuntimeException {

    /**
     * Constructor with a detail message.
     * @param message the detail message describing the specific reason for the exception.
     */
    public ApplicationAlreadyExistsError(String message) {
        super(message);
    }
}
