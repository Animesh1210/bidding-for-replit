package com.enam.bid.seller.service;

import com.enam.bid.seller.dto.FulfillmentStopDTO;
import com.enam.bid.seller.entity.ondc.FulfillmentStop;
import com.enam.bid.seller.entity.ondc.Location;
import com.enam.bid.seller.entity.ondc.OrderFulfillment;
import com.enam.bid.seller.exception.ResourceNotFoundException;
import com.enam.bid.seller.repository.FulfillmentStopRepository;
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
public class FulfillmentStopService {

    private static final Logger logger = LoggerFactory.getLogger(FulfillmentStopService.class);

    private final FulfillmentStopRepository repository;
    private final OrderFulfillmentRepository fulfillmentRepo;

    public List<FulfillmentStopDTO> getAll() {
        try {
            logger.info("Fetching all fulfillment stops.");
            return repository.findByIsActiveTrue()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred while fetching all fulfillment stops.", e);
            throw new ResourceNotFoundException("Unable to fetch fulfillment stops.");
        }
    }

    public List<FulfillmentStopDTO> getByFulfillmentId(Integer fulfillmentId) {
        try {
            logger.info("Fetching fulfillment stops by fulfillment ID: {}", fulfillmentId);
            return repository.findByOrderFulfillment_IdAndIsActiveTrue(fulfillmentId)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching fulfillment stops for fulfillment ID: {}", fulfillmentId, e);
            throw new ResourceNotFoundException("Unable to fetch fulfillment stops for fulfillment ID: " + fulfillmentId);
        }
    }

    public FulfillmentStopDTO getById(Integer id) {
        try {
            logger.info("Fetching fulfillment stop by ID: {}", id);
            return toDTO(repository.findById(id)
                    .filter(FulfillmentStop::getIsActive)
                    .orElseThrow(() -> new ResourceNotFoundException("Fulfillment stop not found with ID: " + id)));
        } catch (Exception e) {
            logger.error("Error fetching fulfillment stop by ID: {}", id, e);
            throw new ResourceNotFoundException("Unable to fetch fulfillment stop with ID: " + id);
        }
    }

    public FulfillmentStopDTO create(FulfillmentStopDTO dto) {
        try {
            logger.info("Creating fulfillment stop with data: {}", dto);
            OrderFulfillment fulfillment = fulfillmentRepo.findById(dto.getFulfillmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order fulfillment not found with ID: " + dto.getFulfillmentId()));

            FulfillmentStop entity = toEntity(dto);
            entity.setOrderFulfillment(fulfillment);

            FulfillmentStop saved = repository.save(entity);
            return toDTO(saved);
        } catch (Exception e) {
            logger.error("Error occurred while creating fulfillment stop with data: {}", dto, e);
            throw new ResourceNotFoundException("Failed to create fulfillment stop.");
        }
    }

    public FulfillmentStopDTO update(Integer id, FulfillmentStopDTO dto) {
        try {
            logger.info("Updating fulfillment stop with ID: {} and data: {}", id, dto);
            FulfillmentStop existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Fulfillment stop not found with ID: " + id));

            existing.setType(dto.getType());
            existing.setTimeLabel(dto.getTimeLabel());
            existing.setTimeRangeStart(dto.getTimeRangeStart());
            existing.setTimeRangeEnd(dto.getTimeRangeEnd());
            existing.setContactPhone(dto.getContactPhone());
            existing.setContactEmail(dto.getContactEmail());
            existing.setPersonName(dto.getPersonName());
            existing.setInstructionsName(dto.getInstructionsName());
            existing.setInstructionsShortDesc(dto.getInstructionsShortDesc());
            existing.setAuthorizationType(dto.getAuthorizationType());
            existing.setAuthorizationToken(dto.getAuthorizationToken());
            existing.setAuthorizationValidFrom(dto.getAuthorizationValidFrom());
            existing.setAuthorizationValidTo(dto.getAuthorizationValidTo());
            existing.setAuthorizationStatus(dto.getAuthorizationStatus());

            FulfillmentStop updated = repository.save(existing);
            return toDTO(updated);
        } catch (Exception e) {
            logger.error("Error updating fulfillment stop with ID: {}", id, e);
            throw new ResourceNotFoundException("Failed to update fulfillment stop with ID: " + id);
        }
    }

    public void delete(Integer id) {
        try {
            logger.info("Deleting (soft) fulfillment stop by ID: {}", id);
            FulfillmentStop existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Fulfillment stop not found with ID: " + id));
            existing.setIsActive(false);
            repository.save(existing);
        } catch (Exception e) {
            logger.error("Error deleting fulfillment stop with ID: {}", id, e);
            throw new ResourceNotFoundException("Failed to delete fulfillment stop with ID: " + id);
        }
    }

    private FulfillmentStopDTO toDTO(FulfillmentStop e) {
        return FulfillmentStopDTO.builder()
                .id(e.getId())
                .fulfillmentId(e.getOrderFulfillment().getId())
                .type(e.getType())
                .locationId(e.getLocation() != null ? e.getLocation().getId() : null)
                .timeLabel(e.getTimeLabel())
                .timeRangeStart(e.getTimeRangeStart())
                .timeRangeEnd(e.getTimeRangeEnd())
                .contactPhone(e.getContactPhone())
                .contactEmail(e.getContactEmail())
                .personName(e.getPersonName())
                .instructionsName(e.getInstructionsName())
                .instructionsShortDesc(e.getInstructionsShortDesc())
                .authorizationType(e.getAuthorizationType())
                .authorizationToken(e.getAuthorizationToken())
                .authorizationValidFrom(e.getAuthorizationValidFrom())
                .authorizationValidTo(e.getAuthorizationValidTo())
                .authorizationStatus(e.getAuthorizationStatus())
                .isActive(e.getIsActive())
                .build();
    }

    private FulfillmentStop toEntity(FulfillmentStopDTO dto) {
        Location location = null;
        if (dto.getLocationId() != null) {
            location = new Location();
            location.setId(dto.getLocationId());
        }

        return FulfillmentStop.builder()
                .id(dto.getId())
                .type(dto.getType())
                .location(location)
                .timeLabel(dto.getTimeLabel())
                .timeRangeStart(dto.getTimeRangeStart())
                .timeRangeEnd(dto.getTimeRangeEnd())
                .contactPhone(dto.getContactPhone())
                .contactEmail(dto.getContactEmail())
                .personName(dto.getPersonName())
                .instructionsName(dto.getInstructionsName())
                .instructionsShortDesc(dto.getInstructionsShortDesc())
                .authorizationType(dto.getAuthorizationType())
                .authorizationToken(dto.getAuthorizationToken())
                .authorizationValidFrom(dto.getAuthorizationValidFrom())
                .authorizationValidTo(dto.getAuthorizationValidTo())
                .authorizationStatus(dto.getAuthorizationStatus())
                .build();
    }
}
