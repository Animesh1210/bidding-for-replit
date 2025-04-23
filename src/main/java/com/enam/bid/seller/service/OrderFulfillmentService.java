package com.enam.bid.seller.service;

import com.enam.bid.seller.dto.OrderFulfillmentDTO;
import com.enam.bid.seller.entity.ondc.Order;
import com.enam.bid.seller.entity.ondc.OrderFulfillment;
import com.enam.bid.seller.exception.ResourceNotFoundException;
import com.enam.bid.seller.repository.OrderFulfillmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderFulfillmentService {
    private static final Logger logger = LoggerFactory.getLogger(OrderFulfillmentService.class);
    private final OrderFulfillmentRepository repository;

    public List<OrderFulfillmentDTO> getAll() {
        try {
            logger.info("Fetching all Order Fulfillment entries");
            return repository.findByIsActiveTrue()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("Failed to fetch all Order Fulfillment entries", ex);
            throw new RuntimeException("Unable to fetch data at this time. Please try again later.");
        }
    }

    public OrderFulfillmentDTO getById(Integer id) {
        try {
            logger.info("Fetching Order Fulfillment with ID: {}", id);
            OrderFulfillment fulfillment = repository.findById(id)
                    .filter(OrderFulfillment::getIsActive)
                    .orElseThrow(() -> new ResourceNotFoundException("Order Fulfillment not found with ID " + id));
            return toDTO(fulfillment);
        } catch (ResourceNotFoundException rnfe) {
            throw rnfe;
        } catch (Exception ex) {
            logger.error("Error while fetching Order Fulfillment with ID: {}", id, ex);
            throw new RuntimeException("Unable to fetch fulfillment details. Please try again.");
        }
    }

    public OrderFulfillmentDTO create(OrderFulfillmentDTO dto) {
        try {
            logger.info("Creating Order Fulfillment");
            OrderFulfillment entity = toEntity(dto);
            return toDTO(repository.save(entity));
        } catch (Exception ex) {
            logger.error("Error while creating Order Fulfillment", ex);
            throw new RuntimeException("Unable to create fulfillment. Please check the data and try again.");
        }
    }

    public OrderFulfillmentDTO update(Integer id, OrderFulfillmentDTO dto) {
        try {
            logger.info("Updating Order Fulfillment with ID: {}", id);
            OrderFulfillment existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order Fulfillment not found with ID " + id));

            existing.setExternalId(dto.getExternalId());
            existing.setStateDescriptorCode(dto.getStateDescriptorCode());
            existing.setRateable(dto.getRateable());

            if (dto.getOrderId() != null) {
                Order order = new Order();
                order.setId(dto.getOrderId());
                existing.setOrder(order);
            } else {
                existing.setOrder(null);
            }

            return toDTO(repository.save(existing));
        } catch (ResourceNotFoundException rnfe) {
            throw rnfe;
        } catch (Exception ex) {
            logger.error("Error while updating Order Fulfillment with ID: {}", id, ex);
            throw new RuntimeException("Update failed. Please try again.");
        }
    }

    public void delete(Integer id) {
        try {
            logger.info("Deleting Order Fulfillment with ID: {}", id);
            OrderFulfillment existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order Fulfillment not found with ID " + id));
            existing.setIsActive(false);
            repository.save(existing);
        } catch (ResourceNotFoundException rnfe) {
            throw rnfe;
        } catch (Exception ex) {
            logger.error("Error while deleting Order Fulfillment with ID: {}", id, ex);
            throw new RuntimeException("Delete operation failed. Please try again.");
        }
    }

    private OrderFulfillmentDTO toDTO(OrderFulfillment entity) {
        return OrderFulfillmentDTO.builder()
                .id(entity.getId())
                .orderId(entity.getOrder() != null ? entity.getOrder().getId() : null)
                .externalId(entity.getExternalId())
                .stateDescriptorCode(entity.getStateDescriptorCode())
                .rateable(entity.getRateable())
                .isActive(entity.getIsActive())
                .build();
    }

    private OrderFulfillment toEntity(OrderFulfillmentDTO dto) {
        Order order = null;
        if (dto.getOrderId() != null) {
            order = new Order();
            order.setId(dto.getOrderId());
        }

        return OrderFulfillment.builder()
                .id(dto.getId())
                .order(order)
                .externalId(dto.getExternalId())
                .stateDescriptorCode(dto.getStateDescriptorCode())
                .rateable(dto.getRateable())
                .build();
    }
}
