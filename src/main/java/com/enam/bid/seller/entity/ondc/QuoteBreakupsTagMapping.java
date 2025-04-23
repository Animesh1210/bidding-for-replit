package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quote_breakups_tag_mapping", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteBreakupsTagMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_breakup_id", referencedColumnName = "id")
    private QuoteBreakup quoteBreakup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_list_id", referencedColumnName = "id")
    private TagsList tagsList;
}
