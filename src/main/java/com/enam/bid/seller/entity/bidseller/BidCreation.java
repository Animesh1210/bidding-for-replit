package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.base.BaseEntity;
import com.enam.bid.seller.entity.bidseller.enums.BidStatusEnum;
import com.enam.bid.seller.entity.bidseller.enums.BidTypeEnum;
import com.enam.bid.seller.entity.bidseller.enums.AuctionTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bid_creation", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidCreation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bid_id", unique = true)
    private String bidId;

    @Column(name = "lot_number")
    private String lotNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "bid_type")
    private BidTypeEnum bidType;

    @Enumerated(EnumType.STRING)
    @Column(name = "auction_type")
    private AuctionTypeEnum auctionType = AuctionTypeEnum.ENGLISH;


    @Column(name = "auto_assign_winner")
    private Boolean autoAssignWinner;

    @Column(name = "allow_multi_bids")
    private Boolean allowMultiBids;

    @Column(name = "bid_start_date",nullable = false)
    private LocalDate bidStartDate;

    @Column(name = "bid_end_date",nullable = false)
    private LocalDate bidEndDate;

    @Column(name = "bid_start_time",nullable = false)
    private LocalTime bidStartTime;

    @Column(name = "bid_end_time",nullable = false)
    private LocalTime bidEndTime;

    @Column(name = "min_bidders_req", nullable = false)
    private Integer minBiddersReq = 1;

    @Column(name = "min_bid_price")
    private BigDecimal minBidPrice;

//    @Column(name = "label")
//    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "bid_status")
    private BidStatusEnum bidStatus = BidStatusEnum.CREATED;

    @OneToMany(mappedBy = "bidCreation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BidAssociation> bidAssociations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bidCreation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BidResults> bidResults = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bidCreation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BidHistory> bidHistories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bidCreation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AuctionExtensionHistory> auctionExtensionHistories = new LinkedHashSet<>();
}
