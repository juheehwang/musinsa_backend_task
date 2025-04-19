package com.musinsa.task.controller;

import com.musinsa.task.dto.request.product.ProductInsertRequest;
import com.musinsa.task.dto.request.product.ProductUpdateRequest;
import com.musinsa.task.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 추가")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductInsertRequest insertRequest){
        return ResponseEntity.ok(productService.addProduct(insertRequest));
    }

    @Operation(summary = "상품 수정")
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest updateRequest){
        return ResponseEntity.ok(productService.updateProduct(updateRequest));
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductUpdateRequest updateRequest){
        return ResponseEntity.ok(productService.deleteProduct(updateRequest));
    }

}
