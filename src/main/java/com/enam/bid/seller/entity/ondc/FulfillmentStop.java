package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fulfillment_stops", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FulfillmentStop extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "time_label", length = 50)
    private String timeLabel;

    @Column(name = "time_range_start")
    private LocalDateTime timeRangeStart;

    @Column(name = "time_range_end")
    private LocalDateTime timeRangeEnd;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "person_name", length = 255)
    private String personName;

    @Column(name = "instructions_name", length = 255)
    private String instructionsName;

    @Column(name = "instructions_short_desc", columnDefinition = "text")
    private String instructionsShortDesc;

    @Column(name = "authorization_type", length = 50)
    private String authorizationType;

    @Column(name = "authorization_token", length = 50)
    private String authorizationToken;

    @Column(name = "authorization_valid_from")
    private LocalDateTime authorizationValidFrom;

    @Column(name = "authorization_valid_to")
    private LocalDateTime authorizationValidTo;

    @Column(name = "authorization_status", length = 50)
    private String authorizationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fulfillment_id", referencedColumnName = "id")
    private OrderFulfillment orderFulfillment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}
