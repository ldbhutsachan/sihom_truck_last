package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Details.Details;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ldb.truck.Service.DetailsService.DetailsService;

import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNoRes;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNoReq;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNo;
@RestController
@RequestMapping("${base_url}")
public class DetailController {
    private static final Logger log = LogManager.getLogger(DetailController.class);
    @Autowired DetailsService detailsService;
    //get invoice No
    @CrossOrigin(origins = "*")
    @PostMapping("/listInvoiceNo.service")
    public getInvoiceNoRes listInvoiceNo(){
        getInvoiceNoRes result = new getInvoiceNoRes();
        try {
            result = detailsService.ListInvoicedetails();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/listDeatilsAll.service")
    public DetailsRes listVicicleHeader(){
        DetailsRes result = new DetailsRes();
        try {
            result = detailsService.ListDataALL();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //get by id
    @CrossOrigin(origins = "*")
    @PostMapping("/listDeatilsAllByID.service")
    public DetailsRes listVicicleHeaderById(@RequestBody  DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        try {
            result = detailsService.ListDataALLbyID(detailsReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delDataByID.service")
    public DetailsRes delDataByID(@RequestBody  DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        try {
            result = detailsService.deldata(detailsReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateDataByID.service")
    public DetailsRes updateDataByID(@RequestBody  DetailsReq detailsReq){
        DetailsRes result = new DetailsRes();
        try {
            result = detailsService.upDateData(detailsReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveDataDetails.service")
    public DetailsRes saveDataDetails(@RequestBody  DetailsReq detailsReq){
        log.info("data999999999:"+detailsReq.getFeeJumPo2());
        DetailsRes result = new DetailsRes();
        try {
            result = detailsService.saveData(detailsReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
}
