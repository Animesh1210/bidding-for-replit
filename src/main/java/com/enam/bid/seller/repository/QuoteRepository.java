package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}