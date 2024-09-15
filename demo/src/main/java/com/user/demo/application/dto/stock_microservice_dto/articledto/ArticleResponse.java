package com.user.demo.application.dto.stock_microservice_dto.articledto;

import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRelationArticleDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ArticleResponse {
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal price;

    private Long brandId;

    private List<CategoryRelationArticleDto> categoryIds;
}
