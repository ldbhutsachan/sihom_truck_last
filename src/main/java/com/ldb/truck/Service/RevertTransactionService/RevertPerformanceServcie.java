package com.ldb.truck.Service.RevertTransactionService;
import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Login.Performance.v_performanceRes;
import com.ldb.truck.Model.Login.Performance.v_performanceReq;
import com.ldb.truck.Model.Login.Performance.Performance;
import com.ldb.truck.Model.Login.Performance.PerformanceRes;
import com.ldb.truck.Model.Login.Performance.PerformanceReq;
import com.ldb.truck.Model.Login.RevertModel.PerformanceModelRes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Dao.Revert.RevertDao;
import java.util.ArrayList;
import java.util.List;
@Service
public class RevertPerformanceServcie {
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
