package com.user.demo.infrastructure.input.controller.stock_microservice_controller.brand;

import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandRequest;
import com.user.demo.application.dto.stock_microservice_dto.branddto.BrandResponse;
import com.user.demo.application.handler.stock_microservice_handler.StockService;
import com.user.demo.domain.util.pagination.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
public class BrandController {

    private final StockService stockService;

    @GetMapping("/brand")
    public ResponseEntity<PaginatedResult<BrandResponse>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "true") boolean ascending) {

        PaginatedResult<BrandResponse> paginatedResult = stockService.getBrands(page, size, sort, ascending);

        return ResponseEntity.ok(paginatedResult);
    }

    @PostMapping("/brand")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        BrandResponse brand = stockService.createBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }
}
