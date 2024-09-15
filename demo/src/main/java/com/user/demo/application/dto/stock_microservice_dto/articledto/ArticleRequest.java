package com.user.demo.application.dto.stock_microservice_dto.articledto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ArticleRequest {

     private String name;

    private String description;

    private int quantity;

    private BigDecimal price;

    private Long brandId;

    private List<Long> categoryIds;
}
