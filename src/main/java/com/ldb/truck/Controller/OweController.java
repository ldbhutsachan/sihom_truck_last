package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Owe.OwePayBackRes;
import com.ldb.truck.Model.Login.Pay.PayReq;
import com.ldb.truck.Model.Login.Pay.PayRes;
import com.ldb.truck.Model.Login.Pay.PayTxnDetailsRes;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Service.OweService.OweService;
import com.ldb.truck.Model.Login.Owe.OweRes;
import com.ldb.truck.Service.PayService.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class OweController {
    private static final Logger log = LogManager.getLogger(OweController.class);
    @Autowired
    OweService oweService;
    @Autowired
    PayService payService;
    @CrossOrigin(origins = "*")
    @PostMapping("/listTxnPayOwe.service")
    public OweRes listTxn(){
        OweRes result =new OweRes();
        try
        {
            result = oweService.listTxnOwe();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--
    @CrossOrigin(origins = "*")
    @PostMapping("/OweReport.service")
    public OwePayBackRes getListReportOweContrller(@RequestBody ResFromDateReq resFromDateReq){
        OwePayBackRes result = new OwePayBackRes();
        try
        {
            result = oweService.listReportOwe(resFromDateReq);
            result.setStatus("00");
            result.setMessage("sucess");

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--get by date to date
    @CrossOrigin(origins = "*")
    @PostMapping("/OweReportByDate.service")
    public OwePayBackRes getListReportByDateOweController(ResFromDateReq resFromDateReq){
        OwePayBackRes result = new OwePayBackRes();
        try
        {
            result = oweService.listReportOweByDate(resFromDateReq);
            result.setStatus("00");
            result.setMessage("sucess");

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/storePayOwe.service")
    public PayRes storePayOwe(@RequestBody PayReq payReq){
        System.out.println("==============================>PAY OWE<==================================================");
        log.info("getBillNo:"+payReq.getBillNo());
        log.info("getPayDate:"+payReq.getPayDate());
        log.info("getInvoiceNo:"+payReq.getInvoiceNo());
        log.info("getPaymentType:"+payReq.getPaymentType());
        log.info("getBankName:"+payReq.getBankName());
        log.info("getRefNo:"+payReq.getRefNo());
        log.info("getAmount:"+payReq.getAmount());
        log.info("getPayAmount:"+payReq.getPayAmount());
        log.info("getStatus:"+payReq.getStatus());
        log.info("getNoPayAmount:"+payReq.getNoPayAmount());
        PayRes result =new PayRes();
        try{
            result = payService.storePayOwe(payReq);
            return result;
        }catch (Exception e){
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }

}
