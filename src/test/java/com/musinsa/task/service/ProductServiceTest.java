package com.musinsa.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.task.dto.request.product.ProductInsertRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import com.musinsa.task.entity.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;


    @Test
    @DisplayName("상품 추가 성공")
    void addProduct_success() {
        // given
        Brand brand = new Brand();
        brand.setName("TestBrand");

        ProductInsertRequest request = new ProductInsertRequest("상의",1L, 10000);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        Product savedProduct = new Product();
        savedProduct.setBrand(brand);
        savedProduct.setCategory(Category.TOP);
        savedProduct.setPrice(10000);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        //when
        productService.addProduct(request);

        //then
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("상품 추가 실패 - 브랜드 없음")
    void addProduct_fail_noBrand() {
        ProductInsertRequest request = new ProductInsertRequest("하의", 1L,5000);

        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(request));
    }
}