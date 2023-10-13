package com.ldb.truck.Service.Truck;

import com.ldb.truck.Dao.Truck.ImpTruckDao;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.Truck.*;
import com.ldb.truck.Model.Login.staft.StaffDetails;
import com.ldb.truck.Service.staft.StaftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TruckService {
    private static final Logger logger = LogManager.getLogger(TruckService.class);
    @Autowired
    ImpTruckDao impTruckDao;

    public TruckRes getAllTruck (){
        List<TruckOut> listTruck = new ArrayList<>();
        TruckRes result = new TruckRes();
        try {
            listTruck = impTruckDao.getAllTruck();
            if(listTruck.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(listTruck);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
    public TruckRes getTruckById (TruckReq truckReq){
        List<TruckOut> listTruck = new ArrayList<>();
        TruckRes result = new TruckRes();
        try {
            listTruck = impTruckDao.getTruckById(truckReq);

            if(listTruck.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(listTruck);
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }

    public TruckRes StoreTruck (TruckReq truckReq){
        TruckRes result = new TruckRes();
        int i = 0;
        try {
            i = impTruckDao.StoreTruck(truckReq);
            if(i == 0){
                result.setMessage(" can not store truck");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            return result;
        }catch (Exception e){
            e.printStackTrace();

            result.setMessage(" can not store truck");
            result.setStatus("01");
            return result;
        }
    }

    public TruckRes UpdateTruck (TruckReq truckReq){

        TruckRes result = new TruckRes();

        int i = 0 ;

        try {

            i = impTruckDao.updateTruck(truckReq);

            if(i == 0){

                result.setMessage(" can not update truck");
                result.setStatus("01");
                return result;
            }

            result.setMessage("Success");
            result.setStatus("00");
            return result;


        }catch (Exception e){
            e.printStackTrace();
            result.setMessage(" can not update truck");
            result.setStatus("01");
            return result;
        }

    }

    public TruckRes DeleteTruck (TruckReq truckReq){

        int id = Integer.parseInt(truckReq.getId());

        TruckRes result = new TruckRes();
        int i = 0;

        try {

            i = impTruckDao.deleteTruck(id);

            if(i == 0){

                result.setMessage(" can not delete truck");
                result.setStatus("01");
                return result;
            }

            result.setMessage("success");
            result.setStatus("00");
            return result;

        }catch (Exception e){
            e.printStackTrace();

            result.setMessage(" can not delete truck");
            result.setStatus("01");
            return result;
        }
    }
//===============================================REPORT CAR ALL
public TruckDetailsRes ReportGive(ResFromDateReq resFromDateReq){
    List<TruckDetails> listTruck = new ArrayList<>();
    TruckDetailsRes result = new TruckDetailsRes();
    try {
        listTruck = impTruckDao.ReportGive(resFromDateReq);
        if(listTruck.size() < 1 ){
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        result.setMessage("Success");
        result.setStatus("00");
        result.setData(listTruck);
        return result;
    }catch (Exception e){
        e.printStackTrace();
        result.setMessage("data not found");
        result.setStatus("01");
        return result;
    }
}
    //===============================================REPORT CAR ALL
    public TruckDetailsGroupRes ReportGiveCarAllNo(TruckDetailsReq truckDetailsReq){

        DecimalFormat numfm = new DecimalFormat("#########");
        List<TruckDetailsGroupDataDetails> listTruck = new ArrayList<>();
        TruckDetailsGroupRes result = new TruckDetailsGroupRes();
        try {
            listTruck = impTruckDao.ReportGiveDetails(truckDetailsReq);
            Double carGiveTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getCarGive).collect(Collectors.summingDouble(Double::doubleValue));
            Double carPayTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getCarPay).collect(Collectors.summingDouble(Double::doubleValue));
            Double kumLaiyTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getKumLaiy).collect(Collectors.summingDouble(Double::doubleValue));
            result.setCarGiveTotal(numfm.format(carGiveTotal));
            result.setCarPayTotal(numfm.format(carPayTotal));
            result.setKumLaiyTotal(numfm.format(kumLaiyTotal));
            if(listTruck.size() < 1 ){
                result.setMessage("data not found");
                result.setStatus("01");
                return result;
            }
            result.setMessage("Success");
            result.setStatus("00");
            result.setData(listTruck);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
    }
}
