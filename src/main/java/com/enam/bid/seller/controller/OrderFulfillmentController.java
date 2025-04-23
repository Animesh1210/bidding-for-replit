package com.enam.bid.seller.controller;

import com.enam.bid.seller.dto.OrderFulfillmentDTO;
import com.enam.bid.seller.exception.ResourceNotFoundException;
import com.enam.bid.seller.service.OrderFulfillmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-fulfillments")
@RequiredArgsConstructor
public class OrderFulfillmentController {

    private static final Logger logger = LoggerFactory.getLogger(OrderFulfillmentController.class);

    private final OrderFulfillmentService service;

    @GetMapping
    public List<OrderFulfillmentDTO> getAll() {
        try {
            logger.info("Request received: Fetching all order fulfillments.");
            return service.getAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all order fulfillments.", e);
            throw new ResourceNotFoundException("Unable to retrieve order fulfillments at this time.");
        }
    }

    @GetMapping("/{id}")
    public OrderFulfillmentDTO getById(@PathVariable Integer id) {
        try {
            logger.info("Request received: Fetching order fulfillment by ID: {}", id);
            return service.getById(id);
        } catch (Exception e) {
            logger.error("Error occurred while fetching order fulfillment with ID: {}", id, e);
            throw new ResourceNotFoundException("Order fulfillment not found with ID: " + id);
        }
    }

    @PostMapping
    public OrderFulfillmentDTO create(@RequestBody OrderFulfillmentDTO dto) {
        try {
            logger.info("Request received: Creating a new order fulfillment with data: {}", dto);
            return service.create(dto);
        } catch (Exception e) {
            logger.error("Error occurred while creating order fulfillment. Payload: {}", dto, e);
            throw new ResourceNotFoundException("Failed to create order fulfillment.");
        }
    }

    @PutMapping("/{id}")
    public OrderFulfillmentDTO update(@PathVariable Integer id, @RequestBody OrderFulfillmentDTO dto) {
        try {
            logger.info("Request received: Updating order fulfillment with ID: {} and data: {}", id, dto);
            return service.update(id, dto);
        } catch (Exception e) {
            logger.error("Error occurred while updating order fulfillment with ID: {}", id, e);
            throw new ResourceNotFoundException("Failed to update order fulfillment with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            logger.info("Request received: Soft deleting order fulfillment with ID: {}", id);
            service.delete(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting order fulfillment with ID: {}", id, e);
            throw new ResourceNotFoundException("Failed to delete order fulfillment with ID: " + id);
        }
    }
}
