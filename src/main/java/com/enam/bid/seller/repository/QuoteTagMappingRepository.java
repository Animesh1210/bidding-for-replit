package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.QuoteTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteTagMappingRepository extends JpaRepository<QuoteTagMapping, Integer> {
}