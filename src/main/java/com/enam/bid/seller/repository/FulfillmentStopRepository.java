package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.FulfillmentStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FulfillmentStopRepository extends JpaRepository<FulfillmentStop, Integer> {
    List<FulfillmentStop> findByIsActiveTrue();
    List<FulfillmentStop> findByOrderFulfillment_IdAndIsActiveTrue(Integer fulfillmentId);
}