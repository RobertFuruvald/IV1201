package com.server.backend.controller;

import com.server.backend.dto.AuthenticationRequest;
import com.server.backend.dto.RegistrationDTO;
import com.server.backend.security.CustomUserDetailsPrincipal;
import com.server.backend.security.JwtUtil;
import com.server.backend.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication requests, including login and
 * registration.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RegistrationService registrationService;

    /**
     * is used for testing now and needs to be updated
     **/
    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }

    /**
     * Authenticates a user and generates a JWT token upon successful
     * authentication.
     * 
     * @param authReq The authentication request containing username and password.
     * @return ResponseEntity containing the JWT token or an error message.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthenticationRequest authReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));

            CustomUserDetailsPrincipal userDetails = (CustomUserDetailsPrincipal) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(jwt);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    /**
     * Registers a new user account with the given registration details.
     * 
     * @param regDetails The registration details for the new account.
     * @return ResponseEntity indicating the result of the registration attempt.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationDTO regDetails) {
        registrationService.registerNewApplicant(regDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Created Successfully");
    }

}