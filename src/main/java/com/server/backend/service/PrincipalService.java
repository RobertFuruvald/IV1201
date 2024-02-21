package com.server.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.server.backend.security.CustomUserDetailsPrincipal;

@Service
public class PrincipalService {

    public CustomUserDetailsPrincipal getAuthenticatedUserDetails() {
        return (CustomUserDetailsPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
