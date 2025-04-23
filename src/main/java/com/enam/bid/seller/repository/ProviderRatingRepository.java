package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ProviderRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRatingRepository extends JpaRepository<ProviderRating, Integer> {
}