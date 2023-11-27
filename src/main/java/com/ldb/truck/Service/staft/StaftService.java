package com.ldb.truck.Service.staft;

import com.ldb.truck.Controller.BatteryController;
import com.ldb.truck.Dao.Customer.ImpCustomerDao;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRes;
import com.ldb.truck.Model.Login.ReportStaff.Staff;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.staft.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaftService {
    private static final Logger logger = LogManager.getLogger(StaftService.class);
    @Autowired
    ImpCustomerDao impCustomerDao;

    public staftRes getAllStaft (){
        staftRes result = new staftRes();
        List<staftOut> data = new ArrayList<>();
        try {
            data = impCustomerDao.getAllStaft();
            if(data.size() < 1 ){

                result.setMessage("data not found ");
                result.setStatus("01");
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found ");
            result.setStatus("01");
            return result;
        }
    }
    //--List<staftOut> getChooseStaft01()
    public staftRes getChooseStaft01 (){
        staftRes result = new staftRes();
        List<staftOut> data = new ArrayList<>();
        try {
            data = impCustomerDao.getChooseStaft01();
            if(data.size() < 1 ){

                result.setMessage("data not found ");
                result.setStatus("01");
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found ");
            result.setStatus("01");
            return result;
        }
    }
    public staftRes getChooseStaft02 (){
        staftRes result = new staftRes();
        List<staftOut> data = new ArrayList<>();
        try {
            data = impCustomerDao.getChooseStaft02();
            if(data.size() < 1 ){

                result.setMessage("data not found ");
                result.setStatus("01");
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found ");
            result.setStatus("01");
            return result;
        }
    }
    public staftRes getStaftById (stafReq stafReq){
        staftRes result = new staftRes();
        List<staftOut> data = new ArrayList<>();
        try {
            data = impCustomerDao.getStaftById(stafReq.getId());
            if(data.size() < 1 ){
                result.setMessage("data not found ");
                result.setStatus("01");
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found ");
            result.setStatus("01");
            return result;
        }
    }
//
//    public staftRes UpdateStaft (stafReq stafReq){
//        staftRes result = new staftRes();
//        int i = 0;
//        try {
//
//            i = impCustomerDao.UpdateStaft(stafReq);
//
//            if(i == 0 ){
//
//                result.setMessage(" can not update stft");
//                result.setStatus("01");
//                return  result;
//            }
//            result.setMessage("success");
//            result.setStatus("00");
//            return  result;
//        }catch (Exception e) {
//            e.printStackTrace();
//            result.setMessage(" can not update stft");
//            result.setStatus("01");
//            return result;
//        }
//    }
    public Messages  UpdateStaft(stafReq stafReq){
        Messages message = new Messages();
        int i = 0;
        try {
            if(stafReq.getImageStaff().equals("1")){
                i = impCustomerDao.UpdateStaftNoUpdate(stafReq);
            }else {
                i = impCustomerDao.UpdateStaft(stafReq);
            }

            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ເເກ້ໄຂສຳເລັດ");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
            return message;
        }
        return message;
    }
    //--------------------
    public Messages StoreStaft(stafReq stafReq){
        Messages message = new Messages();
        int i = 0;
        try {
            i = impCustomerDao.StoreStaft(stafReq);
            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ບັນທຶກສຳເລັດ");

        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return message;
        }
        return message;
    }

    public staftRes DeleteStaft ( String id){
        staftRes result = new staftRes();
        int i = 0;
        try {
            i = impCustomerDao.deleteStaft(id);
            if(i == 0 ){
                result.setMessage(" can not delete stft");
                result.setStatus("01");
                return  result;
            }
            result.setMessage("success");
            result.setStatus("00");
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMessage(" can not delete stft");
            result.setStatus("01");
            return result;
        }
    }
    //-------wait pay staff
    public ReportStaffRes ListWaiyPaymentStaff(){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        try{
            listdata = impCustomerDao.ListWaiyPaymentStaff();
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
    //=======payment staff========================
    public ReportStaffRes paymentStaff(StaffPaymentReq staffPaymentReq){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        int checkData= 0;
        int checkData01= 0;
        try{
            checkData01=impCustomerDao.paymentStaffUpdate(staffPaymentReq);
            checkData = impCustomerDao.paymentStaff(staffPaymentReq);
            if(checkData == 0 && checkData01==0){
                result.setStatus("01");
                result.setMessage("can't save data");
                return result;
            }else
            {
                result.setStatus("00");
                result.setMessage("save data done ");
                return result;

            }
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //-----report sum staff
    public ReportStaffRes ReportStaffPeymnet(ResFromDateReq resFromDateReq){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        List<Staff> liststaff = new ArrayList<>();

        try{
            listdata = impCustomerDao.ReportStaffPeymnet(resFromDateReq);
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
    //================================>ListStaffPay<============================================
    public StaffPayRes ReportListStaffPay(StaffPayReq staffPayReq){
        List<StaffPay> listdata = new ArrayList<>();
        StaffPayRes result = new StaffPayRes();
        List<Staff> liststaff = new ArrayList<>();
        try{
            listdata = impCustomerDao.ListStaffPay(staffPayReq);
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
    //========================================>ListStaffPaydetailsByStaffId<=============================
    public ReportStaffRes ListWaiyPaymentStaffByID(StaffPayReq staffPayReq){
        List<ReportStaff> listdata = new ArrayList<>();
        ReportStaffRes result = new ReportStaffRes();
        try{
            listdata = impCustomerDao.ListStaffPaydetailsByStaffId(staffPayReq);
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
    //=============staff detailsby keyId==========
    public StaffDetailsRes listGetDetailsStatus(StaffPayReq staffPayReq){
        List<StaffDetails> listdata = new ArrayList<>();
        List<StaffDetailsGroup> resDataItems = new ArrayList<>();
        StaffDetailsRes result = new StaffDetailsRes();
        List<StaffDetailsGroup> staffDetailsGroups = new ArrayList<>();
        try{
            listdata = impCustomerDao.ListDetailStaff(staffPayReq);
            logger.info("dddd==:"+listdata.get(0).getStaffName());
            List<String> staffNo = listdata.stream().map(StaffDetails::getStaffID).distinct().collect(Collectors.toList());
            logger.info("ddddno==:"+staffNo);
            List<String> keyNo = listdata.stream().map(StaffDetails::getKeyId).distinct().collect(Collectors.toList());
            for (String merCode : staffNo) {
                result.setStaffID(listdata.get(0).getStaffID());
                result.setStaffName(listdata.get(0).getStaffName());
                result.setStaffSurname(listdata.get(0).getStaffSurname());
                result.setIdCard(listdata.get(0).getIdCard());
                result.setLicenId(listdata.get(0).getLicenId());
                result.setIdCardExpried(listdata.get(0).getIdCardExpried());
                resDataItems = new ArrayList<>();
                for (StaffDetails rspList : listdata) {
                    logger.info("01:"+rspList.getStaffID());
                    logger.info("02:"+ staffPayReq.getStaffID());
                    if(rspList.getStaffID().equals(staffPayReq.getStaffID())) {
                        StaffDetailsGroup groupDataDetails = new StaffDetailsGroup();
                        groupDataDetails.setKeyId(rspList.getKeyId());
                        groupDataDetails.setHeaderNo(rspList.getHeaderNo());
                        groupDataDetails.setFooterNo(rspList.getFooterNo());
                        groupDataDetails.setCusName(rspList.getCusName());
                        groupDataDetails.setProNo(rspList.getProNo());
                        groupDataDetails.setProName(rspList.getProName());
                        groupDataDetails.setProvinceName(rspList.getProvinceName());
                        groupDataDetails.setPlaceName(rspList.getPlaceName());
                        groupDataDetails.setStartDate(rspList.getStartDate());
                        groupDataDetails.setEndDate(rspList.getEndDate());
                        resDataItems.add(groupDataDetails);

                    }
                }
                result.setData(resDataItems);
            }
            result.setStatus("00");
            result.setMessage("success");
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("data not found");
        }
        return result;
    }
    //=============staff detailsby keyId==========



}
