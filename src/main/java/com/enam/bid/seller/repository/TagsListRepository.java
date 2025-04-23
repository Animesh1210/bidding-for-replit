package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.TagsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsListRepository extends JpaRepository<TagsList, Integer> {
}