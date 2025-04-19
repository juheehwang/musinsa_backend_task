package com.musinsa.task.service;

import com.musinsa.task.dto.request.product.ProductInsertRequest;
import com.musinsa.task.dto.request.product.ProductUpdateRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import com.musinsa.task.entity.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;


    public Long addProduct(ProductInsertRequest productInsertRequest) {
        Brand brand = brandRepository.findById(productInsertRequest.brandId())
            .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 존재하지 않습니다."));

        return productRepository.save(productInsertRequest.toEntity(brand)).getId();
    }

    public Long updateProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productUpdateRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.setCategory(Category.nameOf(productUpdateRequest.category()));
        product.setPrice(productUpdateRequest.price());

        return productRepository.save(product).getId();
    }

    public Boolean deleteProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productUpdateRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        productRepository.delete(product);
        return true;
    }
}
