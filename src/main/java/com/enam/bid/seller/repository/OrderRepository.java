package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}