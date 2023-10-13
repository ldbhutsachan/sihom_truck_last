package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer_PaymentReq {
    private String  iD;
    private String  billNo;
    private String  kEY_ID;
    private String  cUSTOMER_ID;
    private String  cUSTOMER_NAME;
    private String  pRO_TYPE;
    private String lAIYATHANG;
    private String  fEETOTAL;
    private String tOTALNUMMUN;
    private String  tOTAL_PRICE;
    private String  sTATUS;
}
