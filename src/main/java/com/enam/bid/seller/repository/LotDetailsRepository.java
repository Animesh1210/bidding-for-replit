package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.LotDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotDetailsRepository extends JpaRepository<LotDetails, Long> {
    Optional<LotDetails> findByLotNumber(String lotNumber);

    Page<LotDetails> findByBidCreatedFalse(Pageable pageable);

    @Query("SELECT l FROM LotDetails l WHERE l.bidCreated = false AND " +
            "(LOWER(l.lotNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(l.sellerName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<LotDetails> searchByLotNumberOrSellerName(@Param("searchTerm") String searchTerm, Pageable pageable);
}