package com.musinsa.task.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    Brand findByName(String name);
    boolean existsByName(String name);
}
