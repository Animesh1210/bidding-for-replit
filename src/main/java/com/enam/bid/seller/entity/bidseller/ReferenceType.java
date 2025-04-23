package com.enam.bid.seller.entity.bidseller;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "reference_type", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceType { // Does not extend BaseEntity

    @Id
    @Column(name = "ref_type_id") // PK is not generated identity based on DDL
    private Integer refTypeId;

    @Column(name = "ref_type_description")
    private String refTypeDescription;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "referenceType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ReferenceData> referenceData = new LinkedHashSet<>();
}
