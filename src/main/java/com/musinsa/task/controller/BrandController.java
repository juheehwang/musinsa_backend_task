package com.musinsa.task.controller;

import com.musinsa.task.dto.request.brand.BrandDeleteRequest;
import com.musinsa.task.dto.request.brand.BrandInsertRequest;
import com.musinsa.task.dto.request.brand.BrandUpdateRequest;
import com.musinsa.task.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "브랜드 추가")
    @PostMapping
    public ResponseEntity<?> addBrand(@RequestBody BrandInsertRequest insertRequest){
        return ResponseEntity.ok(brandService.addBrand(insertRequest));
    }

    @Operation(summary = "브랜드 수정")
    @PutMapping
    public ResponseEntity<?> updateBrand(@RequestBody BrandUpdateRequest updateRequest) {
        return ResponseEntity.ok(brandService.updateBrand(updateRequest));
    }

    @Operation(summary = "브랜드 삭제")
    @DeleteMapping
    public ResponseEntity<?> deleteBrand(@RequestBody BrandDeleteRequest deleteRequest) {
        return ResponseEntity.ok(brandService.deleteBrand(deleteRequest));
    }
}
