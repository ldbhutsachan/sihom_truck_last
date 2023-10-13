package com.ldb.truck.Service.ReportStaffService;

import com.ldb.truck.Dao.ReportAll.ReportStaffServiceDao;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRes;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReportStaffService {
    @Autowired
    private ReportStaffServiceDao detailsServiceDao;
    public ReportStaffRes ListDataALL(ReportStaffReq detailsReq){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        try{
            listdata = detailsServiceDao.ReportDetails(detailsReq);
            result.setData(listdata);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //---report staff
    public ReportStaffRes ListDataALLStaff(ReportStaffReq detailsReq){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        try{
            listdata = detailsServiceDao.ReportDetailsStaff(detailsReq);
            result.setData(listdata);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //----+
}
