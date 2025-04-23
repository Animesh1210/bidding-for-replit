package com.enam.bid.seller.controller;

import com.enam.bid.seller.dto.FulfillmentStopDTO;
import com.enam.bid.seller.exception.ResourceNotFoundException;
import com.enam.bid.seller.service.FulfillmentStopService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fulfillment-stops")
@RequiredArgsConstructor
public class FulfillmentStopController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FulfillmentStopController.class);

    private final FulfillmentStopService service;

    @GetMapping
    public List<FulfillmentStopDTO> getAll() {
        try {
            logger.info("Fetching all fulfillment stops...");
            return service.getAll();
        } catch (Exception e) {
            logger.error("Failed to fetch all fulfillment stops", e);
            throw new ResourceNotFoundException("Unable to retrieve fulfillment stops at the moment.");
        }
    }

    @GetMapping("/{id}")
    public FulfillmentStopDTO getById(@PathVariable Integer id) {
        try {
            logger.info("Fetching fulfillment stop with ID: {}", id);
            return service.getById(id);
        } catch (Exception e) {
            logger.error("Failed to fetch fulfillment stop with ID: {}", id, e);
            throw new ResourceNotFoundException("Fulfillment stop not found with ID: " + id);
        }
    }

    @GetMapping("/fulfillment/{fulfillmentId}")
    public List<FulfillmentStopDTO> getByFulfillment(@PathVariable Integer fulfillmentId) {
        try {
            logger.info("Fetching fulfillment stops for fulfillment ID: {}", fulfillmentId);
            return service.getByFulfillmentId(fulfillmentId);
        } catch (Exception e) {
            logger.error("Failed to fetch fulfillment stops for fulfillment ID: {}", fulfillmentId, e);
            throw new ResourceNotFoundException("Unable to retrieve fulfillment stops for fulfillment ID: " + fulfillmentId);
        }
    }

    @PostMapping
    public FulfillmentStopDTO create(@RequestBody FulfillmentStopDTO dto) {
        try {
            logger.info("Creating a new fulfillment stop with details: {}", dto);
            return service.create(dto);
        } catch (Exception e) {
            logger.error("Failed to create fulfillment stop. Request body: {}", dto, e);
            throw new ResourceNotFoundException("Unable to create fulfillment stop.");
        }
    }

    @PutMapping("/{id}")
    public FulfillmentStopDTO update(@PathVariable Integer id, @RequestBody FulfillmentStopDTO dto) {
        try {
            logger.info("Updating fulfillment stop with ID: {}. New details: {}", id, dto);
            return service.update(id, dto);
        } catch (Exception e) {
            logger.error("Failed to update fulfillment stop with ID: {}", id, e);
            throw new ResourceNotFoundException("Unable to update fulfillment stop with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            logger.info("Soft deleting fulfillment stop with ID: {}", id);
            service.delete(id);
        } catch (Exception e) {
            logger.error("Failed to delete fulfillment stop with ID: {}", id, e);
            throw new ResourceNotFoundException("Unable to delete fulfillment stop with ID: " + id);
        }
    }
}
