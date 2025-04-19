package com.musinsa.task.dto.request.product;

import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequest(
    @NotNull(message = "상품코드는 필수값입니다.") long id,
    String category,
    Long brandId,
    int price
) {

}
