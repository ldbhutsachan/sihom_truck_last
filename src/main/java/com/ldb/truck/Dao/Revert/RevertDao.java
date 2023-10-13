package com.ldb.truck.Dao.Revert;

import java.security.PublicKey;
import java.util.List;
import com.ldb.truck.Model.Login.Details.Details;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Payment.Invoice;
import com.ldb.truck.Model.Login.Payment.InvoiceDetail;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.Payment.InvoiceReq;
import com.ldb.truck.Model.Login.Performance.Performance;
import com.ldb.truck.Model.Login.Performance.PerformanceReq;
import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Login.Performance.v_performanceReq;
import com.ldb.truck.Model.Login.RevertModel.PerformanceModelRes;


public interface RevertDao {
    public int reVertInvoiceHisByNo(InvoiceDetailReq invoiceDetailReq);
    public int reVertPerHisByNo(InvoiceDetailReq invoiceDetailReq);
    public int reVertPerByNo(InvoiceDetailReq invoiceDetailReq);
    //revert invoice;
    public int reVertInvoiceByNo(InvoiceDetailReq invoiceDetailReq);
    public int updateHeader01 (DetailsReq detailsReq);
    public int updateFooter01 (DetailsReq detailsReq);
    public int updateStaff011 (DetailsReq detailsReq);
    public int updateStaff012 (DetailsReq detailsReq);
    //check header
  public int updateHeader (DetailsReq detailsReq);
    public int updateFooter (DetailsReq detailsReq);
    public int updateStaff01 (DetailsReq detailsReq);
    public int updateStaff02 (DetailsReq detailsReq);

    //new
    public int updateHeaderNew (DetailsReq detailsReq);
    public int updateFooterNew (DetailsReq detailsReq);
    public int updateStaff01New (DetailsReq detailsReq);
    public int updateStaff02New (DetailsReq detailsReq);
  List<Details> showOutCarRevert();
  List<Details> showOutCarRevertbyNo(DetailsReq detailsReq);
  public int UpdateRevertTxn(DetailsReq detailsReq);
 public int UpdateRevertOutCar(DetailsReq detailsReq);
 ///
 ///revert performance
    List<Performance> showPerformance();
    List<Performance> showPerformanceByNo(PerformanceReq vPerformanceReq);
    public int updatePerformanceStatusByNo(PerformanceReq performanceReq);

    public int updatePerformanceAllTxn(PerformanceReq vPerformanceReq);
    public int updatePerformanceAllTxnHis(PerformanceReq performanceReq);
    public int updateAmountDetails(PerformanceReq performanceReq);
    ///REVERT INVOICE
    List<InvoiceDetail> ShowInvoiceDetailsAll();
    List<InvoiceDetail> ShowInvoiceDetailsByNo(InvoiceDetailReq invoiceDetailReq);
    public int updateInvoiceAllTxn(InvoiceDetailReq invoiceDetailReq);
    public int updateInvoiceStatusFromTxn(InvoiceDetailReq invoiceDetailReq);
    //--show invoice popup
    public List<InvoiceDetail> ShowInvoiceDetailsAllForPupupInvoice();


}
