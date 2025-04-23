package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cancellation_terms", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationTerm extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fulfillment_state_descriptor_code", length = 50)
    private String fulfillmentStateDescriptorCode;

    @Column(name = "reason_required")
    private Boolean reasonRequired;

    @Column(name = "cancellation_fee_percentage", length = 10)
    private String cancellationFeePercentage;

    @Column(name = "cancellation_fee_amount_currency", length = 10)
    private String cancellationFeeAmountCurrency;

    @Column(name = "cancellation_fee_amount_value", precision = 15, scale = 2)
    private BigDecimal cancellationFeeAmountValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @OneToMany(mappedBy = "cancellationTerm", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderCancellationTermMapping> orderMappings = new LinkedHashSet<>();
}
