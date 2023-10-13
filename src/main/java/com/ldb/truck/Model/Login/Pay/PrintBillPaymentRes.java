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
public class PrintBillPaymentRes {
    private String status;
    private String message;
    private List<PrintBillPaymentHeader> header;
    private List<PrintBillPayment> details;
    private List<PrintBillPaymentFooter> footer;

}
