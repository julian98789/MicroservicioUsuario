package com.user.demo.infrastructure.input.controller.stock_microservice_controller;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import com.user.demo.application.handler.stock_microservice_handler.StockService;
import com.user.demo.domain.util.pagination.PaginatedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/category")
    public ResponseEntity<PaginatedResult<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "name") @NotBlank @Size(min = 1) String sort,
            @RequestParam(defaultValue = "true") boolean ascending) {

        PaginatedResult<CategoryResponse> paginatedResult = stockService.getCategories(page, size, sort, ascending);

        return ResponseEntity.ok(paginatedResult);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse category = stockService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> brands = stockService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        BrandResponse brand = stockService.createBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        List<ArticleResponse> articles = stockService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        ArticleResponse article = stockService.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
}