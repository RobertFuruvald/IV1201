package com.server.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class that represents validation errors throughout the
 * application business logic.
 * <p>
 * This exception is thrown when input data from client fails validation checks.
 * It is annoted with {@link ResponseStatus} to automatically translate thrown
 * instances of this exception into HTTP responses with
 * HttpStatus.BAD_REQUEST (400 bad request) status code
 */
public class CustomValidationException extends RuntimeException {

    /**
     * Constructor for {@link CustomValidationException} with a given detail
     * message.
     * 
     * @param message the detail message (which can be retrieved with
     *                {@link Throwable#getMessage()} method).
     */
    public CustomValidationException(String message) {
        super(message);
    }
}