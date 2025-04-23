package com.enam.bid.seller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModal {
    private boolean success;
    private String message;
    private Object data;
    private String errorcode;

}
