package com.user.demo.infrastructure.input.controller.LoginRestControllerTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.demo.application.dto.authenticationdto.AuthenticationRequest;
import com.user.demo.application.dto.authenticationdto.AuthenticationResponse;
import com.user.demo.domain.exception.InvalidCredentialsLoginException;
import com.user.demo.infrastructure.configuration.securityconfig.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
 class LoginRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should successfully authenticate a user and return HTTP 200 with a token")
    void testAuthenticate_Success() throws Exception {
        // Dado
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        AuthenticationResponse response = new AuthenticationResponse("fake-jwt-token");

        // Cuando
        when(service.authenticate(any(AuthenticationRequest.class))).thenReturn(response);

        // Entonces
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"));
    }
    @Test
    @DisplayName("Should return HTTP 400 Bad Request when authentication fails")
    void testAuthenticate_Failure() throws Exception {
        // Dado
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("wrong-password");

        // Cuando
        when(service.authenticate(any(AuthenticationRequest.class)))
                .thenThrow(new InvalidCredentialsLoginException("Invalid credentials"));

        // Entonces
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertEquals("Invalid credentials", result.getResponse().getContentAsString())
                );
    }

}