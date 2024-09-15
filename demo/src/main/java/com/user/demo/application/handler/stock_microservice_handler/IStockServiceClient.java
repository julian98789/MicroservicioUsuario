package com.user.demo.application.handler.stock_microservice_handler;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name = "stockServiceClient", url = "http://localhost:8080")
public interface IStockServiceClient {

    @PostMapping("/category")
    CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest);

    @GetMapping("/category")
    List<CategoryResponse> getCategories();

    @PostMapping("/brand")
    BrandResponse createBrand(@RequestBody BrandRequest brandRequest);

    @GetMapping("/brand")
    List<BrandResponse> getBrands();

    @PostMapping("/article")
    ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest);

    @GetMapping("/article")
    List<ArticleResponse> getArticles();
}
