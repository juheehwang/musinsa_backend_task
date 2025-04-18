package com.musinsa.task.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;



    @BeforeEach
    void setUp() {
        Brand brandA = new Brand();
        brandA.setName("Z");

        Product product1 = new Product();
        product1.setPrice(10000);
        product1.setCategory(Category.BAG);
        product1.setBrand(brandA);

        Product product2 = new Product();
        product2.setPrice(3300);
        product2.setCategory(Category.ACCESSORY);
        product2.setBrand(brandA);

        brandA.setProducts(List.of(product1,product2));
        brandRepository.save(brandA);
    }

    @Test
    @DisplayName("카테고리별 최저가 & 총합 조회 api")
    void getLowestByCategory() throws Exception {
        mockMvc.perform(get("/api/v1/prices/lowest-by-category"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.products").isArray())
            .andExpect(jsonPath("$.총액").isNumber());
    }

    @Test
    @DisplayName("단일브랜드로 모든 카테고리 상품 최저가 & 총합 api")
    void getLowestByBrand() throws Exception {
        mockMvc.perform(get("/api/v1/prices/lowest-by-brand"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.브랜드").isString())
            .andExpect(jsonPath("$.카테고리").isArray())
            .andExpect(jsonPath("$.총액").isNumber());
    }
}