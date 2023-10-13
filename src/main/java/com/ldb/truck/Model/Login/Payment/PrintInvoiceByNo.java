package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.sound.midi.Soundbank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintInvoiceByNo {
    private String keyId;
    private String cusID;
    private String perID;
    private String cusName;
    private String proType;
    private String proAmount;
    private String priCE;
    private Double priCES;
    private String totalPrice;
    private Double totalPriceS;
    private String status;
    private String inVoiceID;

    private String inVoiceDate;

    private String laHudPoyLod;
    private String outDate;
    private String ViciCleNumber;

    private Double sumPayamount;
    private Double sumNoPayAmount;
    private String billNo;
    private String currency;


}
