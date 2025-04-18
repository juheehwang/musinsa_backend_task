package com.musinsa.task.service;

import com.musinsa.task.dto.response.PriceResponse;
import com.musinsa.task.entity.Category;
import com.musinsa.task.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ProductRepository productRepository;

    public Map<String, Object> findLowestByCategoryWithTotal(){
        List<PriceResponse> priceResponseList = new ArrayList<>();
        int total = 0;

        for(Category category: Category.values()){
            productRepository.findByCategoryOrderByPriceAsc(category)
                .stream().findFirst().ifPresent(product -> {
                    priceResponseList.add(
                        PriceResponse.builder()
                            .price(product.getPrice())
                            .brand(product.getBrand().getName())
                            .category(product.getCategory().getKrName())
                        .build()
                    );
                });
        }

        total = priceResponseList.stream().mapToInt(PriceResponse::getPrice).sum();
       return Map.of("products",priceResponseList,"총액", total);
    }

}
