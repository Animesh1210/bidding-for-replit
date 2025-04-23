package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "order_fulfillments", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFulfillment extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "external_id", length = 50)
    private String externalId;

    @Column(name = "state_descriptor_code", length = 50)
    private String stateDescriptorCode;

    @Column(name = "rateable")
    private Boolean rateable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToMany(mappedBy = "orderFulfillment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<FulfillmentStop> fulfillmentStops = new LinkedHashSet<>();
}
