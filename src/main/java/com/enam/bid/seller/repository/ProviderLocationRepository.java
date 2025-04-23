package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ProviderLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderLocationRepository extends JpaRepository<ProviderLocation, Integer> {
}