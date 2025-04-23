package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "offers", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "short_desc", columnDefinition = "text")
    private String shortDesc;

    @Column(name = "long_desc", columnDefinition = "text")
    private String longDesc;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "offer_start_date")
    private LocalDateTime offerStartDate;

    @Column(name = "offer_end_date")
    private LocalDateTime offerEndDate;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderItem> providerItems = new LinkedHashSet<>();
}
