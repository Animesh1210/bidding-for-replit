package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}