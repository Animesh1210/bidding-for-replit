package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.base.BaseEntity;
import com.enam.bid.seller.entity.bidseller.enums.AuctionLocationTypeEnum;
import com.enam.bid.seller.entity.ondc.Provider;
import com.enam.bid.seller.entity.ondc.ProviderItem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bid_association", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidAssociation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lot_code", nullable = false)
    private Integer lotCode;

    @Column(name = "apmc_id")
    private String apmcId;

    @Enumerated(EnumType.STRING)
    @Column(name = "auction_location_type")
    private AuctionLocationTypeEnum auctionLocationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id", nullable = false)
    private BidCreation bidCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_provider", referencedColumnName = "id")
    private Provider bidProvider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_item", referencedColumnName = "id")
    private ProviderItem bidItem;
}
