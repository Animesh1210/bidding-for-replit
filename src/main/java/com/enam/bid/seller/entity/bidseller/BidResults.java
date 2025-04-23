package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid_results", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidResults extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "winning_buyer_id")
    private Integer winningBuyerId; // Assuming this refers to a buyer concept not fully defined in this schema, mapped as Integer

    @Column(name = "winning_price", precision = 10, scale = 2)
    private BigDecimal winningPrice;

    @Column(name = "is_rejected_by_seller")
    private Boolean isRejectedBySeller = false; // Default from DDL

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "declared_by", length = 20)
    private String declaredBy;

    @Column(name = "declared_at")
    private LocalDateTime declaredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id", nullable = false)
    private BidCreation bidCreation;
}
