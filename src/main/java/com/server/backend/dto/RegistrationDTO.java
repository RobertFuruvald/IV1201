package com.server.backend.dto;

import lombok.Getter;

/**
 * DTO for carrying user registration details.
 * This class encapsulates the information provided by a user during the registration process.
 * <p>
 * It includes name, surname, personal number (pnr), email, username and password.
 * The class is annotated with Lombok's {@code @Getter} to automatically generate getters,
 */
@Getter
public class RegistrationDTO {

    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;
    private String username;
}
