package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ItemTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTagMappingRepository extends JpaRepository<ItemTagMapping, Integer> {
}