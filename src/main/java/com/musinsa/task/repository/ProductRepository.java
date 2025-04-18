package com.musinsa.task.repository;

import com.musinsa.task.entity.Category;
import com.musinsa.task.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryOrderByPriceAsc(Category category);

}
