package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}