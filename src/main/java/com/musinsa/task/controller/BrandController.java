package com.musinsa.task.controller;

import com.musinsa.task.dto.request.brand.BrandInsertRequest;
import com.musinsa.task.dto.request.brand.BrandUpdateRequest;
import com.musinsa.task.service.BrandService;
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

    @PostMapping
    public ResponseEntity<?> addBrand(@RequestBody BrandInsertRequest insertRequest){
        return ResponseEntity.ok(brandService.addBrand(insertRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateBrand(@RequestBody BrandUpdateRequest updateRequest) {
        return ResponseEntity.ok(brandService.updateBrand(updateRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBrand(@RequestBody BrandUpdateRequest updateRequest) {
        return ResponseEntity.ok(brandService.deleteBrand(updateRequest));
    }
}
