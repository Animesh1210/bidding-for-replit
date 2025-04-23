package com.enam.bid.seller.service;

import com.enam.bid.seller.dto.BidCreationRequestDTO;
import com.enam.bid.seller.entity.bidseller.BidCreation;
import com.enam.bid.seller.entity.bidseller.enums.AuctionTypeEnum;
import com.enam.bid.seller.entity.bidseller.enums.BidStatusEnum;
import com.enam.bid.seller.entity.bidseller.enums.BidTypeEnum;
import com.enam.bid.seller.repository.BidCreationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidCreationServiceTest {

    @Mock
    private BidCreationRepository bidCreationRepository;

    @InjectMocks
    private BidCreationService bidCreationService;

    private BidCreationRequestDTO validRequestDTO;
    private BidCreation sampleBid;

    @BeforeEach
    void setUp() {
        // Setup valid request DTO
        validRequestDTO = new BidCreationRequestDTO();
        validRequestDTO.setBidType(BidTypeEnum.OPEN);
        validRequestDTO.setAutoAssignWinner(true);
        validRequestDTO.setAllowMultiBids(true);
        validRequestDTO.setBidStartDate(LocalDate.now());
        validRequestDTO.setBidEndDate(LocalDate.now().plusDays(5));
        validRequestDTO.setBidStartTime(LocalTime.of(10, 0));
        validRequestDTO.setBidEndTime(LocalTime.of(16, 0));
        validRequestDTO.setMinBiddersReq(3);
        validRequestDTO.setMinBidPrice(BigDecimal.valueOf(1000.0));
        validRequestDTO.setLotId(Arrays.asList("LOT-001", "LOT-002"));

        // Setup sample bid
        sampleBid = new BidCreation();
        sampleBid.setId(1);
        sampleBid.setBidId("bid-uuid");
        sampleBid.setLotNumber("LOT-001");
        sampleBid.setBidType(BidTypeEnum.OPEN);
        sampleBid.setAuctionType(AuctionTypeEnum.ENGLISH);
        sampleBid.setAutoAssignWinner(true);
        sampleBid.setAllowMultiBids(true);
        sampleBid.setBidStartDate(LocalDate.now());
        sampleBid.setBidEndDate(LocalDate.now().plusDays(5));
        sampleBid.setBidStartTime(LocalTime.of(10, 0));
        sampleBid.setBidEndTime(LocalTime.of(16, 0));
        sampleBid.setMinBiddersReq(3);
        sampleBid.setMinBidPrice(BigDecimal.valueOf(1000.0));
        sampleBid.setBidStatus(BidStatusEnum.CREATED);
    }

    @Test
    void createBid_withValidData_shouldCreateBids() {
        // Arrange
        when(bidCreationRepository.findByLotNumberIn(anyList())).thenReturn(new ArrayList<>());
        when(bidCreationRepository.save(any(BidCreation.class))).thenAnswer(invocation -> {
            BidCreation savedBid = invocation.getArgument(0);
            return savedBid;
        });

        // Act
        List<BidCreation> result = bidCreationService.createBid(validRequestDTO);

        // Assert
        assertEquals(2, result.size());
        verify(bidCreationRepository, times(1)).findByLotNumberIn(anyList());
        verify(bidCreationRepository, times(2)).save(any(BidCreation.class));

        BidCreation firstBid = result.get(0);
        assertEquals(validRequestDTO.getBidType(), firstBid.getBidType());
        assertEquals(AuctionTypeEnum.ENGLISH, firstBid.getAuctionType());
        assertEquals(validRequestDTO.getAutoAssignWinner(), firstBid.getAutoAssignWinner());
        assertEquals(validRequestDTO.getAllowMultiBids(), firstBid.getAllowMultiBids());
        assertEquals(validRequestDTO.getBidStartDate(), firstBid.getBidStartDate());
        assertEquals(validRequestDTO.getBidEndDate(), firstBid.getBidEndDate());
        assertEquals(validRequestDTO.getBidStartTime(), firstBid.getBidStartTime());
        assertEquals(validRequestDTO.getBidEndTime(), firstBid.getBidEndTime());
        assertEquals(validRequestDTO.getMinBiddersReq(), firstBid.getMinBiddersReq());
        assertEquals(validRequestDTO.getMinBidPrice(), firstBid.getMinBidPrice());
        assertEquals(BidStatusEnum.CREATED, firstBid.getBidStatus());
    }

    @Test
    void createBid_withExistingLotNumbers_shouldThrowException() {
        // Arrange
        List<BidCreation> existingBids = new ArrayList<>();
        existingBids.add(sampleBid);
        when(bidCreationRepository.findByLotNumberIn(anyList())).thenReturn(existingBids);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bidCreationService.createBid(validRequestDTO);
        });

        assertTrue(exception.getMessage().contains("The following lot numbers already exist:"));
        verify(bidCreationRepository, times(1)).findByLotNumberIn(anyList());
        verify(bidCreationRepository, never()).save(any(BidCreation.class));
    }

    @Test
    void updateBidById_withValidData_shouldUpdateBid() {
        // Arrange
        Integer bidId = 1;
        when(bidCreationRepository.findById(eq(bidId))).thenReturn(Optional.of(sampleBid));
        when(bidCreationRepository.save(any(BidCreation.class))).thenReturn(sampleBid);

        // Act
        BidCreation result = bidCreationService.updateBidById(bidId, validRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(bidId, result.getId());
        assertEquals(validRequestDTO.getBidType(), result.getBidType());
        assertEquals(validRequestDTO.getBidStartDate(), result.getBidStartDate());
        verify(bidCreationRepository, times(1)).findById(eq(bidId));
        verify(bidCreationRepository, times(1)).save(any(BidCreation.class));
    }

    @Test
    void updateBidById_withNonExistentId_shouldThrowException() {
        // Arrange
        Integer bidId = 99;
        when(bidCreationRepository.findById(eq(bidId))).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bidCreationService.updateBidById(bidId, validRequestDTO);
        });

        assertTrue(exception.getMessage().contains("No bid found for id:"));
        verify(bidCreationRepository, times(1)).findById(eq(bidId));
        verify(bidCreationRepository, never()).save(any(BidCreation.class));
    }

    @Test
    void updateBidById_withInvalidDateRange_shouldThrowException() {
        // Arrange
        Integer bidId = 1;
        validRequestDTO.setBidEndDate(LocalDate.now().minusDays(1));
        when(bidCreationRepository.findById(eq(bidId))).thenReturn(Optional.of(sampleBid));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bidCreationService.updateBidById(bidId, validRequestDTO);
        });

        assertTrue(exception.getMessage().contains("Bid start datetime must be before bid end datetime"));
        verify(bidCreationRepository, times(1)).findById(eq(bidId));
        verify(bidCreationRepository, never()).save(any(BidCreation.class));
    }

    @Test
    void updateBidByLotNumber_withValidData_shouldUpdateBid() {
        // Arrange
        String lotNumber = "LOT-001";
        when(bidCreationRepository.findByLotNumber(eq(lotNumber))).thenReturn(Optional.of(sampleBid));
        when(bidCreationRepository.save(any(BidCreation.class))).thenReturn(sampleBid);

        // Act
        BidCreation result = bidCreationService.updateBidByLotNumber(lotNumber, validRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(lotNumber, result.getLotNumber());
        assertEquals(validRequestDTO.getBidType(), result.getBidType());
        assertEquals(validRequestDTO.getBidStartDate(), result.getBidStartDate());
        verify(bidCreationRepository, times(1)).findByLotNumber(eq(lotNumber));
        verify(bidCreationRepository, times(1)).save(any(BidCreation.class));
    }

    @Test
    void updateBidByLotNumber_withNonExistentLotNumber_shouldThrowException() {
        // Arrange
        String lotNumber = "NON-EXISTING-LOT";
        when(bidCreationRepository.findByLotNumber(eq(lotNumber))).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bidCreationService.updateBidByLotNumber(lotNumber, validRequestDTO);
        });

        assertTrue(exception.getMessage().contains("No bid found for lot number:"));
        verify(bidCreationRepository, times(1)).findByLotNumber(eq(lotNumber));
        verify(bidCreationRepository, never()).save(any(BidCreation.class));
    }

    @Test
    void updateBidByLotNumber_withInvalidDateRange_shouldThrowException() {
        // Arrange
        String lotNumber = "LOT-001";
        validRequestDTO.setBidEndDate(LocalDate.now().minusDays(1));
        when(bidCreationRepository.findByLotNumber(eq(lotNumber))).thenReturn(Optional.of(sampleBid));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bidCreationService.updateBidByLotNumber(lotNumber, validRequestDTO);
        });

        assertTrue(exception.getMessage().contains("Bid start datetime must be before bid end datetime"));
        verify(bidCreationRepository, times(1)).findByLotNumber(eq(lotNumber));
        verify(bidCreationRepository, never()).save(any(BidCreation.class));
    }

    @Test
    void filterBids_shouldReturnFilteredResults() {
        // Arrange
        String lotNumber = "LOT-001";
        String bidType = "FORWARD";
        LocalDate startDateFrom = LocalDate.now();
        LocalDate startDateTo = LocalDate.now().plusDays(5);
        int page = 0;
        int size = 10;

        List<BidCreation> bidList = Arrays.asList(sampleBid);
        Page<BidCreation> expected = new PageImpl<>(bidList);

        Pageable pageable = PageRequest.of(page, size);
        when(bidCreationRepository.filterBidsByParameters(
                eq(lotNumber), eq(bidType), eq(startDateFrom), eq(startDateTo), eq(pageable)))
                .thenReturn(expected);

        // Act
        Page<BidCreation> result = bidCreationService.filterBids(
                lotNumber, bidType, startDateFrom, startDateTo, page, size);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(sampleBid, result.getContent().get(0));
        verify(bidCreationRepository, times(1)).filterBidsByParameters(
                eq(lotNumber), eq(bidType), eq(startDateFrom), eq(startDateTo), eq(pageable));
    }
}