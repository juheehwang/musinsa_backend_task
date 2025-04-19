package com.musinsa.task.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.task.dto.request.brand.BrandInsertRequest;
import com.musinsa.task.dto.request.brand.BrandUpdateRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BrandRepository brandRepository;

    @Test
    @DisplayName("브랜드 추가 api test")
    void addBrand() throws Exception {
        mockMvc.perform(post("/api/v1/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new BrandInsertRequest("Brand1"))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.아이디").value(1L));
    }

    @Test
    void updateBrand() throws Exception {
        Brand brand = new Brand();
        brand.setName("Old");
        brandRepository.save(brand);


        mockMvc.perform(put("/api/v1/brands")
                .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new BrandUpdateRequest(brand.getId(), "Updated"))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.이름").value("Updated"));
    }

    @Test
    void deleteBrand() throws Exception {
        Brand brand = new Brand();
        brand.setName("Deleted");
        brandRepository.save(brand);

        mockMvc.perform(delete("/api/v1/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new BrandUpdateRequest(brand.getId(), "Deleted"))))
            .andExpect(status().isOk());
    }
}
