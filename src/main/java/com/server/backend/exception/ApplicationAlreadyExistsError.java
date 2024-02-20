package com.server.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class that represents erros when user tries to create an
 * application while already having one. This is an exception because it should
 * not be possible to do so from front end
 * <p>
 * This exception annoted with {@link ResponseStatus} to automatically translate
 * thrown
 * instances of this exception into HTTP responses with
 * HttpStatus.BAD_REQUEST (400 bad request) status code
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationAlreadyExistsError extends RuntimeException {

    /**
     * Constructor for {@link CustomValidationException} with a given detail
     * message. The exception will automatically send http response with 400 bad
     * request status and the given message.
     * 
     * @param message the detail message (which can be retrieved with
     *                {@link Throwable#getMessage()} method).
     */
    public ApplicationAlreadyExistsError(String message) {
        super(message);
    }
}