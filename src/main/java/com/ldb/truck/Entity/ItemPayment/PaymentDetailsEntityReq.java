package com.ldb.truck.Entity.ItemPayment;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsEntityReq {
    private String toKen;
    private String status;
    private String ccy;
    private String invoiceNo;
    private Float amount;
    private Float exchangeRate;
    private String type;
    private Date expiredDate;
    private Date exp;
    private String paymentType;

}
