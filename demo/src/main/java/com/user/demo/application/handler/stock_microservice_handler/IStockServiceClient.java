package com.user.demo.application.handler.stock_microservice_handler;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import com.user.demo.domain.util.pagination.PaginatedResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "stockServiceClient", url = "http://localhost:8080")
public interface IStockServiceClient {

    @PostMapping("/category")
    CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest);

    @GetMapping("/category")
    PaginatedResult<CategoryResponse> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "true") boolean ascending);

    @PostMapping("/brand")
    BrandResponse createBrand(@RequestBody BrandRequest brandRequest);

    @GetMapping("/brand")
    PaginatedResult<BrandResponse> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "true") boolean ascending);

    @PostMapping("/article")
    ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest);

    @GetMapping("/article")
    PaginatedResult<ArticleResponse> getArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "true") boolean ascending);
}
