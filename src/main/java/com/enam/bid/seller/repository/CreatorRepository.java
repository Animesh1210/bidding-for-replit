package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
}