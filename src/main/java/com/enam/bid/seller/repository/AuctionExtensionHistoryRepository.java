package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.AuctionExtensionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionExtensionHistoryRepository extends JpaRepository<AuctionExtensionHistory, Integer> {
}