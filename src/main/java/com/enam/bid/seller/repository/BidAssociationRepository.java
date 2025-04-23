package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.BidAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidAssociationRepository extends JpaRepository<BidAssociation, Integer> {
}