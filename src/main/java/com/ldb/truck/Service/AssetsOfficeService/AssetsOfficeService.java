package com.ldb.truck.Service.AssetsOfficeService;

import com.ldb.truck.Dao.AssetOfficeDAOs.AssetsOfficeDAOs;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeModel;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeReq;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeRes;
import com.ldb.truck.Model.Login.AssetsOffice.sumFooterGroupAsset;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
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
public class AssetsOfficeService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(VicicleHeaderService.class);
    @Autowired
    private AssetsOfficeDAOs assetsOfficeDAOs;

    public Messages InsertAssetsOfficeService (AssetsOfficeReq assetsOfficeReq){
        log.info("toKen=======================:"+assetsOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(assetsOfficeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        assetsOfficeReq.setUserId(userId);
        assetsOfficeReq.setBranch(userBranchNo);
        Messages message = new Messages();

        try {
             assetsOfficeDAOs.InsertAssetsOfficeDAOs(assetsOfficeReq);
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
//update asset
public Messages UpdateAssetOfficeService (AssetsOfficeReq assetsOfficeReq ){
    log.info("toKen=======================:"+assetsOfficeReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(assetsOfficeReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    assetsOfficeReq.setUserId(userId);
    assetsOfficeReq.setBranch(userBranchNo);
    Messages message = new Messages();
    int i = 0;
    try {
        if(assetsOfficeReq.getImg().equals("1") || assetsOfficeReq.getImg() == null  ){
            i =assetsOfficeDAOs.updateAssetsOfficeUppicHaveData(assetsOfficeReq);
        }else {
            i = assetsOfficeDAOs.UpdateAssetsOfficeDAOs(assetsOfficeReq);
        }

        if(i == 0){
            message.setStatus("01");
            message.setMessage("ບໍ່ມີຂໍ້ມຸນໃຫ້ແກ້ໄຂ");
            return message;
        }
        message.setStatus("00");
        message.setMessage("ແກ້ໄຂສຳເລັດ");

    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("ບໍ່ສາມາດແກ້ໄຂໄດ້");
        return message;
    }
    return message;
}
    //list Asset service
    public AssetsOfficeRes listAssetOfficeService (@RequestBody AssetsOfficeReq assetsOfficeReq){
        log.info("toKen=======================:"+assetsOfficeReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(assetsOfficeReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        assetsOfficeReq.setUserId(userId);
        assetsOfficeReq.setBranch(userBranchNo);
        //====================================================================
        List<AssetsOfficeModel> Data = new ArrayList<>();
        AssetsOfficeRes result = new AssetsOfficeRes();
        DecimalFormat numfm = new DecimalFormat("###,###.###");

        try {
            sumFooterGroupAsset resFooter = new sumFooterGroupAsset();
            Data = assetsOfficeDAOs.listAssetsOfficeDAOs(assetsOfficeReq);

            double total_price = Data.stream().map(AssetsOfficeModel::getPrice).collect(Collectors.summingDouble(Double::doubleValue));
              resFooter.setTotal_price(numfm.format(total_price));

              if (assetsOfficeReq.getCurrency()== null){
                  result.setMessage("Success");
                  result.setStatus("00");
                  result.setData(Data);
                  return result;
              }else {
                  result.setResFooter(resFooter);
                  result.setMessage("Success");
                  result.setStatus("00");
                  result.setData(Data);
                  return result;
              }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    // asset detail by id
    public AssetsOfficeRes listAssetOfficeServiceDetailById (@RequestBody AssetsOfficeReq assetsOfficeReq){
        List<AssetsOfficeModel> AssetsOfficeModel = new ArrayList<>();
        AssetsOfficeRes result = new AssetsOfficeRes();
        try {
            AssetsOfficeModel = assetsOfficeDAOs.listAssetsOfficeDAOsDetailById(assetsOfficeReq);
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(AssetsOfficeModel);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    // delete assset
    public AssetsOfficeRes DelAssetsByID (AssetsOfficeReq assetsOfficeReq){
        AssetsOfficeRes result = new AssetsOfficeRes();
        try {
            assetsOfficeDAOs.delAssetOfficeDAOs(assetsOfficeReq);
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
