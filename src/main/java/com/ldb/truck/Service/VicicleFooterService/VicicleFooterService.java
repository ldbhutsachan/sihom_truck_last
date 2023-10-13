package com.ldb.truck.Service.VicicleFooterService;

import com.ldb.truck.Dao.VicicleFooterDao.VicicleFooterServiceDao;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VicicleFooterService {
    private static final Logger log = LogManager.getLogger(VicicleFooterService.class);
    @Autowired
    private VicicleFooterServiceDao vicicleFooterServiceDao;
    public VicicleFooterRes delVicicleFooterByID (VicicleFooterReq vicicleFooterReq){
        log.info("key_id"+vicicleFooterReq);
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleFooterServiceDao.delVicicleFooter(vicicleFooterReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //--show all data
    public VicicleFooterRes listViciclefooter (){
        List<VicicleFooter> vicicleHeaders = new ArrayList<>();
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleHeaders = vicicleFooterServiceDao.ListVicicleFooter();
            if(vicicleHeaders.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(vicicleHeaders);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //--combo
    public VicicleFooterRes listViciclefootercombox1(){
        List<VicicleFooter> vicicleHeaders = new ArrayList<>();
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleHeaders = vicicleFooterServiceDao.ListVicicleFooterCombo1();
            if(vicicleHeaders.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(vicicleHeaders);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //--show all data
    public VicicleFooterRes listViciclefooterbyID (VicicleFooterReq vicicleFooterReq){
        List<VicicleFooter> vicicleHeaders = new ArrayList<>();
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleHeaders = vicicleFooterServiceDao.ListVicicleFooterByID(vicicleFooterReq);
            if(vicicleHeaders.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(vicicleHeaders);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //--insert data
    public VicicleFooterRes saveVicicleHeader(VicicleFooterReq vicicleFooterReq){
        log.info("data req:"+vicicleFooterReq);
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleFooterServiceDao.saveVicicleFooter(vicicleFooterReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //update data
    public VicicleFooterRes updateVicicleHeader(VicicleFooterReq vicicleFooterReq){
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            vicicleFooterServiceDao.updateVicicleFooter(vicicleFooterReq);
            vicicleFooterServiceDao.saveVicicleFooterHistory(vicicleFooterReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //---INSERT FOOTER  updateVicicleFooter

    //Report Footer
    public  VicicleFooterRes ReportFooter(ReportAllReq reportAllReq){
        VicicleFooterRes result = new VicicleFooterRes();
        List<VicicleFooter> listData = new ArrayList<>();
        try {
            listData = vicicleFooterServiceDao.ReportFooter(reportAllReq);
            result.setData(listData);
            result.setMessage("Success");
            result.setStatus("00");

        }catch (Exception e ){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;

        }
        return  result;
    }
}
