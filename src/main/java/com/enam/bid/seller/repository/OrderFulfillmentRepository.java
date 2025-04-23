package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderFulfillment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFulfillmentRepository extends JpaRepository<OrderFulfillment, Integer> {
    List<OrderFulfillment> findByIsActiveTrue();
}