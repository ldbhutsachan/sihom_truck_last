package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintBillPaymentInfo {
    private String proId;
    private String proName_type;
    private String proTotal;
    private  String priceUnit;
    private String amountTotal;
}
