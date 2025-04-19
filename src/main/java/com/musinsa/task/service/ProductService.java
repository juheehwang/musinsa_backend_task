package com.musinsa.task.service;

import com.musinsa.task.dto.request.product.ProductDeleteRequest;
import com.musinsa.task.dto.request.product.ProductInsertRequest;
import com.musinsa.task.dto.request.product.ProductUpdateRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import com.musinsa.task.entity.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;


    public Map<String,Long> addProduct(ProductInsertRequest productInsertRequest) {
        Brand brand = brandRepository.findById(productInsertRequest.brandId())
            .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 존재하지 않습니다."));

        Product product = productRepository.save(productInsertRequest.toEntity(brand));

        return Map.of("상품코드",product.getId());
    }

    public Map<String,Long> updateProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productUpdateRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.setCategory(Category.nameOf(productUpdateRequest.category()));
        product.setPrice(productUpdateRequest.price());

        Long productId = productRepository.save(product).getId();
        return Map.of("상품코드",productId);
    }

    public Boolean deleteProduct(ProductDeleteRequest deleteRequest) {
        Product product = productRepository.findById(deleteRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("삭제할 상품이 존재하지 않습니다."));

        productRepository.delete(product);
        return true;
    }
}
