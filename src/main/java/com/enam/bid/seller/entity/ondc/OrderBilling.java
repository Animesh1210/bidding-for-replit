package com.enam.bid.seller.entity.ondc;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_billing", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBilling { // Has only created/updated_at

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "billing_name", length = 255)
    private String billingName;

    @Column(name = "billing_address", columnDefinition = "text")
    private String billingAddress;

    @Column(name = "billing_state_name", length = 100)
    private String billingStateName;

    @Column(name = "billing_city_name", length = 100)
    private String billingCityName;

    @Column(name = "billing_tax_id", length = 50)
    private String billingTaxId;

    @Column(name = "billing_email", length = 255)
    private String billingEmail;

    @Column(name = "billing_phone", length = 20)
    private String billingPhone;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
