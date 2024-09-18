package com.user.demo.infrastructure.input.controller.stock_microservice_controller.article;

import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleRequest;
import com.user.demo.application.dto.stock_microservice_dto.articledto.ArticleResponse;
import com.user.demo.application.handler.stock_microservice_handler.StockService;
import com.user.demo.domain.util.pagination.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
public class ArticleController {

    private final StockService stockService;

    @GetMapping("/article")
    public ResponseEntity<PaginatedResult<ArticleResponse>> getArticles(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name")  String sort,
            @RequestParam(defaultValue = "true") boolean ascending) {

        PaginatedResult<ArticleResponse> paginatedResult = stockService.getArticles(page, size, sort, ascending);

        return ResponseEntity.ok(paginatedResult);
    }

    @PostMapping("/article")
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest articleRequest) {
        ArticleResponse article = stockService.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
}
