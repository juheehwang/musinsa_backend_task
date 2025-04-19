package com.musinsa.task.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class LowestBrandTotalResponse {

    @JsonProperty(value = "카테고리")
    private List<BrandResponse> brandResponseList;

    @JsonProperty(value = "브랜드")
    private String brand;

    @JsonProperty(value = "총액")
    private int total;

}
