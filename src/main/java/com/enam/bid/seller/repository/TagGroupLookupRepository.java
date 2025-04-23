package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.TagGroupLookup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagGroupLookupRepository extends JpaRepository<TagGroupLookup, Integer> {
}