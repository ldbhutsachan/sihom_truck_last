package com.ldb.truck.Service.DetailsService;
import com.ldb.truck.Dao.Details.DetailsServiceDao;
import com.ldb.truck.Dao.Performance.PerformanceDao;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Details.DetailsRes;
import com.ldb.truck.Model.Login.Details.Details;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFormatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNoRes;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNoReq;
import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNo;
import java.util.ArrayList;
import java.util.List;
@Service
public class DetailsService {
    @Autowired
    private DetailsServiceDao detailsServiceDao;
    @Autowired
    PerformanceDao performanceDao;
    public DetailsRes ListDataALL(){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.ListDetails();
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
     public DetailsRes ListDataALLbyID(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.ListDetailsById(detailsReq);
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
    public DetailsRes deldata(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            listdata = detailsServiceDao.delData(detailsReq);
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
    public DetailsRes upDateData(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
            detailsServiceDao.updateData(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    public DetailsRes saveData(DetailsReq detailsReq){
        List<Details> listdata = new ArrayList<>();
        DetailsRes result = new DetailsRes();
        try{
           detailsServiceDao.saveData(detailsReq);
//           performanceDao.updateDetailsHeaderKM(detailsReq);
//           performanceDao.updateDetailsFooterKM(detailsReq);
//           detailsServiceDao.UpdateHeader(detailsReq);
//           detailsServiceDao.UpdateFooter(detailsReq);
//           detailsServiceDao.updateStaff01(detailsReq);
//           detailsServiceDao.updateStaff02(detailsReq);
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //show get invoice No
    public getInvoiceNoRes ListInvoicedetails (){
        List<getInvoiceNo> listdata = new ArrayList<>();
        getInvoiceNoRes result = new getInvoiceNoRes();
        try {
            listdata = detailsServiceDao.showMaxLahudPoyLod();
            result.setStatus("00");
            result.setMessage("success");
            result.setData(listdata);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
            result.setData(listdata);
        }
        return  result;
    }
}
