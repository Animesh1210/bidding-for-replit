package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}