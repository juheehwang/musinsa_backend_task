package com.musinsa.task.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    @JsonProperty(value = "브랜드")
    private String brand;

    @JsonProperty(value = "가격")
    private int price;

}
