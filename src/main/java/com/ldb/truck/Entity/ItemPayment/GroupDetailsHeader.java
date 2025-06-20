package com.ldb.truck.Entity.ItemPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDetailsHeader {
    private String invoiceNo;
    private String billNo;
    private String txnDate;
    private String expDate3;
    private int qty;
    private String amount;
    private String paymentTotal;
    private String sunSpendTotal;
    private String status;
    List<ItemDetailsEntity> details;
}
