package com.ldb.truck.Controller;
import com.ldb.truck.Model.Login.Payment.*;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Service.PaymentService.CustomerPaymentService;
import com.ldb.truck.Service.PaymentService.InvoiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("${base_url}")
public class InvoiceController {
    @Autowired
    CustomerPaymentService customerPaymentService;
    @Autowired
    InvoiceService invoiceService;
    private static final Logger log = LogManager.getLogger(InvoiceController.class);
    @CrossOrigin(origins = "*")
    @PostMapping("/listGetPayment.service")
    public Customer_PaymentRes listGetPayment(){
        Customer_PaymentRes result = new Customer_PaymentRes();
        try {
            result =  customerPaymentService.listGetPayment();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---get data by info
    @CrossOrigin(origins = "*")
    @PostMapping("/listGetCustomerByInfo.service")
    public Customer_PaymentRes listGetCustomerByInfo(@RequestBody Customer_PaymentReq customerPaymentReq){
        Customer_PaymentRes result = new Customer_PaymentRes();
        try {
            result =  customerPaymentService.listGetCustomerByInfo(customerPaymentReq);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--ListInvoice
    @CrossOrigin(origins = "*")
    @PostMapping("/ListInvoice.service")
    public InvoiceRes listGetCustomerByInfo(){
        InvoiceRes result = new InvoiceRes();
        try {
            result =  invoiceService.ListInvoice();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---get invoice generate  gernerateID
    @CrossOrigin(origins = "*")
    @PostMapping("/gernerateID.service")
    public GenerateInvoiceIDRes gernerateID(){
        GenerateInvoiceIDRes result = new GenerateInvoiceIDRes();
        try {
            result =  invoiceService.gernerateID();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/CreateInvoice.service")
    public InvoiceRes CreateInvoice (@RequestBody  InvoiceReq invoiceReq){
        log.info("req:"+invoiceReq);
        InvoiceRes result = new InvoiceRes();
        try{
            result = invoiceService.CreateInvoice(invoiceReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/CreateMoreInvoice.service")
    public invoiceDetailRes CreateInvoicemore(@RequestBody  List<InvoiceDetailReq> invoiceDetailReq){
        System.out.println("re:"+invoiceDetailReq);
        invoiceDetailRes result = new invoiceDetailRes();
        try{
            result = invoiceService.CreateInvoiceAll(invoiceDetailReq);
            System.out.println("result:"+result);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---Print Bill by No
    @CrossOrigin(origins = "*")
    @PostMapping("/PintInvoiceByNo.service")
    public PrintInvoiceByNoRes PrintInvoiceByNo(@RequestBody PrintInvoiceByNoReq printInvoiceByNoReq){
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
    try{
        result = invoiceService.PintBillByNo(printInvoiceByNoReq);
        result.setStatus("00");
        result.setMessage("success");
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
        return  result;
    }
    //----
    @CrossOrigin(origins = "*")
    @PostMapping("/viewPintInvoiceByNo.service")
    public PrintInvoiceByNoRes viewPintInvoiceByNo(@RequestBody PrintInvoiceByNoReq printInvoiceByNoReq){
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
        try{
            result = invoiceService.viewPintInvoiceByNo(printInvoiceByNoReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return  result;
    }
    //--print return
    @CrossOrigin(origins = "*")
    @PostMapping("/ViewInVoiceBlack.service")
    public InvoiceRes ViewInVoiceBlack(@RequestBody ResFromDateReq resFromDateReq){
        InvoiceRes result = new InvoiceRes();
        try{
            result = invoiceService.ListviewBlackPrintNo(resFromDateReq);

            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return  result;
    }
    ///--viewPintBillBackByNo
    @CrossOrigin(origins = "*")
    @PostMapping("/viewPintBillBackByNo.service")
    public PrintInvoiceByNoRes viewPintBillBackByNo(@RequestBody PrintInvoiceByNoReq printInvoiceByNoReq){
        PrintInvoiceByNoRes result = new PrintInvoiceByNoRes();
        try{
            result = invoiceService.viewPintBillBackByNo(printInvoiceByNoReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return  result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/v_popupPerInVoice.service")
    public InvoiceRes v_popupPerInVoice(){
        InvoiceRes result = new InvoiceRes();
        try{
            result = invoiceService.v_popupPerInVoice();
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return  result;
    }

    //--- REVERT INVOICE


}
