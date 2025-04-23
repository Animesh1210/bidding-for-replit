package com.enam.bid.seller.entity.ondc; // Assuming same package structure

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import com.enam.bid.seller.entity.ondc.enums.FulfillmentTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fulfillments", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fulfillments extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fulfillment_id", unique = true, nullable = false, length = 50)
    private String fulfillmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FulfillmentTypeEnum fulfillmentType;
}