package com.server.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for carrying user registration details.
 * This class encapsulates the information provided by a user during the
 * registration process.
 * <p>
 * It includes name, surname, personal number (pnr), email, username and
 * password. This class uses validation to ensuere that properties are not null
 * and in correct format
 * The class is annotated with Lombok's {@code @Getter} to automatically
 * generate getters,
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required")
    private String surname;

    @NotBlank(message = "Person number is required")
    private String pnr;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Username is required")
    private String username;
}
