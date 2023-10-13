package com.ldb.truck.Model.Login.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrintInvoiceByNoRes {
    private String status;
    private String message;
    private List<GenerateInvoiceID> headerPrint;
    private List<PrintInvoiceByNo> data;
    private List<PrintInvoiceByNoSumFooter> sumTotal;
}
