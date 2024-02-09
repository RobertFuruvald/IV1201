package com.server.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for carrying login response data upon successful login.
 * <p>
 * Includes JWT token for authenticating api calls and roles for authorization.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private List<String> roles;
    private String token;

    /**
     * Constructor for LoginresponseDTO
     * <p>
     * Constructs an object of LoginResponseDTO with JWT token for authenticating
     * api calls and role for authorization.
     * 
     * @param role  the list of roles for the authenticated person.
     * @param token the JWT token string.
     */
}
