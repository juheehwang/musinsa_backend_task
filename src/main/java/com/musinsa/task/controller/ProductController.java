package com.musinsa.task.controller;

import com.musinsa.task.dto.request.product.ProductInsertRequest;
import com.musinsa.task.dto.request.product.ProductUpdateRequest;
import com.musinsa.task.service.ProductService;
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

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductInsertRequest insertRequest){
        return ResponseEntity.ok(productService.addProduct(insertRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest updateRequest){
        return ResponseEntity.ok(productService.updateProduct(updateRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductUpdateRequest updateRequest){
        return ResponseEntity.ok(productService.deleteProduct(updateRequest));
    }

}
