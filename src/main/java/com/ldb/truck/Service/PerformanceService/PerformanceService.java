package com.ldb.truck.Service.PerformanceService;
import java.util.ArrayList;
import java.util.List;

import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportAllRes;
import com.ldb.truck.Model.Login.product.ProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.Performance.PerformanceDao;

@Service
public class PerformanceService {
@Autowired
    PerformanceDao performanceDao;
public getBillNoRes GetBillNo(getBillNoReg getBillNoReg){
    getBillNoRes result = new getBillNoRes();
    List<getBillNo> resData = new ArrayList<>();
    try {
        resData = performanceDao.getBillNo(getBillNoReg);
        result.setData(resData);
        result.setMessage("success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
    }
    return result;
}
//save performance
public PerformanceSaveRes savePerformance (PerformanceReq performanceReq){
    PerformanceSaveRes result = new PerformanceSaveRes();
    int number=0;int i = 0; int number1 =0;int number2=0;
    try {
        performanceDao.updateStaffNum01(performanceReq);
        performanceDao.updateStaffNum02(performanceReq);
        performanceDao.updateDetailsFooter(performanceReq);
        performanceDao.updateDetailsHeader(performanceReq);
        i = performanceDao.updateDetails(performanceReq);
        number = performanceDao.savePerformance(performanceReq);
        number1=performanceDao.storePerformanceHis(performanceReq);
       // number2 = performanceDao.updateDetailsAmount(performanceReq);
        if(number== 0 && i ==0 && number1==0 ) {
            result.setStatus("01");
            result.setMessage("Can't Save data ");
            return result;
        }else
        {
            result.setStatus("00");
            result.setMessage("success");
            return result;
        }
    }catch (Exception e ){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Can't save Data");
        return  result;
    }
    //----print Bill performance
}
    public PerformanceRes listBillGruopHeader(PerformanceReq performanceReq){
        PerformanceRes result  = new PerformanceRes();
        List<performance_SmallHeaderGruop> resperformanceSmallHeaderGruops =new ArrayList<>();
        List<performanceheaderGruop> resgruopHeader = new ArrayList<>();
        List<performaceGroupDetails> resgroupDetails = new ArrayList<>();
        List<performanceGroupFee> resgroupFee = new ArrayList<>();
        List<performance_FeePower> feePower = new ArrayList<>();

        try {
            resperformanceSmallHeaderGruops= performanceDao.groupSmallGroup(performanceReq);
            resgruopHeader = performanceDao.gruopperformance(performanceReq);
            resgroupDetails = performanceDao.gruoperDetails(performanceReq);
            resgroupFee = performanceDao.gruoperFee(performanceReq);
            feePower = performanceDao.groupFeePower(performanceReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setPerformanceSmallHeaderGruops(resperformanceSmallHeaderGruops);
            result.setPerformanceheaderGruops(resgruopHeader);
            result.setPerformaceGroupDetails(resgroupDetails);
            result.setPerformaceGroupFee(resgroupFee);
            result.setPerformance_GroupFeePower(feePower);
            return result;
        } catch (Exception e ){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            result.setPerformanceSmallHeaderGruops(resperformanceSmallHeaderGruops);
            result.setPerformanceheaderGruops(resgruopHeader);
            result.setPerformaceGroupDetails(resgroupDetails);
            result.setPerformaceGroupFee(resgroupFee);
            result.setPerformance_GroupFeePower(feePower);
        }
        return  result;
    }
    //--view data
    public v_performanceRes ListV_performance(){
        v_performanceRes result = new v_performanceRes();
        List<v_performance> resdata = new ArrayList<>();
        try {
            resdata = performanceDao.ListV_performance();
            result.setData(resdata);
            result.setMessage("success");
            result.setStatus("00");

        }catch (Exception e){
            e.printStackTrace();
            result.setData(resdata);
            result.setMessage("data not found");
            result.setStatus("01");
        }
         return result;
    }
    //-print bill lback
    public v_performanceRes PrintBillBlack(PerformanceReq performanceReq){
        v_performanceRes result = new v_performanceRes();
        List<v_performance> resdata = new ArrayList<>();
        try {
            resdata = performanceDao.ListV_performancebyBillNo(performanceReq);
            result.setData(resdata);
            result.setMessage("success");
            result.setStatus("00");

        }catch (Exception e){
            e.printStackTrace();
            result.setData(resdata);
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
    //---gen ID
    public generateKeyIDRes listGenPerNo(){
        generateKeyIDRes result =new generateKeyIDRes();
        List<generateKeyID> resData = new ArrayList<>();
        try{
            resData = performanceDao.genKeyID();
            result.setData(resData);
            result.setMessage("success");
            result.setStatus("00");
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    ///----- List<ReportAll> viewPopup()
    public ReportAllRes v_Popup(){
        List<ReportAll> listData = new ArrayList<>();
        ReportAllRes result = new ReportAllRes();
        try {
            listData = performanceDao.viewPopup();
            result.setData(listData);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e ){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //--
    public v_performanceRes v_popupPerformance(){
        v_performanceRes result = new v_performanceRes();
        List<v_performance> resdata = new ArrayList<>();
        try {
            resdata = performanceDao.v_popupPerformance();
            result.setData(resdata);
            result.setMessage("success");
            result.setStatus("00");

        }catch (Exception e){
            e.printStackTrace();
            result.setData(resdata);
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }

}
