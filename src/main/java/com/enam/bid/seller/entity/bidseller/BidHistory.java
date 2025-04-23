package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.ondc.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid_history", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bid_price")
    private BigDecimal bidPrice;

    @Column(name = "bid_placed_timestamp")
    private LocalDateTime bidPlacedTimestamp;

    @Column(name = "bid_accepted")
    private Boolean bidAccepted;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id", nullable = false)
    private BidCreation bidCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
