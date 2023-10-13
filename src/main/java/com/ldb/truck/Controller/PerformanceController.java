package com.ldb.truck.Controller;
import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportAllRes;
import com.ldb.truck.Service.PerformanceService.PerformanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class PerformanceController {
    private static final Logger log = LogManager.getLogger(PerformanceController.class);
    @Autowired
    PerformanceService performanceService;
    @CrossOrigin(origins = "*")
    @PostMapping("/getBillNo.service")
    public getBillNoRes getBillNo(@RequestBody getBillNoReg getBillNoReg){
        getBillNoRes result = new getBillNoRes();
        try {
            result =  performanceService.GetBillNo(getBillNoReg);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //---save performance
    @CrossOrigin(origins = "*")
    @PostMapping("/savePerformance.service")
    public PerformanceSaveRes savePerformance(@RequestBody PerformanceReq performanceReq){
        log.info("performanceReq:"+performanceReq.getLast_LEKMAI());
        PerformanceSaveRes result = new PerformanceSaveRes();
        try {
            result = performanceService.savePerformance(performanceReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //---list group header
    @CrossOrigin(origins = "*")
    @PostMapping("/ListPrintBillByNo.service")
    public PerformanceRes getgrouheader(@RequestBody PerformanceReq performanceReq){
        PerformanceRes result = new PerformanceRes();
        try {
            result =  performanceService.listBillGruopHeader(performanceReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //**----
    @CrossOrigin(origins = "*")
    @PostMapping("/ListViewPerformance.service")
    public v_performanceRes ListV_Performance(){
        v_performanceRes result = new v_performanceRes();
        try {
            result =  performanceService.ListV_performance();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/SearchBillPerformance.service")
    public v_performanceRes PrintBillBlack(@RequestBody PerformanceReq performanceReq){
        v_performanceRes result = new v_performanceRes();
        try {
            result =  performanceService.PrintBillBlack(performanceReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //--get list gen ID
    @CrossOrigin(origins = "*")
    @PostMapping("/getPerByGenID.service")
    public generateKeyIDRes getGenNo(){
        generateKeyIDRes result = new generateKeyIDRes();
        try {
            result =  performanceService.listGenPerNo();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }

    //--view popup
    @CrossOrigin(origins = "*")
    @PostMapping("/v_popupDetails.service")
    public ReportAllRes v_popupDetails(){
        ReportAllRes result = new ReportAllRes();
        try {
            result= performanceService.v_Popup();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //--view popup per
    @CrossOrigin(origins = "*")
    @PostMapping("/v_popupPerformance.service")
    public v_performanceRes v_popupPerformance(){
        v_performanceRes result = new v_performanceRes();
        try {
            result =  performanceService.v_popupPerformance();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
    //------show data for update
    @CrossOrigin(origins = "*")
    @PostMapping("/v_popupInvoice.service")
    public v_performanceRes v_popupInvoice(){
        v_performanceRes result = new v_performanceRes();
        try {
            result =  performanceService.v_popupPerformance();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }

}
