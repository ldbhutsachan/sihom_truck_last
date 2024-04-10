package com.ldb.truck.Service.Inventory;

import com.ldb.truck.Dao.Inventory.InventoryDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.FuelStation.FuelStationReq;
import com.ldb.truck.Model.Login.FuelStation.FuelStationRes;
import com.ldb.truck.Model.Login.Inventory.Items.ItemReq;
import com.ldb.truck.Model.Login.Inventory.Items.ItemRes;
import com.ldb.truck.Model.Login.Inventory.Items.Items;
import com.ldb.truck.Model.Login.Inventory.Shops.ShopReq;
import com.ldb.truck.Model.Login.Inventory.Shops.ShopRes;
import com.ldb.truck.Model.Login.Inventory.Shops.Shops;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    ProfileDao profileDao;
    private static final Logger log = LogManager.getLogger(InventoryService.class);
    @Autowired private InventoryDao inventoryDao;
    //=====================================Shops======================================
    //---Show shop
    public ShopRes ListShops (@RequestBody ShopReq shopReq){
        log.info("toKen=======================:"+shopReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        shopReq.setUserId(userId);
        shopReq.setBranch(userBranchNo);
        //====================================================================
        List<Shops> data = new ArrayList<>();
        ShopRes result = new ShopRes();
        try {
            data = inventoryDao.ListShops(shopReq);
            if(data.size() < 1 ){
                result.setMessage("have no data");
                result.setStatus("01");
                return result;
            }else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(data);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
// insert shops
public ShopRes InsertShops(ShopReq shopReq ){

    log.info("toKen=======================:"+shopReq.getToKen());
    //============================get User info=======================
    List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
    log.info("show=================UserNo:"+userIn.get(0).getUserId());
    log.info("show=================UserBname:"+userIn.get(0).getBranchName());
    log.info("show=================Role:"+userIn.get(0).getRole());
    log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
    //================================================================
    String userId = userIn.get(0).getUserId();
    String userBranchNo = userIn.get(0).getBranchNo();
    //===================set data to userId===============================
    shopReq.setUserId(userId);
    shopReq.setBranch(userBranchNo);
    //====================================================================
    ShopRes result = new ShopRes();
    int i = 0 ;
    try {
        i = inventoryDao.InsertShops(shopReq);
        if(i == 0 ){
            result.setMessage("can't Insert Shops'");
            result.setStatus("01");
            return result;
        }
        result.setMessage("Success");
        result.setStatus("00");
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("exeption");
        result.setStatus("01");
        return result;
    }
}
// delete shop
    public ShopRes DelShops (ShopReq shopReq){
        ShopRes result = new ShopRes();
        try {
            inventoryDao.delShops(shopReq);
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
    // update shops
    public ShopRes UpdateShops(ShopReq shopReq ){

        log.info("toKen=======================:"+shopReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(shopReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        shopReq.setUserId(userId);
        shopReq.setBranch(userBranchNo);
        //====================================================================
        ShopRes result = new ShopRes();
        int i = 0 ;
        try {
            i = inventoryDao.UpdateShops(shopReq);
            if(i == 0 ){
                result.setMessage("can't Insert Shops'");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("exeption");
            result.setStatus("01");
            return result;
        }
    }
    //=====================================Shops======================================

    //---Show
    public ItemRes ListItems (@RequestBody ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================
        List<Items> data = new ArrayList<>();
        ItemRes result = new ItemRes();
        try {
            data = inventoryDao.ListItems(itemReq);
            if(data.size() < 1 ){
                result.setMessage("have no data");
                result.setStatus("01");
                return result;
            }else {

                result.setMessage("Success");
                result.setStatus("00");
                result.setData(data);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    //--delete item
    public ItemRes DelItem (ItemReq itemReq){
        ItemRes result = new ItemRes();
        try {
            inventoryDao.delItem(itemReq);
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
//--insert Item
    public Messages saveItems(ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================

        Messages message = new Messages();
        int i = 0;
        try {
            i = inventoryDao.InsertItemDao(itemReq);
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
//Update
    public Messages  UpdateItems(ItemReq itemReq){
        log.info("toKen=======================:"+itemReq.getToKen());
        //============================get User info=======================
        List<Profile> userIn = profileDao.getProfileInfoByToken(itemReq.getToKen());
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());
        //================================================================
        String userId = userIn.get(0).getUserId();
        String userBranchNo = userIn.get(0).getBranchNo();
        //===================set data to userId===============================
        itemReq.setUserId(userId);
        itemReq.setBranch(userBranchNo);
        //====================================================================
        Messages message = new Messages();
        int i = 0;
        try {
            if(itemReq.getImg().equals("1") || itemReq.getImg() == null){
                i =inventoryDao.UpdateItemDaoHaveData(itemReq);
            }else {
                i =inventoryDao.UpdateItem(itemReq);
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
}
