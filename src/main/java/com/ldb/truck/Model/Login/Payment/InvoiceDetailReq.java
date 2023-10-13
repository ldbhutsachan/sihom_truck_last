package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailReq {
    private String key_id;
    private String cusID;
    private String cusName;
    private String perID;
    private String proType;
    private String proAmount;
    private String priCE;
    private String totalPrice;
    private String status;
    private String inVoiceID;
}
