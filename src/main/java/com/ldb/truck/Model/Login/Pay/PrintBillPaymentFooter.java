package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintBillPaymentFooter {
    private String totalAmount;
    private String sumPayamount;
    private String sumNoPayAmount;

    private String totalCashAmount;


}
