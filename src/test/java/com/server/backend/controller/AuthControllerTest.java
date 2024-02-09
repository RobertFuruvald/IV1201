package com.server.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.backend.dto.RegistrationDTO;
import com.server.backend.service.AuthenticationService;
import com.server.backend.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON strings

    @Test
public void whenRegisterWithInvalidData_thenReturns400AndValidationErrors() throws Exception {
    RegistrationDTO invalidRegDetails = new RegistrationDTO(); // Assuming you have a no-args constructor or setters
    // Leaving all fields blank to trigger validation errors
    String body = objectMapper.writeValueAsString(invalidRegDetails);

    mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name").value("Name is required")) // Adjust these JSON path expressions based on your actual error response structure
            .andExpect(jsonPath("$.email").value("Email is required"));
}


@Test
public void whenRegisterWithValidData_thenReturns201() throws Exception {
    RegistrationDTO validRegDetails = new RegistrationDTO("John", "Doe", "123456789", "john.doe@example.com", "securePassword123", "johndoe");
    String body = objectMapper.writeValueAsString(validRegDetails);

    mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isCreated())
            .andExpect(content().string("Account Created Successfully"));
}

}
