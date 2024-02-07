package com.server.backend.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT authorization filter that intercepts requests to validate JWT tokens.
 * <p>
 * This filter extends {@link OncePerRequestFilter}, ensuring it is executed
 * once per request.
 * It checks for the presence of a JWT in the request, validates it, and sets
 * the authentication in the security context if the token is valid. This
 * enables Spring Security to authorize the request
 * based on the roles and privileges of the user associated with the token.
 * </p>
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt = jwtUtil.extractToken(request);
            if (jwt != null) {
                String username = jwtUtil.extractUsername(jwt);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) userDetailsService
                            .loadUserByUsername(username);

                    if (jwtUtil.validateToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (UsernameNotFoundException e) {
            /* TODO Implement error handling later */
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
