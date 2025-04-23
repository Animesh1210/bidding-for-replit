package com.enam.bid.seller.common;

import com.enam.ondc.api.components.Ack;
import com.enam.ondc.api.components.Error;
import com.enam.ondc.api.Message;
import com.enam.ondc.api.ONDCAcknowledgementResponse;
import org.springframework.stereotype.Service;

@Service
public class OndcCommonHelperService {

    public void ackResponse(Ack.Status ackStatus, ONDCAcknowledgementResponse ondcAckResponseModel, Error error) {
        Message message = new Message();
        Ack ack = new Ack();
        ack.setStatus(ackStatus);
        message.setAck(ack);
        ondcAckResponseModel.setMessage(message);
        ondcAckResponseModel.setError(error);
    }
    public void ackResponse(Ack.Status ackStatus, ONDCAcknowledgementResponse ondcAckResponseModel) {
        ackResponse(ackStatus,ondcAckResponseModel,null);
    }

    public ONDCAcknowledgementResponse returnNACK(){
        ONDCAcknowledgementResponse ondcAcknowledgementResponse=new ONDCAcknowledgementResponse();
        ackResponse(Ack.Status.NACK,ondcAcknowledgementResponse);
        return  ondcAcknowledgementResponse;
    }

}
