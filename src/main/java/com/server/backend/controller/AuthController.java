package com.server.backend.controller;

import com.server.backend.dto.AuthenticationRequestDTO;
import com.server.backend.dto.LoginResponseDTO;
import com.server.backend.dto.RegistrationDTO;
import com.server.backend.service.AuthenticationService;
import com.server.backend.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication requests, including login and
 * registration.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    /**
     * is used for testing now and needs to be updated
     **/
    /*
     * @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
     * public ResponseEntity<?> handleOptionsRequest() {
     * return ResponseEntity.ok().build();
     * }
     */
    /**
     * Authenticates a user and generates a JWT token upon successful
     * authentication.
     * 
     * @param authReq The authentication request containing username and password.
     * @return ResponseEntity containing the JWT token or an error message.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDTO authReq) {
        LoginResponseDTO loginResponse = authenticationService.authenticate(authReq);
        return ResponseEntity.ok(loginResponse);
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