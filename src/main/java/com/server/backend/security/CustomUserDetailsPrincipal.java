package com.server.backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

import java.util.Collection;

/**
 * Custom implementation of {@link User} to include additional user properties.
 * <p>
 * This class extends the spring security {@link User} class to add application
 * specific properties such as email and personId. This class is used by the
 * authentication process to represent an authenticated user.
 */
@Getter
public class CustomUserDetailsPrincipal extends User {
    private final String email;
    private final Integer personId;

    /**
     * Constructs a new {@link CustomUserDetailsPrincipal}
     * 
     * @param username    the username used to authenticate the user.
     * @param password    the password used to authenticate the user.
     * @param authorities the authorities granted to the user.
     * @param email       the email address of the user.
     * @param personId    the unique identifier of the user.
     */
    public CustomUserDetailsPrincipal(String username, String password,
            Collection<? extends GrantedAuthority> authorities, String email, Integer personId) {
        super(username, password, authorities);
        this.email = email;
        this.personId = personId;
    }
}