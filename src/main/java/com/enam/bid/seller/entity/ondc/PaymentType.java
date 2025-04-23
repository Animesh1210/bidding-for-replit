package com.enam.bid.seller.entity.ondc;

import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "payment_type", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentType { // No audit fields, only is_active

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "payment_id", unique = true)
    private String paymentId; // Business key?

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemPaymentMapping> itemMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderPayment> orderPayments = new LinkedHashSet<>();
}
