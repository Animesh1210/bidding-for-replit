package com.enam.bid.seller.controller;

import com.enam.bid.seller.entity.bidseller.LotDetails;
import com.enam.bid.seller.repository.LotDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
@RequestMapping("/api/lots")
public class LotController {
    private static final Logger logger = LoggerFactory.getLogger(LotController.class);

    @Autowired
    private LotDetailsRepository lotDetailsRepository;

    private final String[] CATEGORIES = {"Wheat", "Rice", "Moong Daal", "Chilli", "Tomato", "Potato", "Onion"};
    private final String[] BAG_TYPES = {"30 KG", "50 KG", "25 KG", "10 KG", "15 KG"};

    private final String[] FIRST_NAMES = {"Aditya", "Priya", "Rahul", "Sneha", "Vikram", "Meera", "Arjun", "Ananya",
            "Ravi", "Nisha", "Rajesh", "Kavita", "Sanjay", "Pooja", "Amit", "Deepa",
            "Samar", "Jaydon", "Roger", "Abram", "Maria", "Ahmed", "Li", "Emma",
            "James", "Sophia", "Miguel", "Fatima", "Chen", "Olivia"};

    private final String[] LAST_NAMES = {"Singh", "Sharma", "Patel", "Gupta", "Kumar", "Shah", "Joshi", "Reddy",
            "Shrivastav", "Dias", "Franci", "Vaccaro", "Gonzalez", "Khan", "Wei", "Johnson",
            "Smith", "Wang", "Garcia", "Rodriguez", "Ali", "Kim", "Nguyen", "Tran",
            "Kapoor", "Malhotra", "Verma", "Rao", "Choudhury", "Devi"};

    @PostMapping("/generate-mock")
    public ResponseEntity<Map<String, Object>> generateMockData() {
        try {
            logger.info("Generating mock lot data");
            List<LotDetails> mockLots = new ArrayList<>();
            Random random = new Random();
            Set<String> existingLotNumbers = new HashSet<>();

            while (mockLots.size() < 30) {
                String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
                String lotNumber = "Lot-" + (1000 + random.nextInt(4000));

                if (existingLotNumbers.contains(lotNumber)) {
                    continue;
                }
                existingLotNumbers.add(lotNumber);

                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                String sellerName = firstName + " " + lastName;

                String bagType = BAG_TYPES[random.nextInt(BAG_TYPES.length)];
                int numberOfBags = 10 + random.nextInt(91);
                int quantity = numberOfBags * Integer.parseInt(bagType.split(" ")[0]);
                boolean bidCreated = false;

                LotDetails lot = new LotDetails();
                lot.setLotNumber(lotNumber);
                lot.setCategory(category);
                lot.setSellerName(sellerName);
                lot.setBagType(bagType);
                lot.setNumberOfBags(numberOfBags);
                lot.setQuantity(quantity);
                lot.setBidCreated(bidCreated);

                logger.debug("Created mock lot: {}", lot);
                mockLots.add(lot);
            }

            List<LotDetails> savedLots;
            try {
                savedLots = lotDetailsRepository.saveAll(mockLots);
            } catch (Exception e) {
                logger.error("Failed to save mock lots to database", e);
                throw new RuntimeException("Database error while saving mock lots");
            }
            logger.info("Successfully saved {} mock lots", savedLots.size());

            Map<String, Object> data = Map.of(
                    "lotIds", savedLots.stream().map(LotDetails::getLotNumber).toArray(),
                    "status", "created"
            );

            Map<String, Object> response = Map.of(
                    "success", true,
                    "message", "Lots created successfully",
                    "data", data
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error generating mock data", e);
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Failed to generate mock data: " + e.getMessage(),
                    "data", Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAvailableLots(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, name = "search_by_lot_or_name") String searchTerm) {
        try {
            if (page < 0 || size <= 0) {
                throw new IllegalArgumentException("Invalid pagination parameters");
            }

            logger.info("Fetching available lots - Page: {}, Size: {}, Search term: {}", page, size, searchTerm);
            Pageable pageable = PageRequest.of(page, size);

            Page<LotDetails> lotsPage;
            try {
                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    lotsPage = lotDetailsRepository.searchByLotNumberOrSellerName(searchTerm, pageable);
                } else {
                    lotsPage = lotDetailsRepository.findByBidCreatedFalse(pageable);
                }
            } catch (Exception e) {
                logger.error("Database error while fetching lots", e);
                throw new RuntimeException("Database error while fetching lots");
            }

            List<Map<String, Object>> items = new ArrayList<>();
            for (LotDetails lot : lotsPage.getContent()) {
                Map<String, Object> lotMap = Map.of(
                        "lotId", lot.getLotNumber(),
                        "lotCategory", lot.getCategory(),
                        "sellerName", lot.getSellerName(),
                        "bagType", lot.getBagType(),
                        "numberOfBags", lot.getNumberOfBags(),
                        "quantity", lot.getQuantity(),
                        "bidCreated", lot.isBidCreated()
                );
                items.add(lotMap);
            }

            logger.info("Fetched {} lots", lotsPage.getContent().size());

            Map<String, Object> pagination = Map.of(
                    "page", lotsPage.getNumber(),
                    "size", lotsPage.getSize(),
                    "totalItems", lotsPage.getTotalElements(),
                    "totalPages", lotsPage.getTotalPages()
            );

            Map<String, Object> response = Map.of(
                    "success", true,
                    "message", "Lots fetched",
                    "data", Map.of(
                            "items", items,
                            "pagination", pagination
                    )
            );

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid input parameters", e);
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Invalid input: " + e.getMessage(),
                    "data", Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("Error fetching lots", e);
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Failed to fetch lots: " + e.getMessage(),
                    "data", Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update-bid-status-by-numbers")
    public ResponseEntity<Map<String, Object>> updateBidStatusByLotNumbers(@RequestBody List<String> lotNumbers) {
        try {
            if (lotNumbers == null || lotNumbers.isEmpty()) {
                throw new IllegalArgumentException("Lot numbers list cannot be null or empty");
            }

            logger.info("Received request to update bid status for lot numbers: {}", lotNumbers);

            List<String> updatedLots = new ArrayList<>();
            List<String> alreadyTrueLots = new ArrayList<>();
            List<String> notFoundLots = new ArrayList<>();

            for (String lotNumber : lotNumbers) {
                if (lotNumber == null || lotNumber.trim().isEmpty()) {
                    continue;
                }
                logger.debug("Processing lot number: {}", lotNumber);
                try {
                    Optional<LotDetails> optionalLot = lotDetailsRepository.findByLotNumber(lotNumber);

                    if (optionalLot.isPresent()) {
                        LotDetails lot = optionalLot.get();
                        if (lot.isBidCreated()) {
                            logger.warn("Bid already created for lot number: {}", lotNumber);
                            alreadyTrueLots.add(lotNumber);
                        } else {
                            lot.setBidCreated(true);
                            lotDetailsRepository.save(lot);
                            updatedLots.add(lotNumber);
                            logger.info("Bid status updated for lot number: {}", lotNumber);
                        }
                    } else {
                        notFoundLots.add(lotNumber);
                        logger.warn("Lot number not found: {}", lotNumber);
                    }
                } catch (Exception e) {
                    logger.error("Error processing lot number: {}", lotNumber, e);
                    notFoundLots.add(lotNumber);
                }
            }

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            data.put("updatedLots", updatedLots);
            data.put("alreadyTrueLots", alreadyTrueLots);
            data.put("notFoundLots", notFoundLots);

            if (!updatedLots.isEmpty()) {
                response.put("success", true);
                response.put("message", "Lot update operation completed successfully");
                response.put("data", data);
                return ResponseEntity.ok(response);
            } else if (!alreadyTrueLots.isEmpty() || !notFoundLots.isEmpty()) {
                response.put("success", false);
                response.put("message", "Bid update operation failed for some lots");
                response.put("data", data);
                response.put("errorCode", "PARTIAL_FAILURE");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                response.put("success", false);
                response.put("message", "No valid updates performed");
                response.put("data", data);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid input parameters", e);
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Invalid input: " + e.getMessage(),
                    "data", Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("Error updating bid status", e);
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", "Failed to update bid status: " + e.getMessage(),
                    "data", Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}