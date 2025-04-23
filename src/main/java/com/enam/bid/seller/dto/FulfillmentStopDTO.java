package com.enam.bid.seller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FulfillmentStopDTO {
    private Integer id;
    private Integer fulfillmentId;
    private String type;
    private Integer locationId;
    private String timeLabel;
    private LocalDateTime timeRangeStart;
    private LocalDateTime timeRangeEnd;
    private String contactPhone;
    private String contactEmail;
    private String personName;
    private String instructionsName;
    private String instructionsShortDesc;
    private String authorizationType;
    private String authorizationToken;
    private LocalDateTime authorizationValidFrom;
    private LocalDateTime authorizationValidTo;
    private String authorizationStatus;
    private Boolean isActive;
}
