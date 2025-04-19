package com.musinsa.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.task.dto.request.brand.BrandInsertRequest;
import com.musinsa.task.dto.request.brand.BrandUpdateRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.ProductRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private BrandService brandService;

    @Test
    @DisplayName("브랜드 추가 성공")
    void addBrand_success() {
        // given
        BrandInsertRequest request = new BrandInsertRequest( "Brand1");
        Brand savedBrand = Brand.builder().name("Brand1").id(1L).build();

        when(brandRepository.existsByName("Brand1")).thenReturn(false);
        when(brandRepository.save(any(Brand.class))).thenReturn(savedBrand);

        // when
        brandService.addBrand(request);

        // then
        verify(brandRepository).save(any(Brand.class));
    }


    @Test
    @DisplayName("브랜드 수정 성공")
    void updateBrand_success() {
        // given
        Brand existing = new Brand();
        existing.setName("OldBrand");

        when(brandRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(brandRepository.save(any(Brand.class))).thenReturn(existing);

        BrandUpdateRequest request = new BrandUpdateRequest(1L, "NewName");

        // when
        Map<String,String> updatedId = brandService.updateBrand(request);

        // then
        assertThat(updatedId.get("이름")).isEqualTo("NewName");
    }
}
