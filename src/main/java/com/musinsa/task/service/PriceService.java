package com.musinsa.task.service;

import com.musinsa.task.dto.response.BrandResponse;
import com.musinsa.task.dto.response.CategoryResponse;
import com.musinsa.task.dto.response.LowestBrandTotalResponse;
import com.musinsa.task.dto.response.PriceResponse;
import com.musinsa.task.entity.Brand;
import com.musinsa.task.entity.BrandRepository;
import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import com.musinsa.task.entity.ProductRepository;
import com.musinsa.task.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final static String Total_Price = "총액";
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;


    public Map<String, Object> findLowestByCategoryWithTotal(){
        List<PriceResponse> priceResponseList = new ArrayList<>();

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

       return Map.of("products",priceResponseList,
           Total_Price, priceResponseList.stream().mapToInt(PriceResponse::getPrice).sum()
       );
    }

    public Map<String, Object> findLowestByBrandWithTotal() {
        Brand brand = brandRepository.findAll().stream()
            .min(Comparator.comparingInt(a -> a.getProducts().stream()
                .mapToInt(Product::getPrice).sum()))
            .orElseThrow(() -> new NotFoundException("브랜드가 존재하지 않습니다"));

        List<BrandResponse> brandResponseList = brand.getProducts().stream()
            .map(product -> BrandResponse.builder()
                .category(product.getCategory().getKrName())
                .price(product.getPrice())
                .build()).toList();

        return Map.of("최저가", LowestBrandTotalResponse.builder().brand(brand.getName())
            .brandResponseList(brandResponseList)
            .total(brandResponseList.stream().mapToInt(BrandResponse::getPrice).sum())
            .build()
        );
    }

    public Map<String,Object> findCategoryMinMaxPriceInfo(String category) {
        if(category == null || category.isEmpty()){
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }

        if(Category.nameOf(category) == null){
            throw new NotFoundException("존재하지않는 카테고리입니다.");
        }

        List<CategoryResponse> minProducts = new ArrayList<>();
        List<CategoryResponse> maxProducts = new ArrayList<>();

        productRepository.findByCategoryOrderByPriceAsc(Category.nameOf(category))
            .stream().findFirst().ifPresent(product ->
                minProducts.add(CategoryResponse.builder()
                    .price(product.getPrice())
                    .brand(product.getBrand().getName())
                    .build())
            );
        productRepository.findByCategoryOrderByPriceDesc(Category.nameOf(category))
            .stream().findFirst().ifPresent(product ->
                maxProducts.add(CategoryResponse.builder()
                    .price(product.getPrice())
                    .brand(product.getBrand().getName())
                    .build())
            );

        return Map.of("카테고리",category, "최저가",minProducts,"최고가", maxProducts);

    }
}
