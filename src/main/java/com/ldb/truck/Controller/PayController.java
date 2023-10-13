package com.ldb.truck.Controller;

import com.ldb.truck.Dao.PayDao.PayDao;
import com.ldb.truck.Model.Login.Pay.*;
import com.ldb.truck.Model.Login.Payment.Customer_PaymentRes;
import com.ldb.truck.Model.Login.Payment.InvoiceDetailReq;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Service.PayService.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class PayController {
    private static final Logger log = LogManager.getLogger(PayController.class);
    @Autowired
    PayService payService;
    @Autowired
    PayDao payDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/getListBank.service")
    public BankRes listGetPayment(){
        BankRes result = new BankRes();
        try {
            result =  payService.getListBank();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--gen Bill No
    @CrossOrigin(origins = "*")
    @PostMapping("/getBillNoForPay.service")
    public getBillNoRes getBillNoForPay(){
        getBillNoRes result = new getBillNoRes();
        try
        {
            result  = payService.getBillNoForPay();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---store payment
    @CrossOrigin(origins = "*")
    @PostMapping("/StorePayment.service")
    public PayRes storePay(@RequestBody PayReq payReq){
        PayRes result =new PayRes();
        try{
            result = payService.StorePayment(payReq);
            return result;
        }catch (Exception e){
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---owe

//---print bill for payment
@CrossOrigin(origins = "*")
@PostMapping("/listBillPaymentByNo.service")
public PrintBillPaymentRes listBillPaymentByNo(@RequestBody PrintBillPaymentReq printReq){
        log.info("check01");
    PrintBillPaymentRes result = new PrintBillPaymentRes();
    try
    {
        result  = payService.printBillPayment(printReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
    @CrossOrigin(origins = "*")
    @PostMapping("/listBillPaymentByNoBlack.service")
    public PrintBillPaymentRes listBillPaymentByNoBlack(@RequestBody PrintBillPaymentReq printReq){
        PrintBillPaymentRes result = new PrintBillPaymentRes();
        try
        {
            result  = payService.listBillPaymentByNoBlack(printReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
//--black OWE
//--show txn bill
@CrossOrigin(origins = "*")
@PostMapping("/listTxnPay.service")
public  PayTxnDetailsRes listTxn(){
    PayTxnDetailsRes result =new PayTxnDetailsRes();
    try
    {
        result = payService.listTxn();
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
//---report lai hup
@CrossOrigin(origins = "*")
@PostMapping("/listTxnReportLaiHup.service")
public  PayCashRes listTxnReportLaiHup(@RequestBody ResFromDateReq resFromDateReq){
    PayCashRes result =new PayCashRes();
    try
    {
        result = payService.LstPaymentByDate(resFromDateReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}

    @CrossOrigin(origins = "*")
    @PostMapping("/listTxnReportLaiHupAll.service")
    public  PayCashRes listTxnReportLaiHupAll(@RequestBody ResFromDateReq resFromDateReq){
        PayCashRes result =new PayCashRes();
        try
        {
            result = payService.LstPaymentByDateAll(resFromDateReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--
    @CrossOrigin(origins = "*")
    @PostMapping("/v_popupPay.service")
    public  PayTxnDetailsRes v_popupPay(){
        PayTxnDetailsRes result =new PayTxnDetailsRes();
        try
        {
            result = payService.v_popupPay();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---revertPaymentByStatusNo---revertPaymentByStatusHis
    @CrossOrigin(origins = "*")
    @PostMapping("/RevertPaymentByBillNo.service")
    public PayRes RevertPaymentByBillNo(@RequestBody PrintBillPaymentReq paymentReq){
        PayRes result = new PayRes();
        int i =0;
        try
        {
            result = payService.revertPayment(paymentReq);
            result.setStatus("00");
            result.setMessage("sucess");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Can't Revert data");
        }
        return  result;
    }
}
