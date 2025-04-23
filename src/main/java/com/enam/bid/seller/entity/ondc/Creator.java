package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "creator", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Creator extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "creator_name", length = 255)
    private String creatorName;

    @Column(name = "creator_contact_name", length = 255)
    private String creatorContactName;

    @Column(name = "creator_contact_address", columnDefinition = "text")
    private String creatorContactAddress;

    @Column(name = "creator_contact_phone", length = 20)
    private String creatorContactPhone;

    @Column(name = "creator_contact_email", length = 255)
    private String creatorContactEmail;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderItem> providerItems = new LinkedHashSet<>();
}
