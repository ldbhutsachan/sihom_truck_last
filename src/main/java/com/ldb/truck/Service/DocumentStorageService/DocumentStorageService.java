package com.ldb.truck.Service.DocumentStorageService;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.documentStorageDAOs.DocumentStorageDaos;
import com.ldb.truck.Model.Login.Dept_Must_Receive.*;
import com.ldb.truck.Model.Login.DocumentStorage.*;
import com.ldb.truck.Model.Login.DocumentStorage.RockShipSample.RockShipSampleModel;
import com.ldb.truck.Model.Login.DocumentStorage.RockShipSample.RockShipSampleReq;
import com.ldb.truck.Model.Login.DocumentStorage.RockShipSample.RockShipSampleRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.Task.*;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentStorageService{
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(VicicleHeaderService.class);
    @Autowired
    DocumentStorageDaos documentStorageDaos;

//    insert document
    public Messages InsertDocument (DocumentStorageReq documentStorageReq){
        log.info("toKen=======================:"+documentStorageReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(documentStorageReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        documentStorageReq.setUserId(userId);
        documentStorageReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = documentStorageDaos.InsertDocumentDAOs(documentStorageReq);
            if(i == 0) {
                message.setStatus("01");
                message.setMessage("Can not Store");
                return message;
            }
            message.setStatus("00");
            message.setMessage("Store Successful");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("Can not Store");
            return message;
        }
        return message;
    }
//    insert pic of bor service
public Messages InsertPicOfBor(DocumentStorageReq[] documentStorageReq) {
    Messages message = new Messages();
    try {
        // Assume documentStorageReq[0] contains the correct data
        String toKen = documentStorageReq[0].getToKen();
        log.info("toKen=======================:" + toKen);

        // Get User info based on the token
        List<Profile> userIn = profileDao.getProfileInfoByToken(toKen);
        log.info("UserNo:" + userIn.get(0).getUserId());
        log.info("BranchNo:" + userIn.get(0).getBranchNo());

        // Set user data
        documentStorageReq[0].setUserId(userIn.get(0).getUserId());
        documentStorageReq[0].setBranch(userIn.get(0).getBranchNo());

        int i = documentStorageDaos.InsertMultiPic(documentStorageReq); // Insert into database
        if (i == 0) {
            message.setStatus("01");
            message.setMessage("Can not Store pic");
        } else {
            message.setStatus("00");
            message.setMessage("Store Pic Successful");
        }
    } catch (Exception e) {
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
    }
    return message;
}
//service task
public TaskRes InsertTaskService(TaskReq[] taskReq) {
    TaskRes result = new TaskRes();
    try {
        // Assume documentStorageReq[0] contains the correct data
        String toKen = taskReq[0].getToKen();
        log.info("toKen=======================:" + toKen);

        // Get User info based on the token
        List<Profile> userIn = profileDao.getProfileInfoByToken(toKen);
        log.info("UserNo:" + userIn.get(0).getUserId());
        log.info("BranchNo:" + userIn.get(0).getBranchNo());

        // Set user data
        taskReq[0].setUserId(userIn.get(0).getUserId());
        taskReq[0].setBranch(userIn.get(0).getBranchNo());

        int i = documentStorageDaos.InsertTaskDaos(taskReq); // Insert into database
        if (i == 0) {
            result.setStatus("01");
            result.setMessage("Can not Store Task");
        } else {
            result.setStatus("00");
            result.setMessage("Store Task Successful");
        }
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Can not Store Task");
    }
    return result;
}
//link store
public LinkRes InsertLinksService(LinkReq[] linkReq) {
    LinkRes result = new LinkRes();
    try {
        // Assume documentStorageReq[0] contains the correct data
        String toKen = linkReq[0].getToKen();
        log.info("toKen=======================:" + toKen);

        // Get User info based on the token
        List<Profile> userIn = profileDao.getProfileInfoByToken(toKen);
        log.info("UserNo:" + userIn.get(0).getUserId());
        log.info("BranchNo:" + userIn.get(0).getBranchNo());

        // Set user data
        linkReq[0].setUserId(userIn.get(0).getUserId());
        linkReq[0].setBranch(userIn.get(0).getBranchNo());

        int i = documentStorageDaos.InsertLinksDaos(linkReq); // Insert into database
        if (i == 0) {
            result.setStatus("01");
            result.setMessage("Can not Store Link");
        } else {
            result.setStatus("00");
            result.setMessage("Store Link Successful");
        }
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Can not Store Link");
    }
    return result;
}
//update task
public TaskRes UpdateTaskService (TaskReq[] taskReq) {
    TaskRes result = new TaskRes();
    try {

        int i = documentStorageDaos.UpdateTaskDaos(taskReq); // Insert into database
        if (i == 0) {
            result.setStatus("01");
            result.setMessage("Can not Update Task");
        } else {
            result.setStatus("00");
            result.setMessage("Store Update Successful");
        }
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("Can not Update Task");
    }
    return result;
}
//    dept must received service
    public Messages DeptMustReceivedInsertServiece (DeptMustReceivedReq deptMustReceivedReq){
        log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        deptMustReceivedReq.setUserId(userId);
        deptMustReceivedReq.setBranch(userBranchNo);
        Messages message = new Messages();
//        int i = 0;
        try {
            if (deptMustReceivedReq.getDocument_1() != null && !deptMustReceivedReq.getDocument_1().isEmpty()) {
                documentStorageDaos.DeptMustReceivedInsertDAOs(deptMustReceivedReq);
            }else {
                documentStorageDaos.DeptMustReceivedInsertDAOsNoDoc(deptMustReceivedReq);
            }
//             documentStorageDaos.DeptMustRecievedInsertArray(deptMustReceivedRe);
//            if(i == 0) {
//                message.setStatus("01");
//                message.setMessage("Can not Store");
//                return message;
//            }
            message.setStatus("00");
            message.setMessage("Store Successful");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("Can not Store");
            return message;
        }
        return message;
    }
//    insert list name array
public Messages DeptMustReceivedInsertListName (List<DeptMustReceivedReq> deptMustReceivedRe){
    Messages message = new Messages();
    try {
             documentStorageDaos.DeptMustRecievedInsertArray(deptMustReceivedRe);
        message.setStatus("00");
        message.setMessage("Store Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
        return message;
    }
    return message;
}
//    insert invoice dept service
public Messages InvoiceDeptInsertServiece (InvoiceDeptReq invoiceDeptReq ){
    log.info("toKen=======================:"+invoiceDeptReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(invoiceDeptReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    invoiceDeptReq.setUserId(userId);
    invoiceDeptReq.setBranch(userBranchNo);
    Messages message = new Messages();
//    int i = 0;
    try {
        if (invoiceDeptReq.getPdfandpic() != null && !invoiceDeptReq.getPdfandpic().isEmpty()) {
            documentStorageDaos.InvoiceDeptInsertDAOs(invoiceDeptReq);
        }else
        {
            documentStorageDaos.InvoiceDeptInsertDAOsNoPic(invoiceDeptReq);
        }
//        if(i == 0) {
//            message.setStatus("01");
//            message.setMessage("Can not Store");
//            return message;
//        }
        message.setStatus("00");
        message.setMessage("Store Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
        return message;
    }
    return message;
}
//    update  dept must received
public Messages DeptMustReceivedUpdateServiece (DeptMustReceivedReq deptMustReceivedReq){
    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    deptMustReceivedReq.setUserId(userId);
    deptMustReceivedReq.setBranch(userBranchNo);
    Messages message = new Messages();
    int i = 0;
    try {
        i = documentStorageDaos.DeptMustReceivedUpdateDAOs(deptMustReceivedReq);
        if(i == 0) {
            message.setStatus("01");
            message.setMessage("Can not update");
            return message;
        }
        message.setStatus("00");
        message.setMessage("update Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not update");
        return message;
    }
    return message;
}
//update invoice Dept
public Messages UpdateInvoiceDeptServiece (InvoiceDeptReq invoiceDeptReq){
    log.info("toKen=======================:"+invoiceDeptReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(invoiceDeptReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    invoiceDeptReq.setUserId(userId);
    invoiceDeptReq.setBranch(userBranchNo);
    Messages message = new Messages();
//    int i = 0;
    try {
        documentStorageDaos.InvoiceDeptUpdateDAOs(invoiceDeptReq);
//        if(i == 0) {
//            message.setStatus("01");
//            message.setMessage("Can not update");
//            return message;
//        }
        message.setStatus("00");
        message.setMessage("update Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not update");
        return message;
    }
    return message;
}
//    update Document

    public Messages UpdateDocument (DocumentStorageReq documentStorageReq){
        log.info("toKen=======================:"+documentStorageReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(documentStorageReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        documentStorageReq.setUserId(userId);
        documentStorageReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = documentStorageDaos.UpdateDocumentDAOs(documentStorageReq);
            if(i == 0) {
                message.setStatus("01");
                message.setMessage("Can not Update");
                return message;
            }
            message.setStatus("00");
            message.setMessage("Update Successful");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("Can not Update");
            return message;
        }
        return message;
    }
//    insert hole data
public Messages InsertholedataService (DataHoleReq dataHoleReq){
    log.info("toKen=======================:"+dataHoleReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(dataHoleReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    dataHoleReq.setUserId(userId);
    dataHoleReq.setBranch(userBranchNo);
    Messages message = new Messages();
    int i = 0;
    try {
        i = documentStorageDaos.InsertDataHoleDAOs(dataHoleReq);
        if(i == 0) {
            message.setStatus("01");
            message.setMessage("Can not Store");
            return message;
        }
        message.setStatus("00");
        message.setMessage("Store Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
        return message;
    }
    return message;
}
//service header truck file
public Messages HeaderTruckFileService (OnlyFileHeaderTuckReq onlyFileHeaderTuckReq){
    log.info("toKen=======================:"+onlyFileHeaderTuckReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(onlyFileHeaderTuckReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    onlyFileHeaderTuckReq.setUserId(userId);
    onlyFileHeaderTuckReq.setBranch(userBranchNo);
    Messages message = new Messages();
    int i = 0;
    try {
        i = documentStorageDaos.InsertHeaderTruckFileDAOs(onlyFileHeaderTuckReq);
        if(i == 0) {
            message.setStatus("01");
            message.setMessage("Can not Store");
            return message;
        }
        message.setStatus("00");
        message.setMessage("Store Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
        return message;
    }
    return message;
}
//file header truck update
    public Messages HeaderTruckFileUpdateService (OnlyFileHeaderTuckReq onlyFileHeaderTuckReq){
//        log.info("toKen=======================:"+onlyFileHeaderTuckReq.getToKen());
//        //============================get User info=======================
//        List<Profile> userIn = profileDao.getProfileInfoByToken(onlyFileHeaderTuckReq.getToKen());
//        log.info("show=================UserNo:"+userIn.get(0).getUserId());
//        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//        log.info("show=================Role:"+userIn.get(0).getRole());
//        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//        //================================================================
//        String userId = userIn.get(0).getUserId();
//        String userBranchNo = userIn.get(0).getBranchNo();
//        //===================set data to userId===============================
//        onlyFileHeaderTuckReq.setUserId(userId);
//        onlyFileHeaderTuckReq.setBranch(userBranchNo);
        Messages message = new Messages();
        int i = 0;
        try {
            i = documentStorageDaos.InsertHeaderTruckFileUpdateDAOs(onlyFileHeaderTuckReq);
            if(i == 0) {
                message.setStatus("01");
                message.setMessage("Can not Update");
                return message;
            }
            message.setStatus("00");
            message.setMessage("Update Successful");
        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("Can not update");
            return message;
        }
        return message;
    }
//result of survey
public Messages ResultOfSurveyService (DataHoleReq dataHoleReq){
    log.info("toKen=======================:"+dataHoleReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(dataHoleReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    dataHoleReq.setUserId(userId);
    dataHoleReq.setBranch(userBranchNo);
    Messages message = new Messages();
    int i = 0;
    try {
        i = documentStorageDaos.InsertResultOfSurveyDAOs(dataHoleReq);
        if(i == 0) {
            message.setStatus("01");
            message.setMessage("Can not Store");
            return message;
        }
        message.setStatus("00");
        message.setMessage("Store Successful");
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("Can not Store");
        return message;
    }
    return message;
}

//update asset
//public Messages UpdateAssetOfficeService (AssetsOfficeReq assetsOfficeReq ){
//    log.info("toKen=======================:"+assetsOfficeReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(assetsOfficeReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    assetsOfficeReq.setUserId(userId);
//    assetsOfficeReq.setBranch(userBranchNo);
//    Messages message = new Messages();
//    int i = 0;
//    try {
//        if(assetsOfficeReq.getImg().equals("1") || assetsOfficeReq.getImg() == null  ){
//            i =documentStorageDaos.updateAssetsOfficeUppicHaveData(assetsOfficeReq);
//        }else {
//            i = documentStorageDaos.UpdateAssetsOfficeDAOs(assetsOfficeReq);
//        }
//
//        if(i == 0){
//            message.setStatus("01");
//            message.setMessage("ບໍ່ມີຂໍ້ມຸນໃຫ້ແກ້ໄຂ");
//            return message;
//        }
//        message.setStatus("00");
//        message.setMessage("ແກ້ໄຂສຳເລັດ");
//
//    }catch (Exception e){
//        e.printStackTrace();
//        message.setStatus("01");
//        message.setMessage("ບໍ່ສາມາດແກ້ໄຂໄດ້");
//        return message;
//    }
//    return message;
//}
    //list Document service
    public DocumentStorageRes listDocumentService (@RequestBody DocumentStorageReq documentStorageReq){
        log.info("toKen=======================:"+documentStorageReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(documentStorageReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        documentStorageReq.setUserId(userId);
        documentStorageReq.setBranch(userBranchNo);
        //====================================================================
        List<DocumentStorageModel> Data = new ArrayList<>();
        DocumentStorageRes result = new DocumentStorageRes();
        try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
                Data = documentStorageDaos.listDocDAOs(documentStorageReq);
                result.setMessage("Success");
                result.setStatus("00");
                result.setData(Data);
                return result;
//            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//    dept must recieved all
public DeptMustReceivedRes DeptMustReceivedServices (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    deptMustReceivedReq.setUserId(userId);
    deptMustReceivedReq.setBranch(userBranchNo);
    //====================================================================
    List<DeptMustReceivedModel> Data = new ArrayList<>();
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        Data = documentStorageDaos.listDeptMustReceivedDAOs(deptMustReceivedReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//invoice dept service
public InvoiceDeptRes InvoiceDeptServices (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    deptMustReceivedReq.setUserId(userId);
    deptMustReceivedReq.setBranch(userBranchNo);
    //====================================================================
    List<InvoiceDeptModel> Data = new ArrayList<>();
    InvoiceDeptRes result = new InvoiceDeptRes();
    try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        Data = documentStorageDaos.InvoiceDeptDAOs(deptMustReceivedReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//list name serviece
public ListNameRes ListNameDeptServices (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    List<ListNameModel> Data = new ArrayList<>();
    ListNameRes result = new ListNameRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        summingTotalMoney resFooter = new summingTotalMoney();
        Data = documentStorageDaos.ListNameDeptDAOs(deptMustReceivedReq);
        double totalMoneyd1 = Data.stream().map(ListNameModel::getTotalPrice1).findFirst().orElse(0.0);
        double totalMoneydb = Data.stream().map(ListNameModel::getTotalPrice).collect(Collectors.summingDouble(Double::doubleValue));
        resFooter.setTotalMoney(numfm.format(totalMoneydb + totalMoneyd1));

        result.setSumfooter(resFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//accept service
public DeptMustReceivedRes AcceptTheReqDeptMustReceiveService (@RequestBody DeptMustReceivedReq deptMustReceivedReq){

    List<DeptMustReceivedModel> Data = new ArrayList<>();
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    int i =0;
    try {
       i = documentStorageDaos.AcceptupdateStatusDAOs(deptMustReceivedReq);

        if(i==0){
            result.setMessage("have no data for update");
            result.setStatus("01");
            return result;
        }
        result.setMessage("update success");
        result.setStatus("00");
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
    }
    return result;
}
//    dept must received detail service
public DeptMustReceivedRes DeptMustReceivedServicesdetail (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
//    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    deptMustReceivedReq.setUserId(userId);
//    deptMustReceivedReq.setBranch(userBranchNo);
//    //====================================================================
    List<DeptMustReceivedModel> Data = new ArrayList<>();
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        Data = documentStorageDaos.listDeptMustReceiveddetailDAOs(deptMustReceivedReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//service customer his pay
public CustomerHisPayRes DeptMustReceivedServicesHisCustomer (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    deptMustReceivedReq.setUserId(userId);
    deptMustReceivedReq.setBranch(userBranchNo);
    //====================================================================
    List<CustomerHisPayModel> Data = new ArrayList<>();
    CustomerHisPayRes result = new CustomerHisPayRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        CustomerHisPaySumfooter resFooter = new CustomerHisPaySumfooter();
        Data = documentStorageDaos.DeptMustReceivedHistoryCustormerDAOs(deptMustReceivedReq);

        double price_yet = Data.stream().map(CustomerHisPayModel::getMoney_T_jaiy).collect(Collectors.summingDouble(Double::doubleValue));
        resFooter.setAmount_money_T_jaiy_pai_leo_total(numfm.format(price_yet));
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        result.setFooter_amount(resFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//searching api dept muxt recieved
public CustomerHisPayRes SearchDeptMustReceivedServicesHisCustomer (@RequestBody DeptMustReceivedReq deptMustReceivedReq){
//    log.info("toKen=======================:"+deptMustReceivedReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(deptMustReceivedReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    deptMustReceivedReq.setUserId(userId);
//    deptMustReceivedReq.setBranch(userBranchNo);
//    //====================================================================
    List<CustomerHisPayModel> Data = new ArrayList<>();
    CustomerHisPayRes result = new CustomerHisPayRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    try {
        CustomerHisPaySumfooter resFooter = new CustomerHisPaySumfooter();
        Data = documentStorageDaos.SearchDeptMustReceivedHistoryCustormerDAOs(deptMustReceivedReq);

        double price_yet = Data.stream().map(CustomerHisPayModel::getMoney_T_jaiy).collect(Collectors.summingDouble(Double::doubleValue));
        resFooter.setAmount_money_T_jaiy_pai_leo_total(numfm.format(price_yet));
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        result.setFooter_amount(resFooter);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//    search api for doc
public DocumentStorageRes SearchlistDocumentService (@RequestBody DocumentStorageReq documentStorageReq){
    log.info("toKen=======================:"+documentStorageReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(documentStorageReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    documentStorageReq.setUserId(userId);
    documentStorageReq.setBranch(userBranchNo);
    //====================================================================
    List<DocumentStorageModel> Data = new ArrayList<>();
    DocumentStorageRes result = new DocumentStorageRes();
    try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        Data = documentStorageDaos.SearchlistDocDAOs(documentStorageReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//    all list of hole servieces
public DataHoleRes AlllistOffHoleService (@RequestBody DataHoleReq dataHoleReq){
    log.info("toKen=======================:"+dataHoleReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(dataHoleReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    dataHoleReq.setUserId(userId);
    dataHoleReq.setBranch(userBranchNo);
    //====================================================================
    List<DataHoleModel> Data = new ArrayList<>();
    DataHoleRes result = new DataHoleRes();
    try {
//            if (userIn.get(0).getUserId().equals(141)){
//                Data = documentStorageDaos.listDocumentAdmin(documentStorageReq);
//                result.setMessage("Success");
//                result.setStatus("00");
//                result.setData(Data);
//                return result;
//            }else {
        Data = documentStorageDaos.AlllistOfHoleDAOs(dataHoleReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//show list resulr of survey
public ResultOfSurveyRes AllResultOfSurveyService (@RequestBody DataHoleReq dataHoleReq){
    log.info("toKen=======================:"+dataHoleReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(dataHoleReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    dataHoleReq.setUserId(userId);
    dataHoleReq.setBranch(userBranchNo);
    //====================================================================
    List<ResultOfSurveyModel> Data = new ArrayList<>();
    ResultOfSurveyRes result = new ResultOfSurveyRes();
    try {
        Data = documentStorageDaos.AllResultOfSurveyDAOs(dataHoleReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    //show task service
    public TaskRes getShowTaskService (TaskReq taskReq){
        log.info("toKen=======================:"+taskReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(taskReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        // log.info("show==========where:"+branchReq.getBranchNo(userBranchNo));
        taskReq.setUserId(userId);
        taskReq.setBranch(userBranchNo);

        //====================================================================
        Messages messages = new Messages();
        TaskRes result = new TaskRes();
        try{

            List<TaskModel> data = new ArrayList<>();
            data = documentStorageDaos.AllTaskDAOs(taskReq);
            if(data.size() > 0){
                result.setStatus("00");
                result.setMessage("Done");
                result.setData(data);
            }else {
                result.setStatus("01");
                result.setMessage("No Data");
                result.setData(data);
            }
        }catch (Exception e){
            e.printStackTrace();
            //result.setData(listData);
        }
        return result;
    }
//    link
public LinkRes getShowLinksService (LinkReq linkReq){
    log.info("toKen=======================:"+linkReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(linkReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    // log.info("show==========where:"+branchReq.getBranchNo(userBranchNo));
    linkReq.setUserId(userId);
    linkReq.setBranch(userBranchNo);

    //====================================================================
    Messages messages = new Messages();
    LinkRes result = new LinkRes();
    try{

        List<LinkModel> data = new ArrayList<>();
        data = documentStorageDaos.AllLinksDAOs(linkReq);
        if(data.size() > 0){
            result.setStatus("00");
            result.setMessage("Done");
            result.setData(data);
        }else {
            result.setStatus("01");
            result.setMessage("No Data");
            result.setData(data);
        }
    }catch (Exception e){
        e.printStackTrace();
        //result.setData(listData);
    }
    return result;
}
// show pic of br
public PicOfBorhinRes ShowPicOfBorService (@RequestBody DataHoleReq dataHoleReq){
    log.info("toKen=======================:"+dataHoleReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(dataHoleReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    dataHoleReq.setUserId(userId);
    dataHoleReq.setBranch(userBranchNo);
    //====================================================================
    List<PicOfBorModel> Data = new ArrayList<>();
    PicOfBorhinRes result = new PicOfBorhinRes();
    try {
        Data = documentStorageDaos.PicOfSurveyDAOs(dataHoleReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//show result of survey by id
public ResultOfSurveyRes AllResultOfSurveyByIdService (@RequestBody DataHoleReq dataHoleReq){
    List<ResultOfSurveyModel> Data = new ArrayList<>();
    ResultOfSurveyRes result = new ResultOfSurveyRes();
    try {
        Data = documentStorageDaos.AllResultOfSurveyByIdDAOs(dataHoleReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//show hole data by key_id
public DataHoleRes AlllistOffHoleByKeyIdService (@RequestBody DataHoleReq dataHoleReq){
    List<DataHoleModel> Data = new ArrayList<>();
    DataHoleRes result = new DataHoleRes();
    try {
        Data = documentStorageDaos.AlllistOfHoleByKeyIdDAOs(dataHoleReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//show file of header TRUCK
public FileOfHeaderRes ShowFilesOfHeadertruckKeyIdService (@RequestBody OnlyFileHeaderTuckReq onlyFileHeaderTuckReq){
//    log.info("toKen=======================:"+onlyFileHeaderTuckReq.getToKen());
//    //============================get User info=======================
//    List<Profile> userIn = profileDao.getProfileInfoByToken(onlyFileHeaderTuckReq.getToKen());
//    log.info("show=================UserNo:"+userIn.get(0).getUserId());
//    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
//    log.info("show=================Role:"+userIn.get(0).getRole());
//    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
//    //================================================================
//    String userId = userIn.get(0).getUserId();
//    String userBranchNo = userIn.get(0).getBranchNo();
//    //===================set data to userId===============================
//    onlyFileHeaderTuckReq.setUserId(userId);
//    onlyFileHeaderTuckReq.setBranch(userBranchNo);
    List<FileOfHeaderTruckModel> Data = new ArrayList<>();
    FileOfHeaderRes result = new FileOfHeaderRes();
    try {
        Data = documentStorageDaos.ShowFilesOfHeadertruckKeyIdDAOs(onlyFileHeaderTuckReq);
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(Data);
        return result;
//            }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
//    show list all
    // Document detail by id
    public DocumentStorageRes DocDetailById (@RequestBody DocumentStorageReq documentStorageReq) {
        List<DocumentStorageModel> DocumentStorageModel = new ArrayList<>();
        DocumentStorageRes result = new DocumentStorageRes();
        try {
            DocumentStorageModel = documentStorageDaos.ShowDocumentDetailDAOs(documentStorageReq);
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(DocumentStorageModel);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
//    bouang insert
    public BouangRes BouangInsert(BouangReq bouangReq){
        log.info("toKen=======================:"+bouangReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(bouangReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        bouangReq.setUserId(userId);
        bouangReq.setBranch(userBranchNo);

        //====================================================================
        BouangRes result = new BouangRes();
        int i = 0;
        try{
            i = documentStorageDaos.InsertBouangDAOs(bouangReq);
            if(i==0){
                result.setMessage("can't store data");
                result.setStatus("01");
                return result;
            }
            result.setMessage("success");
            result.setStatus("00");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
        }
        return result;
    }
//    rock ship sample insert service
public RockShipSampleRes StoreRockShipSampleService(RockShipSampleReq[] rockShipSampleReq){
    log.info("toKen=======================:"+rockShipSampleReq[0].getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(rockShipSampleReq[0].getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
//    rockShipSampleReq[0].setUserId(userId);
    rockShipSampleReq[0].setUserId(userIn.get(0).getUserId());
//    bouangReq.setBranch(userBranchNo);
    //====================================================================
    RockShipSampleRes result = new RockShipSampleRes();
    int i = 0;
    try{
        i = documentStorageDaos.StorerockShipSampleDAOs(rockShipSampleReq);
        if(i==0){
            result.setMessage("can't store data");
            result.setStatus("01");
            return result;
        }
        result.setMessage("success");
        result.setStatus("00");
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
    }
    return result;
}
//show rock ship service
public RockShipSampleRes ShowRockShipSampleService(RockShipSampleReq rockShipSampleReq){
    RockShipSampleRes result = new RockShipSampleRes();
    List<RockShipSampleModel> resdata = new ArrayList<>();
    try{
        resdata = documentStorageDaos.ListRockShipDAOS(rockShipSampleReq);
        result.setMessage("Get data successful");
        result.setStatus("00");
        result.setData(resdata);
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        result.setData(resdata);
    }
    return result;
}
//    show get bouang all
public BouangRes getBouangAllServieces (BouangReq bouangReq){
    log.info("toKen=======================:"+bouangReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(bouangReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================

    bouangReq.setBranch(userBranchNo);

    //====================================================================
    BouangRes result = new BouangRes();
    List<BouangModel> resData = new ArrayList<>();
    try{
        if (bouangReq.getToKen().equals("UnCuQ8Dql7bSVS9LcDfMWmA8asAtQLMF"))
        {
            resData = documentStorageDaos.ListBouangAllDAOSpecial(bouangReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }else {
            resData = documentStorageDaos.ListBouangAllDAOs(bouangReq);
            result.setMessage("success");
            result.setStatus("00");
            result.setData(resData);
        }
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        result.setData(resData);
    }
    return result;
}
//update bouang
public BouangRes BouangUpdate (BouangReq bouangReq){
    log.info("toKen=======================:"+bouangReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(bouangReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    bouangReq.setUserId(userId);
    bouangReq.setBranch(userBranchNo);
    //====================================================================
    BouangRes result = new BouangRes();
    int i = 0;
    try{
        i = documentStorageDaos.updateBouangDAOs(bouangReq);
        if(i==0){
            result.setMessage("can't store data");
            result.setStatus("01");
            return result;
        }
        result.setMessage("success");
        result.setStatus("00");
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
    }
    return result;
}
    // delete document
    public DocumentStorageRes DelDocumentByID (DocumentStorageReq documentStorageReq){
        DocumentStorageRes result = new DocumentStorageRes();
        try {
            documentStorageDaos.delDocumentDAOs(documentStorageReq);
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
//    del dept must recieved serviece
public DeptMustReceivedRes DelDeptMustReceivedtByIDServiece (DeptMustReceivedReq deptMustReceivedReq){
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
        documentStorageDaos.delDEptMustReceivedDAOs(deptMustReceivedReq);
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
//    del hole serviece
public DataHoleRes DelHoleByIDServiece (DataHoleReq dataHoleReq){
    DataHoleRes result = new DataHoleRes();
    try {
        documentStorageDaos.delHoledataDAOs(dataHoleReq);
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
//del ResultOfSurvey
public DataHoleRes DelResultOfSurveyByIDServiece (DataHoleReq dataHoleReq){
    DataHoleRes result = new DataHoleRes();
    try {
        documentStorageDaos.delResultOfSurveyDAOs(dataHoleReq);
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
//delete bouang
public BouangRes DelBouangServiece (BouangReq bouangReq){
    BouangRes result = new BouangRes();
    try {
        documentStorageDaos.delBouangDAOs(bouangReq);
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
}
