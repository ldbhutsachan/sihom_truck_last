package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {
    private String keyId;
    private String cusID;
    private String perID;
    private String cusName;
    private String proType;
    private String proAmount;
    private String priCE;
    private String totalPrice;
    private String status;
    private String inVoiceID;
    private List<InvoiceDetail> dataReq;
}
