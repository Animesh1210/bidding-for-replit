package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.BidResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidResultsRepository extends JpaRepository<BidResults, Integer> {
}