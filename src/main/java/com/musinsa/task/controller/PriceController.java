package com.musinsa.task.controller;

import com.musinsa.task.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @Operation(summary = "카테고리 별로 최저가격인 브랜드와 가격조회 및 총합계산")
    @GetMapping("/lowest-by-category")
    public ResponseEntity<?> getLowestByCategory(){
        return ResponseEntity.ok(priceService.findLowestByCategoryWithTotal());
    }

    @Operation(summary = "최저가격에 판매하는 단일 브랜드와 카테고리의 상품가격 및 총액조회")
    @GetMapping("/lowest-by-brand")
    public ResponseEntity<?> getLowestByBrand(){
        return ResponseEntity.ok(priceService.findLowestByBrandWithTotal());
    }

    @Operation(summary = "카테고리 이름으로 최저/최고 가격과 브랜드 조회")
    @GetMapping("/category-min-max-price")
    public ResponseEntity<?> getCategoryMinMaxPriceInfo(@RequestParam String category){
        return ResponseEntity.ok(priceService.findCategoryMinMaxPriceInfo(category));
    }



}
