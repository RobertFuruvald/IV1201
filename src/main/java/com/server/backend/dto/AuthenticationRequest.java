package com.server.backend.dto;

import lombok.Getter;


/**
 * DTO for carrying authentication request details.
 * This class is used to encapsulate the user credentials provided during login requests.
 * <p>
 * It includes the username and password fields, which are required for authentication.
 * The class is annotated with Lombok's {@code @Getter} to automatically generate getters,
 */
@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}