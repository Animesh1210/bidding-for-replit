package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_tag_mapping", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTagMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ProviderItem providerItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_list_id", referencedColumnName = "id")
    private TagsList tagsList;
}
