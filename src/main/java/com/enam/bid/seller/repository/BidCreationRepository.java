package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.bidseller.BidCreation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BidCreationRepository extends JpaRepository<BidCreation, Integer> {
    List<BidCreation> findByLotNumberIn(List<String> lotNumbers);
    Optional<BidCreation> findByLotNumber(String lotNumber);

    @Query("SELECT b FROM BidCreation b WHERE " +
            "b.isActive = true AND " +
            "(:lotNumber IS NULL OR b.lotNumber = :lotNumber) AND " +
            "(:bidType IS NULL OR str(b.bidType) = :bidType) AND " +
            "(COALESCE(:startDateFrom, null) IS NULL OR b.bidStartDate >= :startDateFrom) AND " +
            "(COALESCE(:startDateTo, null) IS NULL OR b.bidStartDate <= :startDateTo)")
    Page<BidCreation> filterBidsByParameters(
            @Param("lotNumber") String lotid,
            @Param("bidType") String bidType,
            @Param("startDateFrom") LocalDate startDateFrom,
            @Param("startDateTo") LocalDate startDateTo,
            Pageable pageable);
}