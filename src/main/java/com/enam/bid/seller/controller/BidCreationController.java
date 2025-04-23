package com.enam.bid.seller.controller;

import com.enam.bid.seller.dto.BidCreationRequestDTO;
import com.enam.bid.seller.entity.bidseller.BidCreation;
import com.enam.bid.seller.model.*;
import com.enam.bid.seller.service.BidCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bids")
public class BidCreationController {
    private static final Logger logger = LoggerFactory.getLogger(BidCreationController.class);

    @Autowired
    private BidCreationService bidCreationService;

    @PostMapping
    public ResponseModal createBids(@Validated @RequestBody BidCreationRequestDTO dto) {
        logger.info("Received request to create bid: {}", dto);
        try {
            List<BidCreation> createdBids = bidCreationService.createBid(dto);
            BidCreation firstBid = createdBids.get(0);

            logger.info("Successfully created bid for lotNumber: {}", firstBid.getLotNumber());

            Map<String, Object> data = new HashMap<>();
            data.put("lotId", firstBid.getLotNumber());
            data.put("status", firstBid.getBidStatus().toString().toLowerCase());

            return new ResponseModal(true, "Bid created successfully", data,  null);

        } catch (Exception e) {
            logger.error("Error creating bid: {}", e.getMessage(), e);
            return new ResponseModal(false, "Bid Creation Failed: " ,  null, "Failed to Create bid"+ e.getMessage());
        }
    }


    @GetMapping
    public ResponseModal filterBids(
            @RequestParam(required = false) String lotId,
            @RequestParam(required = false) String bidType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        logger.info("Filtering bids with params: lotId={}, bidType={}, startDateFrom={}, startDateTo={}, page={}, size={}",
                lotId, bidType, startDateFrom, startDateTo, page, size);
        try {
            Page<BidCreation> bids = bidCreationService.filterBids(lotId, bidType, startDateFrom, startDateTo, page, size);

            logger.debug("Filtered bids count: {}", bids.getTotalElements());
            Map<String, Object> data = new HashMap<>();
            data.put("items", bids.getContent());

            Map<String, Object> pagination = new HashMap<>();
            pagination.put("page", bids.getNumber() + 1);
            pagination.put("size", bids.getSize());
            pagination.put("totalItems", bids.getTotalElements());
            pagination.put("totalPages", bids.getTotalPages());
            data.put("pagination", pagination);
            
            return new ResponseModal(true, "Bid fetched successfully", data ,null);
        } catch (Exception e) {
            logger.error("Error creating bid: {}", e.getMessage(), e);
            return new ResponseModal(false, "Bid Fetching Failed: ", null, "Failed to Fetch bid" + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseModal updateBidById(@PathVariable Integer id, @Validated @RequestBody BidCreationRequestDTO dto) {
        logger.info("Received update request for bid id: {} with data: {}", id, dto);
        try {
            BidCreation updatedBid = bidCreationService.updateBidById(id, dto);
            logger.info("Successfully updated bid for id: {}", id);

            Map<String, Object> data = new HashMap<>();
            data.put("id", updatedBid.getId());
            data.put("status", "Updated");;

            return new ResponseModal(true, "Bid updated successfully", data, null);
        }catch (Exception e) {
            logger.error("Error updating bid: {}", e.getMessage(), e);
            return new ResponseModal(false, "Bid Updation Failed: " , null, "Failed to Update bid"+ e.getMessage());
        }
    }
}
