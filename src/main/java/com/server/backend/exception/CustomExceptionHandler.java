package com.server.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<String> handlecustomValidationException(CustomValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ApplicationAlreadyExistsError.class)
    public ResponseEntity<String> handleApplicationAlreadyExistsError(ApplicationAlreadyExistsError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
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

    /**
     * This method is invoked automatically when a MethodArgumentNotValidException
     * is thrown from any part of the application. It constructs a response entity
     * with a status of 400 bad request upon any validation faliure and includes the
     * message set by the validation tag in the body
     * 
     * @param ex The MethodArgumentNotValidException instance caught by this handler
     * @return A ResponseEntity object containing the name and error message of each
     *         failed field
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Handles exceptions related to database transaction failures.
     * <p>
     * This handler is invoked automatically when a {@link TransactionException} is
     * thrown during
     * database operations such as commit, rollback, or communication failures. It
     * signifies
     * that an internal error occurred while performing a transactional operation,
     * which could
     * not be completed successfully.
     * </p>
     * <p>
     * Upon catching such an exception, this method constructs and returns a
     * {@link ResponseEntity}
     * with a status of {@link HttpStatus#INTERNAL_SERVER_ERROR} (500), indicating
     * an error on the
     * server that prevented the completion of the request. The response body
     * contains a generic
     * message to avoid exposing specific database or internal operation details to
     * the client, thus
     * enhancing security.
     * </p>
     * 
     * @param ex The {@link TransactionException} instance caught by this handler.
     * @return A {@link ResponseEntity<Object>} with the HTTP status set to 500
     *         (Internal Server Error)
     *         and a generic error message in the body.
     */
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Object> handleTransactionExceptions(TransactionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal databse operation failed");

    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String errorMessage = "Invalid date format. Please use the correct date format (yyyy-MM-dd).";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Generic exception handler that catches all exceptions not specifically
     * handled by other exception handlers.
     * 
     * This method provides a catch-all mechanism for exceptions that do not match
     * any of the specific handlers
     * defined in this class. It ensures that the application can gracefully respond
     * to any unexpected situation
     * with a standardized HTTP response.
     * 
     * @param ex      The exception instance caught by this handler.
     * @param request The WebRequest instance providing context about the request
     *                that resulted in the exception.
     * @return A ResponseEntity object containing a general error message and an
     *         HTTP status code of 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", "An unexpected error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}