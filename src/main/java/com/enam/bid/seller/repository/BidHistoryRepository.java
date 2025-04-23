package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidHistoryRepository extends JpaRepository<BidHistory, Integer> {
}