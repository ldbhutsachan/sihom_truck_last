package com.ldb.truck.Model.Login.Dept_Must_Receive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDeptModel {
    private String key_id;
    private String date_invoice;
    private String detail;
    private String pdfandpic;
    private String getmoney_status;
    private String amount_of_money;
    private String quotation_code;
    private String invoice_code;
    private String dept_total;
    private String unit;
}
