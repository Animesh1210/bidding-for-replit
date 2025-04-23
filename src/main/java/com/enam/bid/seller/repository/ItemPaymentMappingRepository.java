package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ItemPaymentMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPaymentMappingRepository extends JpaRepository<ItemPaymentMapping, Integer> {
}