package com.server.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.server.backend.dto.AuthenticationRequestDTO;
import com.server.backend.dto.LoginResponseDTO;
import com.server.backend.security.CustomUserDetailsPrincipal;
import com.server.backend.security.JwtUtil;


/**
 * Service class for handling authentication processes.
 * <p>
 * This service provides the functionality to authenticate a user based on their
 * username and password.
 * It uses the {@link AuthenticationManager} to verify the user's
 * credentials and, upon successful
 * authentication, generates a JWT token for the user. The service also
 * retrieves the user's roles as part
 * of the authentication response.
 * </p>
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticates a user with the provided username and password.
     * <p>
     * This method attempts to authenticate a user by using the
     * {@link AuthenticationManager}.
     * If authentication is successful, it generates a JWT token for the user and
     * collects their roles.
     * A {@link LoginResponseDTO} containing the JWT token and user roles is then
     * returned.
     * </p>
     * 
     * @param authReq The authentication request data transfer object
     *                ({@link AuthenticationRequestDTO})
     *                containing the user's username and password.
     * @return A {@link LoginResponseDTO} containing the JWT token and a list of the
     *         user's roles upon
     *         successful authentication.
     * @throws BadCredentialsException If authentication fails due to invalid
     *                                 username or password,
     *                                 a {@link BadCredentialsException} is thrown.
     */
    public LoginResponseDTO authenticate(AuthenticationRequestDTO authReq) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));

        CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new LoginResponseDTO(roles, jwt);
    }
}
