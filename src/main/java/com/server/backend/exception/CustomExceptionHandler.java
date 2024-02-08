package com.server.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for custom exceptions.
 * <p>
 * This class is annotated with {@link ControllerAdvice} to serve as a
 * centralized exception handling mechanism across the entire application. It
 * captures exceptions thrown from any controller and transforms them into an
 * appropriate HTTP response.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles exceptions of type {@link CustomValidationException}.
     * <p>
     * This method is invoked automatically when a {@link CustomValidationException}
     * is thrown from any part of the application. It constructs a
     * {@link ResponseEntity}
     * with a status of {@link HttpStatus.BAD_REQUEST} and includes the exception
     * message
     * in the response body.
     * </p>
     * 
     * @param ex The {@link CustomValidationException} instance caught by this
     *           handler.
     * @return A {@link ResponseEntity} object containing the exception message and
     *         a
     *         HTTP status code of 400 (Bad Request).
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<String> handleValidationExceptions(CustomValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles BadCredentialsException.
     * 
     * This method is invoked automatically when a BadCredentialsException
     * is thrown from any part of the application. It constructs a ResponseEntity
     * with a status of HttpStatus.UNAUTHORIZED and includes a custom error message
     * in the response body.
     * 
     * @param ex The BadCredentialsException instance caught by this handler.
     * @return A ResponseEntity object containing the custom error message and a
     *         HTTP status code of 401 (Unauthorized).
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}