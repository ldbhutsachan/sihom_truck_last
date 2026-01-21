package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class AccountingWaitCountRes {
    private int accountBillPayWait;
    private int accountbillReceiveWait;
    private int financeBillInProgressReceive;
    private int financeBillInProgressPay;
}
