package com.ldb.truck.Service.RevertTransactionService;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.RevertModel.PerformanceModelRes;
import com.ldb.truck.Model.Login.WastedValue.WastedValueReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.Revert.RevertDao;
import java.util.ArrayList;
import java.util.List;
@Service
public class RevertPerformanceServcie {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(RevertPerformanceServcie.class);
    @Autowired RevertDao revertDao;
    public PerformanceModelRes showRevertPerformanceRes(){
        PerformanceModelRes result = new PerformanceModelRes();
        List<Performance> resData = new ArrayList<>();
        try{
            resData = revertDao.showPerformance();
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //--
    public PerformanceModelRes showPerformanceByNo(PerformanceReq vPerformanceReq){
        log.info("toKen=======================:"+vPerformanceReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vPerformanceReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vPerformanceReq.setUserId(userId);
        vPerformanceReq.setBranch(userBranchNo);
        //====================================================================
        PerformanceModelRes result = new PerformanceModelRes();
        List<Performance> resData = new ArrayList<>();
        try{
            resData = revertDao.showPerformanceByNo(vPerformanceReq);
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //----
    //wasted
    public WastedValueRes showWastedValue(WastedValueReqq wastedValueReqq){
        log.info("toKen=======================:"+wastedValueReqq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(wastedValueReqq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        wastedValueReqq.setUserId(userId);
        wastedValueReqq.setBranch(userBranchNo);
        //====================================================================
        WastedValueRes result = new WastedValueRes();
        List<Performance> resData = new ArrayList<>();
        try{
            resData = revertDao.showWastedValueDao(wastedValueReqq);
            result.setStatus("00");
            result.setMessage("success");
            result.setData(resData);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return result;
    }
    //wasted
    public int updateRevertPerformanceByNo(PerformanceReq vPerformanceReq){
        PerformanceModelRes result = new PerformanceModelRes();
        int i=0;
        try{
            i = revertDao.updatePerformanceStatusByNo(vPerformanceReq);
            if(i==0) {
                result.setStatus("01");
                result.setMessage("Data not Found");
            }
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return 1;
    }
    //-----
    public int updatePerformanceAllTxn(PerformanceReq vPerformanceReq){
        log.info("toKen=======================:"+vPerformanceReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vPerformanceReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vPerformanceReq.setUserId(userId);
        vPerformanceReq.setBranch(userBranchNo);
        //====================================================================
        PerformanceModelRes result = new PerformanceModelRes();
        try{
            int i =0;
            i = revertDao.updatePerformanceAllTxn(vPerformanceReq);
          //  public int updatePerformanceAllTxnHis(PerformanceReq performanceReq)
            revertDao.updatePerformanceAllTxnHis(vPerformanceReq);
            if(i==0){
                result.setStatus("01");
                result.setMessage("can't update data");

            }
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("Data not Found");
        }
        return 1;
    }
}
