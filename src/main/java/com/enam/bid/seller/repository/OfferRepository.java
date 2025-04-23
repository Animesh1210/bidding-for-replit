package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}