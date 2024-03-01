package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String inVoiceID;
    private String inVoiceDate;
    //private String perID;
    private String cusID;
    private String  cusName;
    private String row_Total;
    private String priceUnit;
    private String inVoiceAmount;
    private String inVoiceStatus;
    private String totalDay;
    private String currency;
    private String staff_Curr;

//    private String refNO;
//    private String payType;

}
