package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.enums.BuyerSearchCriteriaMode;
import com.enam.bid.seller.enums.BuyerSearchCriteriaType;
import jakarta.persistence.*;
        import lombok.*;
        import java.time.ZonedDateTime;

@Entity
@Table(name = "buyer_search_criteria", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerSearchCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criteria_id")
    private Long criteriaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "context_id", nullable = false)
    private BuyerContext context; // Ensure you have a BuyerContext entity mapped

    @Column(name = "type", length = 10, nullable = false)
    private BuyerSearchCriteriaType type;

    @Column(name = "start_time")
    private ZonedDateTime startTime;

    @Column(name = "end_time")
    private ZonedDateTime endTime;

    @Column(name = "mode", length = 10)
    private BuyerSearchCriteriaMode mode;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt = ZonedDateTime.now();
}
