package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderBilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBillingRepository extends JpaRepository<OrderBilling, Integer> {
}