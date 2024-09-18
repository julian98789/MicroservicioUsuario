package com.user.demo.application.handler.stock_microservice_handler;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryRequest;
import com.user.demo.application.dto.stock_microservice_dto.categorydto.CategoryResponse;
import com.user.demo.domain.util.pagination.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {
    private final IStockServiceClient stockServiceClient;


    public PaginatedResult<CategoryResponse> getCategories(int page, int size, String sort, boolean ascending) {
        return stockServiceClient.getCategories(page, size, sort, ascending);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return stockServiceClient.createCategory(categoryRequest);
    }

    public PaginatedResult<BrandResponse> getBrands(int page, int size, String sort, boolean ascending) {
        return stockServiceClient.getBrands(page, size, sort, ascending);
    }

    public BrandResponse createBrand(BrandRequest brandRequest) {
        return stockServiceClient.createBrand(brandRequest);
    }

    public PaginatedResult<ArticleResponse> getArticles(int page, int size, String sort, boolean ascending) {
        return stockServiceClient.getArticles(page, size, sort, ascending);
    }

    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        return stockServiceClient.createArticle(articleRequest);
    }
}