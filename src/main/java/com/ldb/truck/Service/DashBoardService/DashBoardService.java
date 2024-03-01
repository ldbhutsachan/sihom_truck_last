package com.ldb.truck.Service.DashBoardService;
import com.ldb.truck.Controller.BatteryController;
import com.ldb.truck.Model.DashBoard.CarDashBoard;
import com.ldb.truck.Model.DashBoard.DashBoard;
import com.ldb.truck.Model.DashBoard.DashBoardReq;
import com.ldb.truck.Model.DashBoard.DashBoardRes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashBoardService {
    private static final Logger logger = LogManager.getLogger(DashBoardService.class);
@Autowired
    DashBoardImpl dashBoard;
public DashBoardRes DashBoard(DashBoardReq dashBoardReq){
    DashBoardRes result = new DashBoardRes();
    DecimalFormat numfm = new DecimalFormat("###,###.###");
    List<DashBoard> listData = new ArrayList<>();
    List<DashBoard> listDataPayCar = new ArrayList<>();
    List<DashBoard> groupA = new ArrayList<>();
    List<DashBoard> groupB = new ArrayList<>();
    List<DashBoard> groupC = new ArrayList<>();
    List<DashBoard> groupD = new ArrayList<>();
    List<CarDashBoard> groupCarFree = new ArrayList<>();
    List<CarDashBoard> groupCarNoFree = new ArrayList<>();
    List<DashBoard> groupStaff = new ArrayList<>();
    List<DashBoard> groupPay = new ArrayList<>();
    try{
        listData = dashBoard.DashBoardShow(dashBoardReq);
        listDataPayCar = dashBoard.DashBoardShowfORpAYcAR(dashBoardReq);
        for(DashBoard resGroup : listData){
            if(resGroup.getTypeGet().equals("A")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroup.getTypeGet());
                gA.setAmt_All(resGroup.getAmt_All());
                gA.setAmount_Done(resGroup.getAmt_Done());
                gA.setAmt_NoDone(resGroup.getAmt_NoDone());
                gA.setAmt_Done(resGroup.getAmount_Done());
                gA.setAmount_NoDone(resGroup.getAmount_NoDone());
                groupA.add(gA);
            }
            else if(resGroup.getTypeGet().equals("B")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroup.getTypeGet());
                gA.setAmt_All(resGroup.getAmt_All());
                gA.setAmount_Done(resGroup.getAmt_Done());
                gA.setAmt_NoDone(resGroup.getAmt_NoDone());
                gA.setAmt_Done(resGroup.getAmount_Done());
                gA.setAmount_NoDone(resGroup.getAmount_NoDone());
                groupB.add(gA);
            }
            else if(resGroup.getTypeGet().equals("C")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroup.getTypeGet());
                gA.setAmt_All(resGroup.getAmt_All());
                gA.setAmount_Done(resGroup.getAmt_Done());
                gA.setAmt_NoDone(resGroup.getAmt_NoDone());
                gA.setAmt_Done(resGroup.getAmount_Done());
                gA.setAmount_NoDone(resGroup.getAmount_NoDone());
                groupC.add(gA);
            }
            else if(resGroup.getTypeGet().equals("D")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroup.getTypeGet());
                gA.setAmt_All(resGroup.getAmt_All());
                gA.setAmount_Done(resGroup.getAmt_Done());
                gA.setAmt_NoDone(resGroup.getAmt_NoDone());
                gA.setAmt_Done(resGroup.getAmount_Done());
                gA.setAmount_NoDone(resGroup.getAmount_NoDone());
                groupD.add(gA);
            }else if(resGroup.getTypeGet().equals("CAR_FREE") ){
                CarDashBoard gb = new CarDashBoard();
                gb.setHeadCarTotal(resGroup.getAmt_Done());
                gb.setFooterCarTotal(resGroup.getAmt_NoDone());
                groupCarFree.add(gb);
            }
            else if(resGroup.getTypeGet().equals("CAR_NOFREE")){
                CarDashBoard gb = new CarDashBoard();
                gb.setHeadCarTotal(resGroup.getAmt_Done());
                gb.setFooterCarTotal(resGroup.getAmt_NoDone());
                groupCarNoFree.add(gb);
            }
            else if(resGroup.getTypeGet().equals("paystaff")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroup.getTypeGet());
                gA.setAmt_All(resGroup.getAmt_All());
                gA.setAmount_Done(resGroup.getAmt_Done());
                gA.setAmt_NoDone(resGroup.getAmt_NoDone());
                gA.setAmt_Done(resGroup.getAmount_Done());
                gA.setAmount_NoDone(resGroup.getAmount_NoDone());
                groupStaff.add(gA);
            }
        }
        //=============================pay car
        for(DashBoard resGroupCar : listDataPayCar){
            logger.info("show:"+resGroupCar.getTypeGet());
            //=============================pay staff get data fro count data
            if(resGroupCar.getTypeGet().equals("calPay")){
                DashBoard gA = new DashBoard();
                gA.setTypeGet(resGroupCar.getTypeGet());
                gA.setAmt_All(resGroupCar.getAmt_All());
                gA.setAmount_Done(resGroupCar.getAmt_Done());
                gA.setAmt_NoDone(resGroupCar.getAmt_NoDone());
                gA.setAmt_Done(resGroupCar.getAmount_Done());
                gA.setAmount_NoDone(resGroupCar.getAmount_NoDone());
                groupPay.add(gA);
            }

        }
        result.setStatus("00");
        result.setMessage("Done");
        result.setGroupA(groupA);
        result.setGroupB(groupB);
        result.setGroupC(groupC);
        result.setGroupD(groupD);
        result.setGroupCarFree(groupCarFree);
        result.setGroupCarNoFree(groupCarNoFree);
        result.setGroupPayStaff(groupStaff);
        result.setGroupPayCar(groupPay);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}

}
