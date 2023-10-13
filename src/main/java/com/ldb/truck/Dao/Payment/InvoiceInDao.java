package com.ldb.truck.Dao.Payment;

import java.util.List;

import com.ldb.truck.Model.Login.Payment.*;
import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNo;
import com.ldb.truck.Model.Login.Payment.PrintInvoiceByNoReq;
import com.ldb.truck.Model.Login.ResFromDateReq;

public interface InvoiceInDao {
    List<Invoice> listInvoiceDetails(ResFromDateReq resFromDateReq);

    List<PrintInvoiceByNo> viewPintBillBackByNo(PrintInvoiceByNoReq printInvoiceByNoReq);
    List<Invoice> List_v_popupPerInVoice();
    List<PrintInvoiceByNo> viewPintBillByNo(PrintInvoiceByNoReq printInvoiceByNoReq);
    List<GenerateInvoiceID> gernerateID();
    List<Invoice>  ListInvoiceAll();

//    List<Invoice>  listInvoiceDetails();

    public int saveInvoice(InvoiceReq invoiceReq);

    List<PrintInvoiceByNo> pintBillByNo(PrintInvoiceByNoReq printInvoiceByNoReq);

    public int CreateMoreInvoice(List<InvoiceDetailReq> invoiceDetailReq);

    public int updatePerformance(List<InvoiceDetailReq> invoiceDetailReq);





}
