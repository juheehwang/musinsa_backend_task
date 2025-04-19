package com.musinsa.task.dto.request.product;

import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import jakarta.validation.constraints.NotNull;

public record ProductInsertRequest (
    @NotNull(message = "카테고리는 필수값입니다.") String category,
    @NotNull(message = "브랜드 아이디는 필수값입니다.") Long brandId,
    @NotNull(message = "가격은 필수값입니다.") int price
    ){

    public Product toEntity(Brand brand){
        return Product.builder()
            .brand(brand)
            .category(Category.nameOf(this.category))
            .price(this.price)
            .build();
    }
}
