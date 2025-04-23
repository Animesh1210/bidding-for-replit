package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auction_extension_history", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionExtensionHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "original_end_time", nullable = false)
    private LocalDateTime originalEndTime;

    @Column(name = "new_end_time", nullable = false)
    private LocalDateTime newEndTime;

    @Column(name = "extension_reason")
    private String extensionReason;

    @Column(name = "extended_by")
    private String extendedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id", nullable = false)
    private BidCreation bidCreation;
}
