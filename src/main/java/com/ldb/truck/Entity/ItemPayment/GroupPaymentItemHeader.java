package com.ldb.truck.Entity.ItemPayment;

import com.ldb.truck.Entity.Stock.StockTxnEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupPaymentItemHeader {
    private String invoiceNo;
    private String billNo;
    private String txnDate;
    private String borName;
    private String borLocation;
    private String expDate3;
    private int qty;
    private String amount;
    private String paymentTotal;
    private String sunSpendTotal;
    private String status;

    List<ItemPaymentViewEntity> details;
}
