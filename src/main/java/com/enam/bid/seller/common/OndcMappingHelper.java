package com.enam.bid.seller.common;

import com.enam.bid.seller.entity.ondc.BuyerContext;
import com.enam.ondc.api.components.Context;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OndcMappingHelper {
    public final CommonHelperService commonHelperService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OndcMappingHelper(CommonHelperService commonHelperService){
        this.commonHelperService=commonHelperService;
    }

    public void upsertOndcBuyerContext(Context context){
        BuyerContext buyerContext = new BuyerContext();
        modelMapper.map(context, buyerContext);

        buyerContext.setCityCode(CommonHelperService.nullSafeGetter(() -> context.getLocation().getCity().getCode()));
        buyerContext.setCountryCode(CommonHelperService.nullSafeGetter(() -> context.getLocation().getCountry().getCode()));
    }


}
