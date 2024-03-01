package com.ldb.truck.Service.Truck;

import com.ldb.truck.Dao.Truck.ImpTruckDao;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.SumGiveFooter.SumGiveFooter;
import com.ldb.truck.Model.Login.Truck.*;
import com.ldb.truck.Model.Login.staft.StaffDetails;
import com.ldb.truck.Model.sumInCome;
import com.ldb.truck.Model.sumInComeCount;
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
    DecimalFormat numfm = new DecimalFormat("###,###,###");
    List<TruckDetails> listTruck = new ArrayList<>();
    TruckDetailsRes result = new TruckDetailsRes();
    List<SumGiveFooter> getSumfooter= new ArrayList<>();
    List<sumInCome> getSumfooterPay= new ArrayList<>();
    List<sumInComeCount> getSumfooterPaysum= new ArrayList<>();
    List<TruckDetails> getData= new ArrayList<>();
    try {
        listTruck = impTruckDao.ReportGive(resFromDateReq);
       for (TruckDetails rsList : listTruck) {
           if (rsList.getType().equals("SUMFOOTER")) {
               SumGiveFooter groupFooter = new SumGiveFooter();
               groupFooter.setSumTotalRow(rsList.getTotalRow());
               groupFooter.setSumTotalFuel(rsList.getTotalFuel());
               Double carGiveTotal = rsList.getCarGive();
               Double carPayTotal = rsList.getCarPay();
               Double kumLaiyTotal = rsList.getKumLaiy();
               Double TotalPriceNummun = rsList.getTotalPriceNummun();
                Double TotalBialieng = rsList.getTotalBialieng();
               groupFooter.setSumCarGive(numfm.format(carGiveTotal));
               groupFooter.setSumCarPay(numfm.format(carPayTotal));
               groupFooter.setSumKumLaiy(numfm.format(kumLaiyTotal));
               groupFooter.setTotalPriceNummun(numfm.format(TotalPriceNummun));
               groupFooter.setTotalBialieng(numfm.format(TotalBialieng));
               getSumfooter.add(groupFooter);
           }
           else if (rsList.getType().equals("DATA")) {
               TruckDetails tr = new TruckDetails();
               tr.setCarTabienLod(rsList.getCarTabienLod());
               tr.setCarModel(rsList.getCarModel());
               tr.setCarType(rsList.getCarType());
               tr.setType(rsList.getType());
               tr.setCarGive(rsList.getCarGive());
               tr.setCarPay(rsList.getCarPay());
               tr.setKumLaiy(rsList.getKumLaiy());
               tr.setTotalRow(rsList.getTotalRow());
               tr.setTotalFuel(rsList.getTotalFuel());
               tr.setTotalPriceNummun(rsList.getTotalPriceNummun());
               tr.setTotalBialieng(rsList.getTotalBialieng());
               getData.add(tr);
           }
           else if (rsList.getType().equals("PAYSUMFOOTER")) {
               sumInCome groupFooter = new sumInCome();
               Double kumLaiyTotal = rsList.getPayReceipt();
               groupFooter.setSumKumLaiy(numfm.format(kumLaiyTotal));
               getSumfooterPay.add(groupFooter);
           }

       }
       //===========================================================
       //loop check data 1 && 2 then cal data
        Double totalAmount=0.0;
        Double totalAmoun1=0.0;
        for (TruckDetails rsList2 : listTruck) {
            sumInComeCount groupFooter2 = new sumInComeCount();
            if (rsList2.getCheckk().equals("2")) {
                 totalAmount = rsList2.getPayReceipt();
                logger.info("show:"+totalAmount);
            }
                else if ( rsList2.getCheckk().equals("1")) {
                 totalAmoun1 = rsList2.getCarGive();
                logger.info("show:"+totalAmoun1);
            }
        }
        Double countTotal = totalAmount+totalAmoun1;
        //===========================================================

        if(listTruck.size() < 1 ){
            result.setMessage("data not found");
            result.setStatus("01");
            return result;
        }
        result.setMessage("Success");
        result.setStatus("00");
        result.setSumFooter(getSumfooter);
        result.setSumFooterPay(getSumfooterPay);
        result.setCountTotalAll(numfm.format(countTotal));
        result.setData(getData);
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
        DecimalFormat numRow = new DecimalFormat("###,###");
        List<TruckDetailsGroupDataDetails> listTruck = new ArrayList<>();
        TruckDetailsGroupRes result = new TruckDetailsGroupRes();
        try {
            listTruck = impTruckDao.ReportGiveDetails(truckDetailsReq);

            Double carGiveTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getCarGive).collect(Collectors.summingDouble(Double::doubleValue));
            Double carPayTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getCarPay).collect(Collectors.summingDouble(Double::doubleValue));
            Double kumLaiyTotal = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getKumLaiy).collect(Collectors.summingDouble(Double::doubleValue));

            Double totakSainummun = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getTotalPriceNummun).collect(Collectors.summingDouble(Double::doubleValue));
            Double totalBialieng = listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getTotalBialieng).collect(Collectors.summingDouble(Double::doubleValue));
            result.setCarGiveTotal(numRow.format(carGiveTotal));
            result.setCarPayTotal(numRow.format(carPayTotal));
            result.setKumLaiyTotal(numRow.format(kumLaiyTotal));
            result.setTotalPriceNummun(numRow.format(totakSainummun));
            result.setTotalBialieng(numRow.format(totalBialieng));
            Double totalRow =listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getTotalRow).collect(Collectors.summingDouble(Double::doubleValue));
            Double totalFuel =listTruck.stream().distinct().map(TruckDetailsGroupDataDetails::getTotalFuel).collect(Collectors.summingDouble(Double::doubleValue));
            result.setTotalRow(numRow.format(totalRow));
            result.setTotalFuel(numRow.format(totalFuel));
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
