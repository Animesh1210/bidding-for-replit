package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTagMappingRepository extends JpaRepository<OrderTagMapping, Integer> {
}