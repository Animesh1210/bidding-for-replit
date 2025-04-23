package com.enam.bid.seller.dto;

import com.enam.bid.seller.entity.bidseller.enums.BidTypeEnum;
import com.enam.bid.seller.entity.bidseller.enums.AuctionTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.message.Message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BidCreationRequestDTO {

    private List<String> lotId;
    private BidTypeEnum bidType;
    private AuctionTypeEnum auctionType;
    private Boolean autoAssignWinner = false;
    private Boolean allowMultiBids = false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bidStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bidEndDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime bidStartTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime bidEndTime;

    private Integer minBiddersReq;
    private BigDecimal minBidPrice;
}
