package com.musinsa.task.service;

import com.musinsa.task.dto.request.brand.BrandDeleteRequest;
import com.musinsa.task.dto.request.brand.BrandInsertRequest;
import com.musinsa.task.dto.request.brand.BrandUpdateRequest;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.exception.DuplicatedException;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    public Map<String,Long> addBrand(BrandInsertRequest insertRequest) {
        if(brandRepository.existsByName(insertRequest.name())){
            throw new DuplicatedException("존재하는 브랜드 이름입니다");
        }
        Long brandId = brandRepository.save(insertRequest.toEntity()).getId();

        return Map.of("아이디",brandId);
    }

    public Map<String, String> updateBrand(BrandUpdateRequest updateRequest) {
        Brand brand = brandRepository.findById(updateRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 존재하지 않습니다."));

        brand.setName(updateRequest.name());
        String brandName = brandRepository.save(brand).getName();

        return Map.of("이름",brandName);
    }

    public boolean deleteBrand(BrandDeleteRequest deleteRequest) {
        Brand brand = brandRepository.findById(deleteRequest.id())
            .orElseThrow(() -> new IllegalArgumentException("삭제할 브랜드가 존재하지 않습니다."));
        brandRepository.delete(brand);
        return true;
    }

}
