package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayHeader {
private String paymentType;
private String totalAmountPerTXN;

    private String totalAmountPerTXNKIP;
    private String totalAmountPerTXNUSD;
    private String totalAmountPerTXNTHB;
    private String totalAmountPerCNY;
private List<PayTxnDetails> payByCash;
}
