package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ItemLocationMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemLocationMappingRepository extends JpaRepository<ItemLocationMapping, Integer> {
}