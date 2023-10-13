package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRes;
import com.ldb.truck.Model.Login.staft.StaffDetailsRes;
import com.ldb.truck.Model.Login.staft.StaffPayReq;
import com.ldb.truck.Model.Login.staft.StaffPayRes;
import com.ldb.truck.Model.Login.staft.StaffPaymentReq;
import com.ldb.truck.Service.staft.StaftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class StaffController {
    private static final Logger log = LogManager.getLogger(DetailController.class);
    @Autowired
    StaftService staftServiceDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/listPaymentStaff.service")
    public ReportStaffRes listReportStaff() {
        log.info("=================================>listPaymentStaff<=================================================");
        ReportStaffRes result = new ReportStaffRes();
        try {
            result = staftServiceDao.ListWaiyPaymentStaff();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //=======================payment staff
    @CrossOrigin(origins = "*")
    @PostMapping("/paymentStaff.service")
    public ReportStaffRes paymentStaff(@RequestBody StaffPaymentReq staffPaymentReq) {
        log.info("=================================>paymentStaff<=================================================");
        ReportStaffRes result = new ReportStaffRes();
        try {
            result = staftServiceDao.paymentStaff(staffPaymentReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //List Payment Report payment
    //=======================================><===============================================================
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportListStaffPay.service")
    public StaffPayRes ReportListStaffPay(@RequestBody StaffPayReq staffPayReq) {
        log.info("=================================>paymentStaff<=================================================");
        StaffPayRes result = new StaffPayRes();
        try {
            result = staftServiceDao.ReportListStaffPay(staffPayReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //==========================================<>================================================================
    @CrossOrigin(origins = "*")
    @PostMapping("/ListWaiyPaymentStaffByID.service")
    public ReportStaffRes ListWaiyPaymentStaffByID(@RequestBody StaffPayReq staffPayReq) {
        log.info("=================================>paymentStaff<=================================================");
        ReportStaffRes result = new ReportStaffRes();
        try {
            result = staftServiceDao.ListWaiyPaymentStaffByID(staffPayReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //===================================<>=================================================================
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportListStaffPayDetails")
    public StaffDetailsRes ReportListStaffPayDetails(@RequestBody StaffPayReq staffPayReq){
        log.info("getStaffID:"+staffPayReq.getStaffID());
        log.info("=================================>ReportListStaffPayDetails<=================================================");
        StaffDetailsRes result = new StaffDetailsRes();
        try {
            result = staftServiceDao.listGetDetailsStatus(staffPayReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;

    }

}
