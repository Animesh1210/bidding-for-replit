package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ItemCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryMappingRepository extends JpaRepository<ItemCategoryMapping, Integer> {
}