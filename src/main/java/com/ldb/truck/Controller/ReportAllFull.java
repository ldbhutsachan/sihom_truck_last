package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Stock.StockItemDetailsEntity;
import com.ldb.truck.Entity.Stock.StockRequest;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Report.*;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRes;
import com.ldb.truck.Model.ReportAllStock.ReportAllStockInOutRes;
import com.ldb.truck.Model.ReportAllStock.ReportAllStockRes;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelReq;
import com.ldb.truck.Model.ReportItemInOutModel.ReportItemInOutModelResponse;
import com.ldb.truck.Service.ReportAllService.ReportAllService;
import com.ldb.truck.Service.ReportStaffService.ReportStaffService;
import com.ldb.truck.Service.VicicleFooterService.VicicleFooterService;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;

import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;

import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class ReportAllFull {
    private static final Logger log = LogManager.getLogger(ReportAll.class);
    @Autowired
    ProfileDao profileDao;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/ShowAllReportFuel.service")
    public ReportFuelRes ReportFuealStation (@RequestBody ReportAllReq reportAllReq){
        ReportFuelRes result = new ReportFuelRes();
        try {
            result = reportAllService.ReportFuealStation(reportAllReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
//    show total fuel paid
@CrossOrigin(origins = "*")
@PostMapping("/ShowTotalOilPaid.service")
public ShowOilPaidRes ShowTotalOilPaid (@RequestBody ReportAllReq reportAllReq){
    ShowOilPaidRes result = new ShowOilPaidRes();
    try {
        result = reportAllService.ShowTotalOilPaidServiece(reportAllReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}

//***
@CrossOrigin(origins = "*")
@PostMapping("/reportDetailDailyStock.service")
public ReportAllStockInOutRes reportAllService (@RequestBody ReportItemInOutModelReq reportAllReq){
    ReportAllStockInOutRes result = new ReportAllStockInOutRes();
    try {
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(reportAllReq.getToKen());
        String role = userProfiles.get(0).getRole();
        String branchNo = userProfiles.get(0).getBranchNo();
        String borNoss = userProfiles.get(0).getBorNo();
        result = reportAllService.getReportDetailDailyStock(reportAllReq,role,branchNo,borNoss);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Error Exception");
        return result;
    }
    return result;
}

    @CrossOrigin(origins = "*")
    @PostMapping("/reportItemOrderHis.service")
    public DataResponse reportItemOrderHis(
            @RequestBody ReportItemInOutModelReq reportAllReq) {

        DataResponse result = new DataResponse();

        try {
            List<Profile> userProfiles =
                    profileDao.getProfileInfoByToken(reportAllReq.getToKen());

            if (userProfiles == null || userProfiles.isEmpty()) {
                result.setStatus("01");
                result.setMessage("Invalid Token");
                return result;
            }

            Profile profile = userProfiles.get(0);
            String role = profile.getRole();
            String branchNo = profile.getBranchNo();
            String borNoss = profile.getBorNo();
            String umission = profile.getStaff_id();

            result = reportAllService.getReportItemHis(
                    reportAllReq, role, branchNo, borNoss,umission);

        } catch (Exception e) {
            log.error("Error reportItemOrderHis", e);
            result.setStatus("01");
            result.setMessage("Error Exception");
        }

        return result;
    }


}
