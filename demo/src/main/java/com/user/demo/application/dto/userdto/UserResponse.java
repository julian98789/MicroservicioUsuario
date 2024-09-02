package com.user.demo.application.dto.userdto;

import com.user.demo.application.dto.roledto.RoleResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserResponse {
    private String name;
    private String lastName;

    private String identification;

    private String  phone;

    private LocalDate dateOfBirth;

    private String email;

    private String password;

    private RoleResponse role;

}
