package com.user.demo.infrastructure.input.controller;

import com.user.demo.application.dto.userdto.UserRequest;
import com.user.demo.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IUserHandler iUserHandler;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    @DisplayName("Should successfully register a user and return HTTP 201")
    void testRegisterUser_Success() throws Exception {
        // Dado
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");
        userRequest.setLastName("Doe");
        userRequest.setIdentification("123456");
        userRequest.setPhone("+5551234");
        userRequest.setDateOfBirth(LocalDate.of(2000, 1, 1));
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("password");

        // Cuando
        when(iUserHandler.registerUser(any(UserRequest.class))).thenReturn(null);

        // Entonces
        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\", \"lastName\":\"Doe\", \"identification\":\"123456\", \"phone\":\"+5551234\", \"dateOfBirth\":\"2000-01-01\", \"email\":\"john.doe@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return HTTP 400 when the request body is empty")
    void testRegisterUser_EmptyRequest() throws Exception {

        String emptyRequestBody = "";


        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyRequestBody))

                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("Should return HTTP 400 when the fields are empty")
    void testRegisterUser_EmptyFields() throws Exception {

        String requestBodyWithEmptyFields = "{ \"name\": \"\", \"lastName\": \"\", \"identification\": \"\", \"phone\": \"\", \"dateOfBirth\": \"\", \"email\": \"\", \"password\": \"\" }";

        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyWithEmptyFields))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return HTTP 400 when the phone number exceeds 13 characters")
    void testRegisterUser_PhoneExceedsMaxLength() throws Exception {

        String requestBodyWithLongPhone = "{ \"name\": \"John\", \"lastName\": \"Doe\", \"identification\": \"123456\", \"phone\": \"12345678901234\", \"dateOfBirth\": \"2000-01-01\", \"email\": \"john.doe@example.com\", \"password\": \"password\" }";

        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyWithLongPhone))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return HTTP 400 when the email format is invalid")
    void testRegisterUser_InvalidEmailFormat() throws Exception {

        String requestBodyWithInvalidEmail = "{ \"name\": \"John\", \"lastName\": \"Doe\", \"identification\": \"123456\", \"phone\": \"555-1234\", \"dateOfBirth\": \"2000-01-01\", \"email\": \"john.doeatexample.com\", \"password\": \"password\" }";


        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyWithInvalidEmail))

                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("Should return HTTP 400 when the identification contains non-numeric characters")
    void testRegisterUser_NonNumericIdentification() throws Exception {

        String requestBodyWithNonNumericIdentification = "{ \"name\": \"John\", \"lastName\": \"Doe\", \"identification\": \"ABC123\", \"phone\": \"555-1234\", \"dateOfBirth\": \"2000-01-01\", \"email\": \"john.doe@example.com\", \"password\": \"password\" }";


        mockMvc.perform(post("/auth/register-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyWithNonNumericIdentification))

                .andExpect(status().isBadRequest());
    }
}