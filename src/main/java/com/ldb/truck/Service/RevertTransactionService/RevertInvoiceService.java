package com.ldb.truck.Service.RevertTransactionService;

import com.ldb.truck.Dao.Revert.Revert;
import com.ldb.truck.Dao.Revert.RevertDao;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.Payment.invoiceDetailRes;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.Payment.InvoiceDetail;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevertInvoiceService {
    private static final Logger log = LogManager.getLogger(RevertInvoiceService.class);
    @Autowired
    RevertDao revertDao;
    public invoiceDetailRes ShowInvoiceDetailsAll(){
        invoiceDetailRes result = new invoiceDetailRes();
        List<InvoiceDetail> resData= new ArrayList<>();
        try{
            resData = revertDao.ShowInvoiceDetailsAll();
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //show revert data by invoice number
    public  invoiceDetailRes ShowInvoiceDetailsByNo(InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        List<InvoiceDetail> resData = new ArrayList<>();
        try{
            resData = revertDao.ShowInvoiceDetailsByNo(invoiceDetailReq);
        result.setStatus("00");
        result.setMessage("success");
        result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //---update invoice by invoice ID only status
    public  invoiceDetailRes updateInvoiceStatusByNo(InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        int i =0;
        int checkStatus=0;
        try{
            i = revertDao.updateInvoiceAllTxn(invoiceDetailReq);
            checkStatus = revertDao.updateInvoiceStatusFromTxn(invoiceDetailReq);
            if(i==0 && checkStatus==0){
                result.setStatus("01");
                result.setMessage("Can't Update status to Reversal");
                return  result;
            }
            result.setStatus("00");
            result.setMessage("success");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //----revert invoice
    public invoiceDetailRes RevertInvoiceByNo(InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        int i =0;
        try{
            i = revertDao.reVertInvoiceByNo(invoiceDetailReq);
            revertDao.reVertPerByNo(invoiceDetailReq);
            revertDao.reVertPerHisByNo(invoiceDetailReq);
            if(i==0){
                result.setStatus("01");
                result.setMessage("Can't Revesal Invoice");
                return  result;
            }
            result.setStatus("00");
            result.setMessage("sucess");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
//---show popup
public invoiceDetailRes ShowInvoiceDetailsAllPopupInvoice(){
    invoiceDetailRes result = new invoiceDetailRes();
    List<InvoiceDetail> resData= new ArrayList<>();
    try{
        resData = revertDao.ShowInvoiceDetailsAllForPupupInvoice();
        result.setStatus("00");
        result.setMessage("success");
        result.setData(resData);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Data not Found");
    }
    return result;
}

}
