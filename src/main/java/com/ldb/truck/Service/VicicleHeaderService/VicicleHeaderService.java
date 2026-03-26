package com.ldb.truck.Service.VicicleHeaderService;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.VicicleHeaderDao.VicicleHeaderDao;
import com.ldb.truck.Dao.VicicleHeaderDao.VicicleHeaderServiceDao;
import com.ldb.truck.Model.Login.CarOffice.*;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeader;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.Report.ReportHeaderRes;
import com.ldb.truck.Model.Login.staft.stafReq;
import com.ldb.truck.Service.GenTransectionID.TransactionIDGenerator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VicicleHeaderService {
    @Autowired
    ProfileDao profileDao;
    TransactionIDGenerator transactionIDGenerator;
    private static final Logger log = LogManager.getLogger(VicicleHeaderService.class);
    @Autowired
    private VicicleHeaderDao vicicleHeaderDao;

    public VicicleHeaderRes listVicicleHeader(VicicleHeaderReq vicicleHeaderReq) {

        log.info("toKen=======================:" + vicicleHeaderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);
        //new
        String uMission = userIn.get(0).getStaff_id();
        List<VicicleHeader> vicicleHeaders = new ArrayList<>();
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            vicicleHeaders = vicicleHeaderDao.listVicicleHeader(vicicleHeaderReq, uMission);
            if (vicicleHeaders.size() < 1) {
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(vicicleHeaders);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //---combo1
    public VicicleHeaderRes listVicicleHeaderCombo1(VicicleHeaderReq vicicleHeaderReq) {
        log.info("toKen=======================:" + vicicleHeaderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);
        //====================================================================
        List<VicicleHeader> vicicleHeaders = new ArrayList<>();
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            vicicleHeaders = vicicleHeaderDao.listVicicleHeaderCombox1(vicicleHeaderReq);
            if (vicicleHeaders.size() < 1) {
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(vicicleHeaders);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //---get data by id
    public VicicleHeaderRes listVicicleHeaderByID(@RequestBody VicicleHeaderReq vicicleHeaderReq) {
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);

        //new
        String uMission = userIn.get(0).getStaff_id();
        List<VicicleHeader> vicicleHeaders = new ArrayList<>();
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            vicicleHeaders = vicicleHeaderDao.listVicicleHeaderByID(vicicleHeaderReq, uMission);
            if (vicicleHeaders.size() < 1) {
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            } else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(vicicleHeaders);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    // list Car office
    public CarOfficeRes listCarOfficeService(@RequestBody CarOfficeReq carOfficeReq) {
        log.info("toKen=======================:" + carOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(carOfficeReq.getToKen());
//        log.info("show=================UserNo:"+userIn.get(0).getUserId());
//        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//        log.info("show=================Role:"+userIn.get(0).getRole());
//        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String branch = userIn.get(0).getBranchNo();
        //add more
        String role = userIn.get(0).getRole();
        String bor_no = userIn.get(0).getBorNo();
        //====================================================================
        List<CarOfficeModel> CarOfficeModel = new ArrayList<>();
        CarOfficeRes result = new CarOfficeRes();
        try {
//======================================================================SMS============================================
//            ArrayList<String> phoneNumbers = new ArrayList<>();
//            phoneNumbers.add("8562092607628");
////            phoneNumbers.add("8562092661111");
////            phoneNumbers.add("8562092607630");
////            phoneNumbers.add("8562092607631");
////            phoneNumbers.add("8562092607632");
////            phoneNumbers.add("8562092607633");
////            phoneNumbers.add("8562092607634");
////            phoneNumbers.add("8562092607635");
            CarOfficeModel = vicicleHeaderDao.listCarOfficeDAOs(carOfficeReq, role, branch, bor_no);
            if (CarOfficeModel.size() < 1) {
                result.setMessage("have No List of Car yet");
                result.setStatus("01");
                return result;
            } else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(CarOfficeModel);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //car bor service
    public CarBorRes listCarOfficeBorService(CarBorReq CarBorReq) {
        log.info("toKen=======================:" + CarBorReq.getToKen());

        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(CarBorReq.getToKen());
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        String borNoProfile = userIn.get(0).getBorNo(); // borNo จาก profile
        String role = userIn.get(0).getRole();

        String borNoClient = CarBorReq.getBorNo(); // borNo จาก body

        //===================set data to CarBorReq===============================
        CarBorReq.setUserId(userId);
        CarBorReq.setBranch(userBranchNo);
        // ไม่ต้อง set BorNo ที่ profile ลงไป เพื่อให้ borNoClient ใช้ได้สำหรับ PADMIN
        //====================================================================

        List<CarBorModel> CarBorModel = new ArrayList<>();
        CarBorRes result = new CarBorRes();
        try {
            CarBorModel = vicicleHeaderDao.listCarBorDao(CarBorReq, role, borNoClient, borNoProfile);
            if (CarBorModel.size() < 1) {
                result.setMessage("have No List of Car yet");
                result.setStatus("01");
                return result;
            } else {
                result.setMessage("Success");
                result.setStatus("00");
                result.setData(CarBorModel);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }


    // list lod dao that paid
    public CarPaidRes listCarDaoPaidService(@RequestBody CarOfficeReq carOfficeReq) {
        log.info("toKen=======================:" + carOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(carOfficeReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        carOfficeReq.setUserId(userId);
        carOfficeReq.setBranch(userBranchNo);
        //====================================================================
        List<CarPaidModel> CarPaidModel = new ArrayList<>();
        CarPaidRes result = new CarPaidRes();
        try {
            CarPaidModel = vicicleHeaderDao.listCarDaoPaid(carOfficeReq);
            if (CarPaidModel.size() < 1) {
                result.setMessage("have No List of Car yet");
                result.setStatus("01");
                return result;
            } else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(CarPaidModel);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //list lod dao
    public CarOfficeRes listDaoCarOfficeService(@RequestBody CarOfficeReq carOfficeReq) {
        log.info("toKen=======================:" + carOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(carOfficeReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        carOfficeReq.setUserId(userId);
        carOfficeReq.setBranch(userBranchNo);
        //====================================================================
        List<CarOfficeModel> CarOfficeModel = new ArrayList<>();
        CarOfficeRes result = new CarOfficeRes();
        try {
            CarOfficeModel = vicicleHeaderDao.listLodDaoOfficeDAOs(carOfficeReq);
            if (CarOfficeModel.size() < 1) {
                result.setMessage("have No List of Car yet");
                result.setStatus("01");
                return result;
            } else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(CarOfficeModel);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    // list car detail by id
    public CarOfficeRes listCarOfficeServiceDetailById(@RequestBody CarOfficeReq carOfficeReq) {

        List<CarOfficeModel> CarOfficeModel = new ArrayList<>();
        CarOfficeRes result = new CarOfficeRes();
        try {
            CarOfficeModel = vicicleHeaderDao.listCarOfficeDAOsDetailById(carOfficeReq);
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(CarOfficeModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //--del
    public VicicleHeaderRes DelVicicleHeaderByID(VicicleHeaderReq vicicleHeaderReq) {
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            vicicleHeaderDao.delVicicleHeader(vicicleHeaderReq);
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    //del car office
    public CarOfficeRes DelCarOfficeService(CarOfficeReq carOfficeReq) {
        CarOfficeRes result = new CarOfficeRes();
        try {
            vicicleHeaderDao.delCarOfficeDAOs(carOfficeReq);
            result.setMessage("Delete Success");
            result.setStatus("00");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("Can not Delete");
            result.setStatus("01");
            return result;
        }
    }

    //--insert data
    public Messages saveVicicleHeader(VicicleHeaderReq stafReq) {
        log.info("toKen=======================:" + stafReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(stafReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        stafReq.setUserId(userId);
        stafReq.setBranch(userBranchNo);
        //====================================================================
        log.info("batno:" + stafReq.getBatNo());
        Messages message = new Messages();
        int i = 0;
        try {
            i = vicicleHeaderDao.saveVicicleHeader(stafReq);
            if (i == 0) {
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ບັນທຶກສຳເລັດ");

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return message;
        }
        return message;
    }

    // insert car office Service
    public Messages InsertCarOfficeService(CarOfficeReq carOfficeReq) {
        log.info("toKen=======================:" + carOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(carOfficeReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        carOfficeReq.setUserId(userId);
        carOfficeReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = vicicleHeaderDao.InsertCarOfficeDAOs(carOfficeReq);
            if (i == 0) {
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ບັນທຶກສຳເລັດ");

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return message;
        }
        return message;
    }

    //pay lod dao service
    public Messages PayLodDaoService(PaidCarDaoReq paidCarDaoReq) {
        log.info("toKen=======================:" + paidCarDaoReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(paidCarDaoReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        paidCarDaoReq.setUserId(userId);
        paidCarDaoReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = vicicleHeaderDao.PayCarDao(paidCarDaoReq);
            if (i == 0) {
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ບັນທຶກສຳເລັດ");

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return message;
        }
        return message;
    }

    // update car office
    public Messages UpdateCarOfficeService(CarOfficeReq carOfficeReq) {
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(carOfficeReq.getToKen());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        carOfficeReq.setUserId(userId);
        carOfficeReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = vicicleHeaderDao.UpdateCarOfficeDAOs(carOfficeReq);

            if (i == 0) {
                message.setStatus("01");
                message.setMessage("ບໍ່ມີຂໍ້ມຸນໃຫ້ແກ້ໄຂ");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ແກ້ໄຂສຳເລັດ");

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດແກ້ໄຂໄດ້");
            return message;
        }
        return message;
    }

    //    update car office notice status
    public Messages UpdateCarOfficeNoticeStatus(CarOfficeReq carOfficeReq) {
        Messages message = new Messages();
        try {
            vicicleHeaderDao.UpdateCarOfficenoticeStatusDAOs(carOfficeReq);
            message.setStatus("00");
            message.setMessage("ແກ້ໄຂສຳເລັດ");

        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດແກ້ໄຂໄດ້");
            return message;
        }
        return message;
    }

    //---update
//public VicicleHeaderRes updateVicicleHeader(VicicleHeaderReq vicicleHeaderReq){
//    VicicleHeaderRes result = new VicicleHeaderRes();
//    try {
//        vicicleHeaderDao.updateVicicleHeader(vicicleHeaderReq);
//        vicicleHeaderDao.saveHeaderHistroty(vicicleHeaderReq);
//        result.setMessage("Success");
//        result.setStatus("00");
//        return result;
//    }catch (Exception e){
//        e.printStackTrace();
//        result.setMessage("data not found");
//        result.setStatus("01");
//        return result;
//    }
//    //---report header
//}
    public Messages updateVicicleHeader(VicicleHeaderReq vicicleHeaderReq) {
        log.info("toKen=======================:" + vicicleHeaderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);
        //====================================================================
        Messages message = new Messages();
        int i = 0;
        try {
            log.info("getImageTruck:" + vicicleHeaderReq.getBatNo());
            if (vicicleHeaderReq.getImageTruck().equals("1") || vicicleHeaderReq.getImageTruck() == null || vicicleHeaderReq.getBatNo() == "undefined") {
                i = vicicleHeaderDao.updateVicicleHeaderUppicHaveData(vicicleHeaderReq);
            } else {
                i = vicicleHeaderDao.updateVicicleHeader(vicicleHeaderReq);
            }

            //  i = vicicleHeaderDao.saveHeaderHistroty(vicicleHeaderReq);
            if (i == 0) {
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ເເກ້ໄຂສຳເລັດ");
        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດເເກ້ໄຂໄດ້");
            return message;
        }
        return message;
    }

    public VicicleHeaderRes ReportHeaderHis(ReportAllReq vicicleHeaderReq) {
        log.info("toKen=======================:" + vicicleHeaderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);
        //====================================================================
        VicicleHeaderRes result = new VicicleHeaderRes();
        List<VicicleHeader> ListData = new ArrayList<>();
        try {
            ListData = vicicleHeaderDao.ReportHistoryHeader(vicicleHeaderReq);
            result.setData(ListData);
            result.setMessage("Success");
            result.setStatus("00");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        return result;
    }

    //List<ReportHeader> listReportHeader(ReportHeaderReq reportHeaderReq)
    public ReportHeaderRes listReportHeader(ReportHeaderReq vicicleHeaderReq) {
        log.info("toKen=======================:" + vicicleHeaderReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(vicicleHeaderReq.getToKen());
        log.info("show=================UserNo:" + userIn.get(0).getUserId());
        log.info("show=================UserBname:" + userIn.get(0).getBranchName());
        log.info("show=================Role:" + userIn.get(0).getRole());
        log.info("show================BranchNo:" + userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        vicicleHeaderReq.setUserId(userId);
        vicicleHeaderReq.setBranch(userBranchNo);
        //====================================================================
        ReportHeaderRes result = new ReportHeaderRes();
        List<ReportHeader> ListData = new ArrayList<>();
        try {
            ListData = vicicleHeaderDao.listReportHeader(vicicleHeaderReq);
            result.setData(ListData);
            result.setMessage("Success");
            result.setStatus("00");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        return result;
    }
}
