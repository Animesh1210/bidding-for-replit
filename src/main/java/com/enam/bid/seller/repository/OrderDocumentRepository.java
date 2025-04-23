package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.OrderDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDocumentRepository extends JpaRepository<OrderDocument, Integer> {
}