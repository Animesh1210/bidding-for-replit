package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity_selected_count")
    private Integer quantitySelectedCount;

    @Column(name = "price_currency", length = 10)
    private String priceCurrency;

    @Column(name = "price_offered_value", precision = 15, scale = 2)
    private BigDecimal priceOfferedValue;

    @Column(name = "price_maximum_value", precision = 15, scale = 2)
    private BigDecimal priceMaximumValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ProviderItem providerItem;
}
