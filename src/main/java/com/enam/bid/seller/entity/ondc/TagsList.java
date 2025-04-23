package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tags_list", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagsList extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descriptor_code", length = 100)
    private String descriptorCode;

    @Column(name = "value", length = 100)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private TagGroupLookup tagGroupLookup;

    @OneToMany(mappedBy = "tagsList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemTagMapping> itemTagMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tagsList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderTagMapping> orderTagMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tagsList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QuoteTagMapping> quoteTagMappings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tagsList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QuoteBreakupsTagMapping> quoteBreakupsTagMappings = new LinkedHashSet<>();
}
