package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintBillPayment {
    //---customer info
    private String cusId;
    private String cusName;
    private String location;
//--details
    private String printDate;
    private String billNo;
private String proId;
private String proName_type;
private String proTotal;
private  String priceUnit;
private Double amountTotal;

private String amountTotalS;
private Double payAmount;
private Double noPayAmount;
private String currency;

    private Double payCashAmount;


}
