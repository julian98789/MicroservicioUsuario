package com.user.demo.application.dto.stock_microservice_dto.categorydto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    Long id;
    private String name;
    private String description;
}