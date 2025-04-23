package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.bidseller.BidAssociation;
import com.enam.bid.seller.entity.bidseller.ProviderExtension;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "providers", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provider { // Has all audit fields but no is_active, added directly

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "provider_id", length = 50, unique = true)
    private String providerId; // Business key

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "short_desc", columnDefinition = "text")
    private String shortDesc;

    @Column(name = "long_desc", columnDefinition = "text")
    private String longDesc;

    @Column(name = "additional_desc_url", length = 255)
    private String additionalDescUrl;

    @Column(name = "additional_desc_content_type", length = 50)
    private String additionalDescContentType;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "ttl", length = 10)
    private String ttl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active") // Added is_active column
    private Boolean isActive;

    // Assuming created_by/updated_by might be missing in original DDL but needed conceptually?
    // If they definitely don't exist in the DB, remove these fields.
    // @Column(name = "created_by", updatable = false)
    // private String createdBy;
    //
    // @Column(name = "updated_by")
    // private String updatedBy;

    // --- Relationships ---

    @OneToMany(mappedBy = "bidProvider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BidAssociation> bidAssociations = new LinkedHashSet<>();

    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ProviderExtension providerExtension;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderRating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderMedia> media = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderLocation> locations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderCredential> credentials = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderItem> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CancellationTerm> cancellationTerms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderFulfillment> fulfillments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Order> orders = new LinkedHashSet<>();
}
