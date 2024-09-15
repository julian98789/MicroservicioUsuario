package com.user.demo.application.dto.stock_microservice_dto.branddto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {
    Long id;
    private String name;
    private String description;
}
