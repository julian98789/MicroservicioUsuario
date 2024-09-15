package com.user.demo.application.handler.stock_microservice_handler;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {
    private final IStockServiceClient stockServiceClient;


    public List<CategoryResponse> getAllCategories() {
        return stockServiceClient.getCategories();
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return stockServiceClient.createCategory(categoryRequest);
    }

    public List<BrandResponse> getAllBrands() {
        return stockServiceClient.getBrands();
    }

    public BrandResponse createBrand(BrandRequest brandRequest) {
        return stockServiceClient.createBrand(brandRequest);
    }

    public List<ArticleResponse> getAllArticles() {
        return stockServiceClient.getArticles();
    }

    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        return stockServiceClient.createArticle(articleRequest);
    }
}