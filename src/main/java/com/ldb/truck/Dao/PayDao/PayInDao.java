package com.ldb.truck.Dao.PayDao;

import com.ldb.truck.Model.Login.Pay.*;
import com.ldb.truck.Model.Login.ResFromDateReq;

import java.text.ParseException;
import java.util.List;

public interface PayInDao {
 List<PayTxnDetails> LstPaymentByDateAll(ResFromDateReq resFromDateReq);
 public int setPaymentAmoutAndStatus(PayReq payReq) throws ParseException;
 //revert payment  revertPaymentByStatusNo---revertPaymentByStatusHis
 public int revertPaymentByStatusNo(PrintBillPaymentReq paymentReq);
 public int revertPaymentByStatusHis(PrintBillPaymentReq paymentReq);

 public List<PrintBillPayment> listBillPaymentByNoBlack(PrintBillPaymentReq paymentReq);
 List<PayTxnDetails> v_popupPay();
List<Bank>  getListBank();
List<getBillNo>  getBillNo();
public int storePayment(PayReq payReq);

public int setInvoiceStatus(PayReq payReq);
List<PrintBillPayment> PrintBillNo(PrintBillPaymentReq paymentReq);
List<PayTxnDetails> listPayment();
List<PayTxnDetails> LstPaymentByDate(ResFromDateReq resFromDateReq);

 //----ຕິດໜີ້
 List<PayTxnDetails> listPaymentOwe();
 public int storePayOwe(PayReq payReq);
 public int setPaymentStatus(PayReq payReq) throws ParseException;

}
