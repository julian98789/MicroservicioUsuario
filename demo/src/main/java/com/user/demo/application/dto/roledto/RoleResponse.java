package com.user.demo.application.dto.roledto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleResponse {
    Long id;
    private String name;
    private String description;
}
