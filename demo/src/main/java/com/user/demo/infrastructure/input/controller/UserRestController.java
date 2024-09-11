package com.user.demo.infrastructure.input.controller;

import com.user.demo.application.dto.authenticationdto.AuthenticationRequest;
import com.user.demo.application.dto.authenticationdto.AuthenticationResponse;
import com.user.demo.application.dto.userdto.UserRequest;
import com.user.demo.application.dto.userdto.UserResponse;
import com.user.demo.application.handler.IUserHandler;
import com.user.demo.infrastructure.configuration.securityconfig.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler iUserHandler;
    @Operation(
            summary = "Register a new user",
            description = "Register a new user in the system. If the user registration is successful, the user details will be returned. If there are validation errors or the user already exists, an error will be returned.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string")
                    )
            )
    })
    @PostMapping("/register-assistant")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse registerUser = iUserHandler.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }


}
