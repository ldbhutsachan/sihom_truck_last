package com.ldb.truck.Model.Login.Dept_Must_Receive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceDeptReq {

    private String  amount_of_money;
    private String  quotation_code;
    private String  pdfandpic;
    private String  toKen;
    private String  date_invoice;
    private String  detail;
    private String  account_number;
    private String  account_name;

    private String  branch;
    private String  userId;
    private String  key_id;
    private String  invoice_code;
}
