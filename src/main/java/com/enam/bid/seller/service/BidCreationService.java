package com.enam.bid.seller.service;

import com.enam.bid.seller.dto.BidCreationRequestDTO;
import com.enam.bid.seller.entity.bidseller.BidCreation;
import com.enam.bid.seller.entity.bidseller.enums.AuctionTypeEnum;
import com.enam.bid.seller.entity.bidseller.enums.BidStatusEnum;
import com.enam.bid.seller.repository.BidCreationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BidCreationService {

    @Autowired
    private BidCreationRepository bidCreationRepository;

    public List<BidCreation> createBid(BidCreationRequestDTO dto) {
        try {
            List<BidCreation> createdBids = new ArrayList<>();
            LocalDateTime startDateTime = LocalDateTime.of(dto.getBidStartDate(), dto.getBidStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(dto.getBidEndDate(), dto.getBidEndTime());

            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                throw new IllegalArgumentException("Bid start datetime must be before bid end datetime.");
            }

            // Check for existing lot numbers
            List<String> lotNumbers = dto.getLotId();
            List<BidCreation> existingLots = bidCreationRepository.findByLotNumberIn(lotNumbers);
            if (!existingLots.isEmpty()) {
                List<String> duplicateLots = existingLots.stream()
                        .map(BidCreation::getLotNumber)
                        .toList();
                throw new IllegalArgumentException("The following lot numbers already exist: " + duplicateLots);
            }

            for (String lotNumber : lotNumbers) {
                BidCreation bid = new BidCreation();

                bid.setBidId(UUID.randomUUID().toString());
                bid.setLotNumber(lotNumber);
                bid.setBidType(dto.getBidType());
                bid.setAuctionType(AuctionTypeEnum.ENGLISH);
                bid.setAutoAssignWinner(dto.getAutoAssignWinner());
                bid.setAllowMultiBids(dto.getAllowMultiBids());
                bid.setBidStartDate(dto.getBidStartDate());
                bid.setBidEndDate(dto.getBidEndDate());
                bid.setBidStartTime(dto.getBidStartTime());
                bid.setBidEndTime(dto.getBidEndTime());
                bid.setMinBiddersReq(dto.getMinBiddersReq());
                bid.setMinBidPrice(dto.getMinBidPrice());
                bid.setBidStatus(BidStatusEnum.CREATED);
                createdBids.add(bidCreationRepository.save(bid));
            }

            return createdBids;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public BidCreation updateBidById(Integer id, BidCreationRequestDTO dto) {
        try {
            BidCreation existingBid = bidCreationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No bid found for id: " + id));

            LocalDateTime startDateTime = LocalDateTime.of(dto.getBidStartDate(), dto.getBidStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(dto.getBidEndDate(), dto.getBidEndTime());

            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                throw new IllegalArgumentException("Bid start datetime must be before bid end datetime.");
            }

            existingBid.setBidType(dto.getBidType());
            existingBid.setAuctionType(AuctionTypeEnum.ENGLISH);
            existingBid.setAutoAssignWinner(dto.getAutoAssignWinner());
            existingBid.setAllowMultiBids(dto.getAllowMultiBids());
            existingBid.setBidStartDate(dto.getBidStartDate());
            existingBid.setBidEndDate(dto.getBidEndDate());
            existingBid.setBidStartTime(dto.getBidStartTime());
            existingBid.setBidEndTime(dto.getBidEndTime());
            existingBid.setMinBiddersReq(dto.getMinBiddersReq());
            existingBid.setMinBidPrice(dto.getMinBidPrice());

            return bidCreationRepository.save(existingBid);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public BidCreation updateBidByLotNumber(String lotNumber, BidCreationRequestDTO dto) {
        try {
            BidCreation existingBid = bidCreationRepository.findByLotNumber(lotNumber)
                    .orElseThrow(() -> new IllegalArgumentException("No bid found for lot number: " + lotNumber));

            LocalDateTime startDateTime = LocalDateTime.of(dto.getBidStartDate(), dto.getBidStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(dto.getBidEndDate(), dto.getBidEndTime());

            if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                throw new IllegalArgumentException("Bid start datetime must be before bid end datetime.");
            }

            existingBid.setBidType(dto.getBidType());
            existingBid.setAuctionType(AuctionTypeEnum.ENGLISH);
            existingBid.setAutoAssignWinner(dto.getAutoAssignWinner());
            existingBid.setAllowMultiBids(dto.getAllowMultiBids());
            existingBid.setBidStartDate(dto.getBidStartDate());
            existingBid.setBidEndDate(dto.getBidEndDate());
            existingBid.setBidStartTime(dto.getBidStartTime());
            existingBid.setBidEndTime(dto.getBidEndTime());
            existingBid.setMinBiddersReq(dto.getMinBiddersReq());
            existingBid.setMinBidPrice(dto.getMinBidPrice());

            return bidCreationRepository.save(existingBid);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    public Page<BidCreation> filterBids(String lotNumber, String bidType, LocalDate startDateFrom, LocalDate startDateTo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bidCreationRepository.filterBidsByParameters(lotNumber, bidType, startDateFrom, startDateTo, pageable);
    }
}