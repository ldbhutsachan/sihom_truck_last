package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Report.*;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRes;
import com.ldb.truck.Service.ReportAllService.ReportAllService;
import com.ldb.truck.Service.ReportStaffService.ReportStaffService;
import com.ldb.truck.Service.VicicleFooterService.VicicleFooterService;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;

import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
@RestController
@RequestMapping("${base_url}")
public class ReportAllFull {
    private static final Logger log = LogManager.getLogger(ReportAll.class);
    @Autowired
    ReportStaffService detailsService;
    @Autowired
    ReportAllService reportAllService;
    @Autowired
    VicicleHeaderService vicicleHeaderService;
    @Autowired
    VicicleFooterService vicicleFooterService;
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportStaff.service")
    public ReportStaffRes listReportStaff(@RequestBody ReportStaffReq reportStaffReq) {
        ReportStaffRes result = new ReportStaffRes();
        try {
            result = detailsService.ListDataALLStaff(reportStaffReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //---ReportAllService
    @CrossOrigin(origins = "*")
    @PostMapping("/ListReportCustomer.service")
    public ReportAllRes ListReportAll01(@RequestBody ReportAllReq reportAllReq){
        ReportAllRes result = new ReportAllRes();
        try {
            result= reportAllService.ListReportAll_Customer(reportAllReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/ListReportProduct.service")
    public ReportAllRes ListReportAllProDuct(@RequestBody ReportAllReq reportAllReq){
        ReportAllRes result = new ReportAllRes();
        try {
            result= reportAllService.ListReportAll_Product(reportAllReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //---report Header
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportHeader.service")
    public VicicleHeaderRes ReportHeaderHis(@RequestBody ReportAllReq vicicleHeaderReq){
            VicicleHeaderRes result = new VicicleHeaderRes();
            try {
                result  = vicicleHeaderService.ReportHeaderHis(vicicleHeaderReq);
            }catch (Exception e ){
                result.setStatus("01");
                result.setMessage("exeption");
                return result;
            }
        return  result;
    }
    //----listReportHeader
    @CrossOrigin(origins = "*")
    @PostMapping("/listReportHeaderPay.service")
    public ReportHeaderRes listReportHeaderPay(@RequestBody ReportHeaderReq reportHeaderReq){
        ReportHeaderRes result = new ReportHeaderRes();
        try {
            result  = vicicleHeaderService.listReportHeader(reportHeaderReq);
        }catch (Exception e ){
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return  result;
    }

    //---Report footer
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportFooter.service")
    public  VicicleFooterRes ReportFooter(@RequestBody ReportAllReq vicicleFooterReq){
                VicicleFooterRes result = new VicicleFooterRes();
                try
                {
                    result = vicicleFooterService.ReportFooter(vicicleFooterReq);

                }catch (Exception e){
                    e.printStackTrace();
                    result.setStatus("01");
                    result.setMessage("exeption");
                    return result;
                }
                return result;
    }
    //---ລາຍງານລາຍຮັບ + ຈ່າຍ
    @CrossOrigin(origins = "*")
    @PostMapping("/ListReportAll01.service")
    public ReportAllRes ListReportAll(@RequestBody ReportAllReq reportAllReq){
        ReportAllRes result = new ReportAllRes();
        try {
            result= reportAllService.ListReportAllProduct(reportAllReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

}
