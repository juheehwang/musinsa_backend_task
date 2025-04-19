package com.musinsa.task.dto.request.brand;

import jakarta.validation.constraints.NotNull;

public record BrandDeleteRequest(
    @NotNull(message = "브랜드코드는 필수값입니다.") long id,
    @NotNull(message = "브랜드이름는 필수값입니다.") String name
) {
}
