package com.user.demo.infrastructure.input.controller.stock_microservice_controller.category;

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

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
public class CategoryController {
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
}
