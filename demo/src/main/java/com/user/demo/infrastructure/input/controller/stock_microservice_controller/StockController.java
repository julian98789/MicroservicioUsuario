package com.user.demo.infrastructure.input.controller.stock_microservice_controller;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import com.user.demo.application.handler.stock_microservice_handler.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/category")
    public List<CategoryResponse> getCategories() {
        return stockService.getAllCategories();
    }

    @PostMapping("/category")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return stockService.createCategory(categoryRequest);
    }

    @GetMapping("/brands")
    public List<BrandResponse> getBrands() {
        return stockService.getAllBrands();
    }

    @PostMapping("/brands")
    public BrandResponse createBrand(@RequestBody BrandRequest brandRequest) {
        return stockService.createBrand(brandRequest);
    }

    @GetMapping("/articles")
    public List<ArticleResponse> getArticles() {
        return stockService.getAllArticles();
    }

    @PostMapping("/articles")
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest) {
        return stockService.createArticle(articleRequest);
    }
}
