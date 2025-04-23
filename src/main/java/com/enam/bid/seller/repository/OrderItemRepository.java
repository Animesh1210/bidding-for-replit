package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}