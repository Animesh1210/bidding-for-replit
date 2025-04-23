package com.enam.bid.seller.controller;

import com.enam.bid.seller.common.OndcCommonHelperService;
import com.enam.bid.seller.service.OndcService;
import com.enam.ondc.api.components.Ack;
import com.enam.ondc.api.ONDCAcknowledgementResponse;
import com.enam.ondc.api.ONDCSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(value = "/ondc")
public class OndcMasterController{
    private final OndcService ondcService;
    private final OndcCommonHelperService ondcCommonHelperService;



    @Autowired
    public OndcMasterController(OndcService ondcService, OndcCommonHelperService ondcCommonHelperService){
        this.ondcService=ondcService;
        this.ondcCommonHelperService = ondcCommonHelperService;
    }


    @PostMapping(value = "/search")
    private ONDCAcknowledgementResponse search(@RequestBody @Validated ONDCSearchRequest ondcSearchRequest){
        boolean payloadReceived = Objects.nonNull(ondcSearchRequest.getMessage());
        if(payloadReceived){
            return ondcService.handleSearch(ondcSearchRequest);
        }
        return ondcCommonHelperService.returnNACK(); //TODO: map ondc error codes later. need master data/enums for all codes
    }

}
