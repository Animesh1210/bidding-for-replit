package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.BuyerContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerContextRepository extends JpaRepository<BuyerContext, Integer> {
}