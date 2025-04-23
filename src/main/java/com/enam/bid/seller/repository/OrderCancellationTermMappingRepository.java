package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderCancellationTermMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancellationTermMappingRepository extends JpaRepository<OrderCancellationTermMapping, Integer> {
}