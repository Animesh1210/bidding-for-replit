package com.enam.bid.seller.service;


import com.enam.bid.seller.common.OndcMappingHelper;
import com.enam.ondc.api.ONDCAcknowledgementResponse;
import com.enam.ondc.api.ONDCSearchRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class OndcService {
    private final OndcMappingHelper ondcMappingHelper;

    @Autowired
    OndcService(OndcMappingHelper ondcMappingHelper){
        this.ondcMappingHelper=ondcMappingHelper;
    }


    public ONDCAcknowledgementResponse handleSearch(ONDCSearchRequest ondcSearchRequest){
        ondcMappingHelper.upsertOndcBuyerContext(ondcSearchRequest.getContext());
        ondcSearchRequest.getMessage().getIntent().getItem(); //assume this is category code.
        return null;
        }
}
