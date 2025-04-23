package com.enam.bid.seller.entity.bidseller;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reference_data", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceData { // Does not extend BaseEntity

    @Id
    @Column(name = "ref_data_id") // PK is not generated identity based on DDL
    private Integer refDataId;

    @Column(name = "ref_code")
    private String refCode;

    @Column(name = "effective_start_date")
    private LocalDateTime effectiveStartDate; // Schema uses DEFAULT (CURRENT_DATE), mapping to LocalDateTime

    @Column(name = "effective_expiry_date")
    private LocalDateTime effectiveExpiryDate; // Schema uses DEFAULT (CURRENT_DATE), mapping to LocalDateTime

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_type_id", referencedColumnName = "ref_type_id")
    private ReferenceType referenceType;
}
