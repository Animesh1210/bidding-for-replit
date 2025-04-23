package com.enam.bid.seller.entity.ondc;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "buyer_context", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerContext { // Has all audit fields but no is_active, so fields added directly

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "context_id")
    private Integer contextId;

    @Column(name = "domain", length = 50)
    private String domain;

    @Column(name = "city_code", length = 10)
    private String cityCode;

    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "action", length = 20)
    private String action;

    @Column(name = "version", length = 10)
    private String version;

    @Column(name = "bap_id", length = 255)
    private String bapId;

    @Column(name = "bap_uri", length = 255)
    private String bapUri;

    @Column(name = "transaction_id", length = 50)
    private String transactionId;

    @Column(name = "message_id", length = 50, unique = true)
    private String messageId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "buyerContext", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Order> orders = new LinkedHashSet<>();
}
