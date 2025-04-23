package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {
}