package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import com.enam.bid.seller.entity.bidseller.BidAssociation;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "provider_item", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderItem extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item_id", length = 50)
    private String itemId; // Business key?

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "short_desc", columnDefinition = "text")
    private String shortDesc;

    @Column(name = "long_desc", columnDefinition = "text")
    private String longDesc;

    @Column(name = "price_currency", length = 10)
    private String priceCurrency;

    @Column(name = "price_value", precision = 15, scale = 2)
    private BigDecimal priceValue;

    @Column(name = "unitized_unit", length = 20)
    private String unitizedUnit;

    @Column(name = "unitized_value", precision = 15, scale = 2)
    private BigDecimal unitizedValue;

    @Column(name = "available_measure_unit", length = 20)
    private String availableMeasureUnit;

    @Column(name = "available_measure_value", precision = 15, scale = 2)
    private BigDecimal availableMeasureValue;

    @Column(name = "available_count")
    private Integer availableCount;

    @Column(name = "minimum_measure_unit", length = 20)
    private String minimumMeasureUnit;

    @Column(name = "minimum_measure_value", precision = 15, scale = 2)
    private BigDecimal minimumMeasureValue;

    @Column(name = "minimum_count")
    private Integer minimumCount;

    @Column(name = "category_id", length = 10) // Not a direct FK per DDL, maybe denormalized?
    private String categoryId;

    @Column(name = "matched")
    private Boolean matched;

    @Column(name = "recommended")
    private Boolean recommended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Creator creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @OneToMany(mappedBy = "bidItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BidAssociation> bidAssociations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemMedia> media = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemCategoryMapping> categoryMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemLocationMapping> locationMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemPaymentMapping> paymentMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemTagMapping> tagMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "providerItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QuoteBreakup> quoteBreakups = new LinkedHashSet<>();

}
