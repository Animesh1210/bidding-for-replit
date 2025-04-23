package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.CancellationTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancellationTermRepository extends JpaRepository<CancellationTerm, Integer> {
}