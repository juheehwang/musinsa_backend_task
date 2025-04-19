package com.musinsa.task.dto.request.product;

import jakarta.validation.constraints.NotNull;

public record ProductDeleteRequest(
    @NotNull(message = "상품코드는 필수값입니다.") long id,
    @NotNull(message = "카테고리는 필수값입니다.") String category,
    @NotNull(message = "브랜드코드는 필수값입니다.") Long brandId,
    @NotNull(message = "가격은 필수값입니다.") int price
) {
}
