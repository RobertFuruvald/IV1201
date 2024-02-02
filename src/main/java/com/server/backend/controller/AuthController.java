package com.server.backend.controller;

import com.server.backend.dto.AuthenticationRequest;
import com.server.backend.dto.RegistrationDTO;
import com.server.backend.security.CustomUserDetailsPrincipal;
import com.server.backend.security.JwtUtil;
import com.server.backend.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authReq) {
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO regDetails) {
        try {
            registrationService.registerNewApplicant(regDetails);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing fields");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Account Created Successfully");
    }

}