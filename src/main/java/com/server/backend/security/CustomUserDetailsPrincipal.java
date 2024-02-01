package com.server.backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

import java.util.Collection;

@Getter
public class CustomUserDetailsPrincipal extends User {
    private final String email;
    private final Integer personId;

    public CustomUserDetailsPrincipal(String username, String password,
            Collection<? extends GrantedAuthority> authorities, String email, Integer personId) {
        super(username, password, authorities);
        this.email = email;
        this.personId = personId;
    }
}