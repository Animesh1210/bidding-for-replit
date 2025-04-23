package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ProviderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderItemRepository extends JpaRepository<ProviderItem, Integer> {
}