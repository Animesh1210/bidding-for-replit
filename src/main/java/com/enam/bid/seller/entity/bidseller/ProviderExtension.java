package com.enam.bid.seller.entity.bidseller;

import com.enam.bid.seller.entity.ondc.Provider;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "provider_extension", schema = "bid_seller")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "gst_credit_invoice")
    private String gstCreditInvoice;

    @Column(name = "brand_owner")
    private String brandOwner;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "type_of_business")
    private String typeOfBusiness;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    // Assuming one Provider has at most one Extension record
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;
}
