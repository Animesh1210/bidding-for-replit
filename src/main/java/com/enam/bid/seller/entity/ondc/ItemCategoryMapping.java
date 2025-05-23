package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_category_mapping", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ProviderItem providerItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id") // Joins on the business key of Category
    private Category category;
}
