package com.enam.bid.seller.entity.bidseller;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lot_details", schema = "bid_seller")
public class LotDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lotNumber;
    private String category;
    private String sellerName;
    private String bagType;
    private int numberOfBags;
    private int quantity;
    private boolean bidCreated = false;
}