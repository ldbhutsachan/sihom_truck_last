package com.ldb.truck.Service.Batery;

import com.ldb.truck.Dao.BateryDao.ImplBateryDao;
import com.ldb.truck.Model.Login.Batery.Batery;
import com.ldb.truck.Model.Login.Batery.BateryReq;
import com.ldb.truck.Model.Login.Batery.BateryRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.staft.stafReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeterSerivice {
@Autowired
    ImplBateryDao implBateryDao;
public BateryRes getBateryAll(BateryReq  bateryReq){
    BateryRes result =new BateryRes();
    List<Batery> listData = new ArrayList<>();
    try {
        listData = implBateryDao.getBateryAll(bateryReq);
        if(listData.size()> 1){
            result.setStatus("00");
            result.setMessage("suscess ");
            result.setData(listData);
        }else {
            result.setStatus("01");
            result.setMessage("Data not Found");
            result.setData(listData);
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//==========================Save batery
public Messages saveBatery(BateryReq bateryReq){
    Messages message = new Messages();
    int i = 0;
    try {
        i = implBateryDao.saveBatery(bateryReq);
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
    //==========================UPDATE batery
    public Messages updateBatery(BateryReq bateryReq){
        Messages message = new Messages();
        int i = 0;
        try {
            i = implBateryDao.UpdateBatery(bateryReq);
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
    //delete data
    public Messages DelBatery(BateryReq bateryReq){

        Messages message = new Messages();
        int i = 0;
        try {
            i = implBateryDao.DelBatery(bateryReq);
            if(i == 0){
                message.setStatus("01");
                message.setMessage("ບໍ່ສາມາດລຶບຂໍ້ມູນໄດ້");
                return message;
            }
            message.setStatus("00");
            message.setMessage("ລຶບຂໍ້ມູນສຳເລັດ");

        }catch (Exception e){
            e.printStackTrace();
            message.setStatus("01");
            message.setMessage("ບໍ່ສາມາດລຶບຂໍ້ມູນໄດ້");
            return message;
        }
        return message;
    }
}
