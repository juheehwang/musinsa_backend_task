package com.musinsa.task.dto.request.brand;

import com.musinsa.task.entity.Brand;
import jakarta.validation.constraints.NotNull;

public record BrandInsertRequest(
    @NotNull(message = "브랜드이름은 필수값입니다.") String name
){
    public Brand toEntity() {
        return Brand.builder()
            .name(this.name)
            .build();
    }

}
