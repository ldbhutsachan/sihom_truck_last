package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer_Payment {
    //private String  iD;
    private String  pERFORMANCEBILLNO;
    private String  kEY_ID;
    private String  cUSTOMER_ID;
    private String  cUSTOMER_NAME;
    private String  pRO_TYPE;
    private String pRODUCT_AMOUNT;
    private String  pRICE;
  //  private String tOTALNUMMUN;
    private String  tOTAL_PRICE;
    private String  sTATUS;
    private String currency;
}
