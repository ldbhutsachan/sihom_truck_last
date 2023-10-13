package com.ldb.truck.Controller;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Pay.PayRes;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.Payment.invoiceDetailRes;
import com.ldb.truck.Model.Login.Performance.Performance;
import com.ldb.truck.Model.Login.Performance.PerformanceReq;
import com.ldb.truck.Model.Login.RevertModel.PerformanceModelRes;
import com.ldb.truck.Service.RevertTransactionService.RevertInvoiceService;
import com.ldb.truck.Service.RevertTransactionService.RevertPerformanceServcie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ldb.truck.Service.RevertTransactionService.RevertTxnDetailsService;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("${base_url}")
public class RevertTransactionController {
    private static final Logger log = LogManager.getLogger(RevertTransactionController.class);
    @Autowired
    RevertTxnDetailsService revertTxnService;
    @Autowired
    RevertPerformanceServcie revertPerformanceServcie;
    @Autowired
    RevertInvoiceService revertInvoiceService;
    //show all txn no condition
    @CrossOrigin(origins = "*")
    @PostMapping("/showOutCarRevert.service")
    public DetailsRes showOutCarRevert(){
        DetailsRes result = new DetailsRes();
        try{
            result = revertTxnService.showOutCarRevert();
            result.setStatus("00");
            result.setMessage("success");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //show txn by lahudpoyload
    @CrossOrigin(origins = "*")
    @PostMapping("/showOutCarRevertbyNo.service")
    public DetailsRes showOutCarRevertbyNo(@RequestBody DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        try{
            result = revertTxnService.showOutCarRevertbyNo(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //update all txn by lahud poylod
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateRevertTxn.service")
    public DetailsRes UpdateRevertTxn(@RequestBody DetailsReq detailsReq){
        log.info("req controller:"+detailsReq.getLaHud_poyLod());
        DetailsRes result = new DetailsRes();
        try{
            result = revertTxnService.UpdateRevertTxn(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/RevertOutCarByNo.service")
    public DetailsRes updateRevertOutCar(@RequestBody DetailsReq detailsReq){
        log.info("req controller:"+detailsReq.getLaHud_poyLod());
        DetailsRes result = new DetailsRes();
        try{
        result = revertTxnService.UpdateRevertOutCar(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //-------control performance
    @CrossOrigin(origins = "*")
    @PostMapping("/showPerformanceRevert.service")
    public PerformanceModelRes showPerformanceRevert(){
        PerformanceModelRes result =new PerformanceModelRes();
        try{
            result = revertPerformanceServcie.showRevertPerformanceRes();
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return  result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/showPerformanceByNo.service")
    public PerformanceModelRes showPerformanceByNo(@RequestBody PerformanceReq vPerformanceReq){
        PerformanceModelRes result =new PerformanceModelRes();
        try{
            result = revertPerformanceServcie.showPerformanceByNo(vPerformanceReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return  result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateRevertPerformanceByNo.service")
    public PerformanceModelRes updateRevertPerformanceByNo(@RequestBody PerformanceReq vPerformanceReq){
        PerformanceModelRes result =new PerformanceModelRes();
        int i=0;
        try{
            i = revertPerformanceServcie.updateRevertPerformanceByNo(vPerformanceReq);
            if(i==0){
                result.setStatus("01");
                result.setMessage("can't update data");
                return  result;
            }
            result.setStatus("00");
            result.setMessage("success");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return  result;
    }
    //---update performance
    @CrossOrigin(origins = "*")
    @PostMapping("/updatePerformanceAllTxn.service")
    public PerformanceModelRes updatePerformanceAllTxn(@RequestBody PerformanceReq vPerformanceReq){
        PerformanceModelRes result =new PerformanceModelRes();
        int i=0;
        try{

            i = revertPerformanceServcie.updatePerformanceAllTxn(vPerformanceReq);

            if(i==0){
                result.setStatus("01");
                result.setMessage("can't update data");

            }
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return  result;
    }
    ///Revert Invoice
    @CrossOrigin(origins = "*")
    @PostMapping("/showInvoiceDetailsAll.service")
    public invoiceDetailRes showInvoiceDetailsAll(){
        invoiceDetailRes result = new invoiceDetailRes();
        try
        {
            result = revertInvoiceService.ShowInvoiceDetailsAll();
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //---show data by invoice id
    @CrossOrigin(origins = "*")
    @PostMapping("/showInvoiceDetailsAllByNo.service")
    public invoiceDetailRes ShowInvoiceDetailsByNo(InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        try
        {
            result = revertInvoiceService.ShowInvoiceDetailsByNo(invoiceDetailReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //----update invoice
    @CrossOrigin(origins = "*")
    @PostMapping("/updateInvoiceStatusByNo.service")
    public invoiceDetailRes updateInvoiceStatusByNo(@RequestBody InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        try
        {
            result = revertInvoiceService.updateInvoiceStatusByNo(invoiceDetailReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    ////---REVERT PAYMENT
    @CrossOrigin(origins = "*")
    @RequestMapping("/revertInvoiceByNo.service")
    public invoiceDetailRes revertInVoiceByNo(@RequestBody InvoiceDetailReq invoiceDetailReq){
        invoiceDetailRes result = new invoiceDetailRes();
        try
        {
            result = revertInvoiceService.RevertInvoiceByNo(invoiceDetailReq);
            result.setStatus("00");
            result.setMessage("sucess");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //--show pupop
    @CrossOrigin(origins = "*")
    @RequestMapping("/showPopupInvoiceByNo.service")
    public invoiceDetailRes showPopupInvoice(){
        invoiceDetailRes result = new invoiceDetailRes();
        try
        {
            result = revertInvoiceService.ShowInvoiceDetailsAllPopupInvoice();
            result.setStatus("00");
            result.setMessage("sucess");
            return  result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
