package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintInvoiceByNoSumFooter {
    private Long rowTotal;
    private String priceUnit;
    private String amountTotal;
    private String sumPayamount;
    private String sumNoPayAmount;

    private String sumTotalLak;
    private String sumTotalUSD;
    private String sumTotalTHB;
    private String sumTotalCNY;
}
