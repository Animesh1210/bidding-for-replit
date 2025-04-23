package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "quote_breakups", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteBreakup extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "price_currency", length = 10)
    private String priceCurrency;

    @Column(name = "price_value", precision = 15, scale = 2)
    private BigDecimal priceValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", referencedColumnName = "id")
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ProviderItem providerItem;

    @OneToMany(mappedBy = "quoteBreakup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QuoteBreakupsTagMapping> tagMappings = new LinkedHashSet<>();
}
