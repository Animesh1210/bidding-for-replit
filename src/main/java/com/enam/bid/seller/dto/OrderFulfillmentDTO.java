package com.enam.bid.seller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFulfillmentDTO {
    private Integer id;
    private Integer orderId;
    private String externalId;
    private String stateDescriptorCode;
    private Boolean rateable;
    private Boolean isActive;
}
