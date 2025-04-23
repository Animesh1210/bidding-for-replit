package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}