package com.ldb.truck.Dao.Truck;

import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.Truck.*;
import com.ldb.truck.Model.Login.delivery.DeliveryOut;
import com.ldb.truck.Model.Login.delivery.DeliveryReq;
import com.ldb.truck.RowMapper.truck.getAllDeliveryMapper;
import com.ldb.truck.RowMapper.truck.getAllTruckMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class ImpTruckDao  implements TruckDao{
    private static final Logger log = LogManager.getLogger(ImpTruckDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;
    @Override
    public List<TruckOut> getAllTruck() {

        List<TruckOut>  result = new ArrayList<>();

        try {
            String SQL = "SELECT  KEY_ID , PLATENO , MODELNO , VEHICLETYPENAME , ENGINENO , CHASSISNO , FEEYEAR , IS_ID , DATE_EXP_IS , GLASS , DATE_EXP_TEP , USERID , L01 , L01EXP , R01 , R01EXP , LIN01,\n" +
                    "LIN01EXP , LIN02 , LIN02EXP , LIN03 , LIN03EXP , LOUT01 , LOUT01EXP , LOUT02 , LOUT02EXP , LOUT03 , LOUT03EXP , RIN01 , RIN01EXP , RIN02 , RIN02EXP , RIN03 , RIN03EXP , ROUT01 , ROUT01EXP  , ROUT02  , ROUT02EXP  ,ROUT03  ,ROUT03EXP," +
                    "L01KM,\n"+
                    "R01KM,\n" +
                    "LIN01KM,\n" +
                    "LIN02KM,\n" +
                    "LIN03KM,\n" +
                    "LOUT01KM,\n" +
                    "LOUT02KM,\n" +
                    "LOUT03KM,\n" +
                    "RIN01KM,\n" +
                    "RIN02KM,\n" +
                    "RIN03KM,\n" +
                    "ROUT01KM,\n" +
                    "ROUT02KM,\n"+
                    "ROUT03KM,\n" +
                    "tRUCK_NO," +
                    "tRUCK_SERIAL," +
                    "tRUCK_MOFAI," +
                    "tRUCK_FAITHAIY," +
                    "tRUCK_VENKHANG," +
                    "tRUCK_PAKUNPHAI," +
                    "tRUCK_LEKTHUNG," +
                    "tRUCK_BUNGTHOM," +
                    "tRUCK_FAIKHANG," +
                    "tRUCK_LEKYANGLOD," +
                    "tRUCK_PAKUNPHAI_DATE," +
                    "tRUCK_GPRS," +
                    "tRUCK_JANLARK," +
                    "tRUCK_VENMONGNAR," +
                    "tRUCK_TANGSIT," +
                    "tRUCK_FOYPUDNUMFON," +
                    "tRUCK_FAINAR," +
                    "tRUCK_VENMONGLG," +
                    "bACK_TRUCK_THABIENLOD," +
                    "bACK_TRUCK_PAO3," +
                    "bACK_TRUCK_SO," +
                    "bACK_TRUCK_BGTOM," +
                    "bACK_TRUCK_LEKKUNZEE," +
                    "bACK_TRUCK_PAO4," +
                    "bACK_TRUCK_PHABUD," +
                    "bACK_TRUCK_PAO1," +
                    "bACK_TRUCK_KORKC," +
                    "bACK_TRUCK_FAIKHANG," +
                    "bACK_TRUCK_PAO2," +
                    "bACK_TRUCK_LOCKTU," +
                    "bACK_TRUCK_FAITHAIY" +
                    " FROM TRUCK  WHERE STATUS='A'  ORDER BY KEY_ID DESC";
           // System.out.println(SQL);
             result = EBankJdbcTemplate.query(SQL , new getAllTruckMapper());

        }catch (Exception e){
            e.printStackTrace();
            return  result;
        }
        return result;
    }
    @Override
    public List<TruckOut> getTruckById(TruckReq truckReq) {
        List<TruckOut>  result = new ArrayList<>();
        try {
            String SQL = "SELECT  KEY_ID , PLATENO , MODELNO , VEHICLETYPENAME , ENGINENO , CHASSISNO , FEEYEAR , IS_ID , DATE_EXP_IS , GLASS , DATE_EXP_TEP , USERID , L01 , L01EXP , R01 , R01EXP , LIN01,\n" +
                    "LIN01EXP , LIN02 , LIN02EXP , LIN03 , LIN03EXP , LOUT01 , LOUT01EXP , LOUT02 , LOUT02EXP , LOUT03 , LOUT03EXP , RIN01 , RIN01EXP , RIN02 , RIN02EXP , RIN03 , RIN03EXP , ROUT01 , ROUT01EXP  , ROUT02  , ROUT02EXP  ,ROUT03  ,ROUT03EXP," +
                    "L01KM,\n"+
                    "R01KM,\n" +
                    "LIN01KM,\n" +
                    "LIN02KM,\n" +
                    "LIN03KM,\n" +
                    "LOUT01KM,\n" +
                    "LOUT02KM,\n" +
                    "LOUT03KM,\n" +
                    "RIN01KM,\n" +
                    "RIN02KM,\n" +
                    "RIN03KM,\n" +
                    "ROUT01KM,\n" +
                    "ROUT02KM,\n"+
                    "ROUT03KM,\n" +
                    "TRUCK_NO," +
                    "TRUCK_SERIAL," +
                    "TRUCK_MOFAI," +
                    "TRUCK_FAITHAIY," +
                    "TRUCK_VENKHANG," +
                    "TRUCK_PAKUNPHAI," +
                    "TRUCK_LEKTHUNG," +
                    "TRUCK_BUNGTHOM," +
                    "TRUCK_FAIKHANG," +
                    "TRUCK_LEKYANGLOD," +
                    "TRUCK_PAKUNPHAI_DATE," +
                    "TRUCK_GPRS," +
                    "TRUCK_JANLARK," +
                    "TRUCK_VENMONGNAR," +
                    "TRUCK_TANGSIT," +
                    "TRUCK_FOYPUDNUMFON," +
                    "TRUCK_FAINAR," +
                    "TRUCK_VENMONGLG," +
                    "BACK_TRUCK_THABIENLOD," +
                    " BACK_TRUCK_PAO3," +
                    "BACK_TRUCK_SO," +
                    " BACK_TRUCK_BGTOM," +
                    "BACK_TRUCK_LEKKUNZEE," +
                    " BACK_TRUCK_PAO4," +
                    "BACK_TRUCK_PHABUD," +
                    "BACK_TRUCK_PAO1," +
                    "BACK_TRUCK_KORKC," +
                    "BACK_TRUCK_FAIKHANG," +
                    "BACK_TRUCK_PAO2," +
                    "BACK_TRUCK_LOCKTU," +
                    "BACK_TRUCK_FAITHAIY" +
            " FROM TRUCK  WHERE STATUS='A'  AND KEY_ID = '"+truckReq.getId()+"' ORDER BY KEY_ID DESC";
         //   System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new getAllTruckMapper());
        }catch (Exception e){
            e.printStackTrace();
            return  result;
        }
        return result;
    }
    @Override
    public int StoreTruck(TruckReq truckReq) {
        log.info("glass:"+truckReq.getGlass());
        int i = 0;
        String plateNo = truckReq.getPlateNo();
        String modelNo = truckReq.getModelNo();
        String vehiName = truckReq.getVehiName();
        String engNo = truckReq.getEngNo();
        String chassNo = truckReq.getChassNo();
        String year = truckReq.getYear();
        String isId = truckReq.getIsId();
        String dateExpIs = truckReq.getDateExpIs();
        String glass = truckReq.getGlass();
        String dateExpTep = truckReq.getDateExpTep();
        String userId = truckReq.getUserId();
        String l01 = truckReq.getL01();
        String l01Exp = truckReq.getL01Exp();
        String l01Km = truckReq.getL01Km();
        String r01 = truckReq.getR01();
        String r01Exp = truckReq.getR01Exp();
        String r01Km = truckReq.getR01Km();
        String lIn01 = truckReq.getlIn01();
        String lIn01Exp = truckReq.getlIn01Exp();
        String lIn01Km = truckReq.getlIn01Km();
        String lIn02 = truckReq.getlIn02();
        String lIn02Exp = truckReq.getlIn02Exp();
        String lIn02Km = truckReq.getlIn02Km();
        String lIn03 = truckReq.getlIn03();
        String lIn03Exp = truckReq.getlIn03Exp();
        String lIn03Km = truckReq.getlIn03Km();
        String lOut01 = truckReq.getlOut01();
        String lOut01Exp = truckReq.getlOut01Exp();
        String lOut01Km = truckReq.getlOut01Km();
        String lOut02 = truckReq.getlOut02();
        String lOut02Exp = truckReq.getlOut02Exp();
        String lOut02Km = truckReq.getlOut02Km();
        String lOut03 = truckReq.getlOut03();
        String lOut03Exp = truckReq.getlOut03Exp();
        String lOut03Km = truckReq.getlOut03Km();
        String rIn01 = truckReq.getrIn01();
        String rIn01Exp = truckReq.getrIn01Exp();
        String rIn01Km = truckReq.getrIn01Km();
        String rIn02 = truckReq.getrIn02();
        String rIn02Exp = truckReq.getrIn02Exp();
        String rIn02Km = truckReq.getrIn02Km();
        String rIn03 = truckReq.getrIn03();
        String rIn03Exp = truckReq.getrIn03Exp();
        String rIn03Km = truckReq.getrIn03Km();
        String rOut01 = truckReq.getrOut01();
        String rOut01Exp = truckReq.getrOut01Exp();
        String rOut01Km = truckReq.getrOut01Km();
        String rOut02 = truckReq.getrOut02();
        String rOut02Exp = truckReq.getrOut02Exp();
        String rOut02Km = truckReq.getrOut02Km();
        String rOut03 = truckReq.getrOut03();
        String rOut03Exp = truckReq.getrOut03Exp();
        String rOut03Km = truckReq.getrOut03Km();
//--
        String tRUCK_NO= truckReq.gettRUCK_NO();
        String tRUCK_SERIAL= truckReq.gettRUCK_SERIAL();
        String tRUCK_MOFAI= truckReq.gettRUCK_MOFAI();
        String tRUCK_FAITHAIY= truckReq.gettRUCK_FAITHAIY();
        String tRUCK_VENKHANG= truckReq.gettRUCK_VENKHANG();
        String tRUCK_PAKUNPHAI= truckReq.gettRUCK_PAKUNPHAI();
        String tRUCK_LEKTHUNG= truckReq.gettRUCK_LEKTHUNG();
        String tRUCK_BUNGTHOM= truckReq.gettRUCK_BUNGTHOM();
        String tRUCK_FAIKHANG= truckReq.gettRUCK_FAIKHANG();
        String tRUCK_LEKYANGLOD= truckReq.gettRUCK_LEKYANGLOD();
        String tRUCK_PAKUNPHAI_DATE= truckReq.gettRUCK_PAKUNPHAI_DATE();
        String tRUCK_GPRS= truckReq.gettRUCK_GPRS();
        String tRUCK_JANLARK=truckReq.gettRUCK_JANLARK();
        String tRUCK_VENMONGNAR= truckReq.gettRUCK_VENMONGNAR();
        String tRUCK_TANGSIT= truckReq.gettRUCK_TANGSIT();
        String tRUCK_FOYPUDNUMFON= truckReq.gettRUCK_FOYPUDNUMFON();
        String tRUCK_FAINAR=truckReq.gettRUCK_FAINAR();
        String tRUCK_VENMONGLG= truckReq.gettRUCK_VENMONGLG();
        String bACK_TRUCK_THABIENLOD= truckReq.getbACK_TRUCK_THABIENLOD();
        String bACK_TRUCK_PAO3= truckReq.getbACK_TRUCK_PAO3();
        String bACK_TRUCK_SO= truckReq.getbACK_TRUCK_SO();
        String bACK_TRUCK_BGTOM= truckReq.getbACK_TRUCK_BGTOM();
        String bACK_TRUCK_LEKKUNZEE= truckReq.getbACK_TRUCK_LEKKUNZEE();
        String bACK_TRUCK_PAO4= truckReq.getbACK_TRUCK_PAO4();
        String bACK_TRUCK_PHABUD= truckReq.getbACK_TRUCK_PHABUD();
        String bACK_TRUCK_PAO1= truckReq.getbACK_TRUCK_PAO1();
        String bACK_TRUCK_KORKC= truckReq.getbACK_TRUCK_KORKC();
        String bACK_TRUCK_FAIKHANG= truckReq.getbACK_TRUCK_FAIKHANG();
        String bACK_TRUCK_PAO2= truckReq.getbACK_TRUCK_PAO2();
        String bACK_TRUCK_LOCKTU= truckReq.getbACK_TRUCK_LOCKTU();
        String bACK_TRUCK_FAITHAIY= truckReq.getbACK_TRUCK_FAITHAIY();
        //-
        try {

            String SQL = "INSERT INTO  TRUCK (PLATENO , MODELNO , VEHICLETYPENAME , ENGINENO , CHASSISNO , FEEYEAR , IS_ID , DATE_EXP_IS , \n" +
                    "GLASS , DATE_EXP_TEP , USERID , L01 , L01EXP , R01 , R01EXP , LIN01,LIN01EXP , LIN02 , LIN02EXP , LIN03 , LIN03EXP ,\n" +
                    " LOUT01 , LOUT01EXP , LOUT02 , LOUT02EXP , LOUT03 ,LOUT03EXP , RIN01 , RIN01EXP , RIN02 , RIN02EXP , RIN03 , RIN03EXP , \n" +
                    " ROUT01 , ROUT01EXP  , ROUT02  , ROUT02EXP  ,ROUT03  ,ROUT03EXP , STATUS ,L01KM ,\n" +
                    "R01KM,\n" +
                    "LIN01KM,\n" +
                    "LIN02KM,\n" +
                    "LIN03KM,\n" +
                    "LOUT01KM,\n" +
                    "LOUT02KM,\n" +
                    "LOUT03KM,\n" +
                    "RIN01KM,\n" +
                    "RIN02KM,\n" +
                    "RIN03KM,\n" +
                    "ROUT01KM,\n" +
                    "ROUT02KM,\n"+
                    "ROUT03KM,\n" +
                    "TRUCK_NO,\n" +
                    "TRUCK_SERIAL,\n" +
                    "TRUCK_MOFAI,\n" +
                    "TRUCK_FAITHAIY,\n" +
                    "TRUCK_VENKHANG,\n" +
                    "TRUCK_PAKUNPHAI,\n" +
                    "TRUCK_LEKTHUNG,\n" +
                    "TRUCK_BUNGTHOM,\n" +
                    "TRUCK_FAIKHANG,\n" +
                    "TRUCK_LEKYANGLOD,\n" +
                    "TRUCK_PAKUNPHAI_DATE,\n" +
                    "TRUCK_GPRS,\n" +
                    "TRUCK_JANLARK,\n" +
                    "TRUCK_VENMONGNAR,\n" +
                    "TRUCK_TANGSIT,\n" +
                    "TRUCK_FOYPUDNUMFON,\n" +
                    "TRUCK_FAINAR,\n" +
                    "TRUCK_VENMONGLG,\n" +
                    "BACK_TRUCK_THABIENLOD,\n" +
                    "BACK_TRUCK_PAO3,\n" +
                    "BACK_TRUCK_SO,\n" +
                    "BACK_TRUCK_BGTOM,\n" +
                    "BACK_TRUCK_LEKKUNZEE,\n" +
                    "BACK_TRUCK_PAO4,\n" +
                    "BACK_TRUCK_PHABUD,\n" +
                    "BACK_TRUCK_PAO1,\n" +
                    "BACK_TRUCK_KORKC,\n" +
                    "BACK_TRUCK_FAIKHANG,\n" +
                    "BACK_TRUCK_PAO2,\n" +
                    "BACK_TRUCK_LOCKTU,\n" +
                    "BACK_TRUCK_FAITHAIY) VALUES ( \n" +
                    "'"+plateNo+"' , '"+modelNo+"' , '"+vehiName+"' , '"+engNo+"' , '"+chassNo+"' , '"+year+"'  ,'"+isId+"' , '"+dateExpIs+"' , \n" +
                    "'"+glass+"' , '"+dateExpTep+"' , '"+userId+"' , '"+l01+"' , '"+l01Exp+"' , '"+r01+"' , '"+r01Exp+"' , '"+lIn01+"' , '"+lIn01Exp+"' , '"+lIn02+"' , '"+lIn02Exp+"' , '"+lIn03+"' , '"+lIn03Exp+"' ,\n" +
                    "'"+lOut01+"' , '"+lOut01Exp+"' , '"+lOut02+"' , '"+lOut02Exp+"' ,'"+lOut03+"' , '"+lOut03Exp+"' , '"+rIn01+"' , '"+rIn01Exp+"' , '"+rIn02+"' ,  '"+rIn02Exp+"' , '"+rIn03+"' , '"+rIn03Exp+"' , \n " +
                    "'"+rOut01+"' , '"+rOut01Exp+"' , '"+rOut02+"' , '"+rOut02Exp+"' , '"+rOut03+"' , '"+rOut03Exp+"' , 'A'  , \n" +
                    "'"+l01Km+"' , '"+r01Km+"' , '"+lIn01Km+"' , '"+lIn02Km+"' , '"+lIn03Km+"' , '"+lOut01Km+"' , '"+lOut02Km+"' , '"+lOut03Km+"' , '"+rIn01Km+"' , '"+rIn02Km+"' , '"+rIn03Km+"' , '"+rOut01Km+"' , '"+rOut02Km+"' , '"+rOut03Km+"'," +
                    " '"+tRUCK_NO+"' ,\n" +
                    "'"+tRUCK_SERIAL+"',\n" +
                    "'"+tRUCK_MOFAI+"',\n" +
                    "'"+tRUCK_FAITHAIY+"',\n" +
                    "'"+tRUCK_VENKHANG+"',\n" +
                    "'"+tRUCK_PAKUNPHAI+"',\n" +
                    "'"+tRUCK_LEKTHUNG+"',\n" +
                    "'"+tRUCK_BUNGTHOM+"',\n" +
                    "'"+tRUCK_FAIKHANG+"',\n" +
                    "'"+tRUCK_LEKYANGLOD+"',\n" +
                    "'"+tRUCK_PAKUNPHAI_DATE+"',\n" +
                    "'"+tRUCK_GPRS+"',\n" +
                    "'"+tRUCK_JANLARK+"',\n" +
                    "'"+tRUCK_VENMONGNAR+"',\n" +
                    "'"+tRUCK_TANGSIT+"',\n" +
                    "'"+tRUCK_FOYPUDNUMFON+"',\n" +
                    "'"+tRUCK_FAINAR+"',\n" +
                    "'"+tRUCK_VENMONGLG+"',\n" +
                    "'"+bACK_TRUCK_THABIENLOD+"',\n" +
                    "'"+bACK_TRUCK_PAO3+"',\n" +
                    "'"+bACK_TRUCK_SO+"',\n" +
                    "'"+bACK_TRUCK_BGTOM+"',\n" +
                    "'"+bACK_TRUCK_LEKKUNZEE+"',\n" +
                    "'"+bACK_TRUCK_PAO4+"',\n" +
                    "'"+bACK_TRUCK_PHABUD+"',\n" +
                    "'"+bACK_TRUCK_PAO1+"',\n" +
                    "'"+bACK_TRUCK_KORKC+"',\n" +
                    "'"+bACK_TRUCK_FAIKHANG+"',\n" +
                    "'"+bACK_TRUCK_PAO2+"',\n" +
                    "'"+bACK_TRUCK_LOCKTU+"',\n" +
                    "'"+bACK_TRUCK_FAITHAIY+"' ) ";
          //  System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public int updateTruck(TruckReq truckReq) {
        int i = 0;
        String id = truckReq.getId();
        String plateNo = truckReq.getPlateNo();
        String modelNo = truckReq.getModelNo();
        String vehiName = truckReq.getVehiName();
        String engNo = truckReq.getEngNo();
        String chassNo = truckReq.getChassNo();
        String year = truckReq.getYear();
        String isId = truckReq.getIsId();
        String dateExpIs = truckReq.getDateExpIs();
        String glass = truckReq.getGlass();
        String dateExpTep = truckReq.getDateExpTep();
        String userId = truckReq.getUserId();
        String l01 = truckReq.getL01();
        String l01Exp = truckReq.getL01Exp();
        String r01 = truckReq.getR01();
        String r01Exp = truckReq.getR01Exp();
        String lIn01 = truckReq.getlIn01();
        String lIn01Exp = truckReq.getlIn01Exp();
        String lIn02 = truckReq.getlIn02();
        String lIn02Exp = truckReq.getlIn02Exp();
        String lIn03 = truckReq.getlIn03();
        String lIn03Exp = truckReq.getlIn03Exp();
        String lOut01 = truckReq.getlOut01();
        String lOut01Exp = truckReq.getlOut01Exp();
        String lOut02 = truckReq.getlOut02();
        String lOut02Exp = truckReq.getlOut02Exp();
        String lOut03 = truckReq.getlOut03();
        String lOut03Exp = truckReq.getlOut03Exp();
        String rIn01 = truckReq.getrIn01();
        String rIn01Exp = truckReq.getrIn01Exp();
        String rIn02 = truckReq.getrIn02();
        String rIn02Exp = truckReq.getrIn02Exp();
        String rIn03 = truckReq.getrIn03();
        String rIn03Exp = truckReq.getrIn03Exp();
        String rOut01 = truckReq.getrOut01();
        String rOut01Exp = truckReq.getrOut01Exp();
        String rOut02 = truckReq.getrOut02();
        String rOut02Exp = truckReq.getrOut02Exp();
        String rOut03 = truckReq.getrOut03();
        String rOut03Exp = truckReq.getrOut03Exp();

        String l01Km = truckReq.getL01Km();
        String r01Km = truckReq.getR01Km();
        String lIn01Km = truckReq.getlIn01Km();
        String lIn02Km = truckReq.getlIn02Km();
        String lIn03Km = truckReq.getlIn03Km();
        String lOut01Km = truckReq.getlOut01Km();
        String lOut02Km = truckReq.getlOut02Km();
        String lOut03Km = truckReq.getlOut03Km();
        String rIn01Km = truckReq.getrIn01Km();
        String rIn02Km = truckReq.getrIn02Km();
        String rIn03Km = truckReq.getrIn03Km();
        String rOut01Km = truckReq.getrOut01Km();
        String rOut02Km = truckReq.getrOut02Km();
        String rOut03Km = truckReq.getrOut03Km();

        String tRUCK_NO= truckReq.gettRUCK_NO();
        String tRUCK_SERIAL= truckReq.gettRUCK_SERIAL();
        String tRUCK_MOFAI= truckReq.gettRUCK_MOFAI();
        String tRUCK_FAITHAIY= truckReq.gettRUCK_FAITHAIY();
        String tRUCK_VENKHANG= truckReq.gettRUCK_VENKHANG();
        String tRUCK_PAKUNPHAI= truckReq.gettRUCK_PAKUNPHAI();
        String tRUCK_LEKTHUNG= truckReq.gettRUCK_LEKTHUNG();
        String tRUCK_BUNGTHOM= truckReq.gettRUCK_BUNGTHOM();
        String tRUCK_FAIKHANG= truckReq.gettRUCK_FAIKHANG();
        String tRUCK_LEKYANGLOD= truckReq.gettRUCK_LEKYANGLOD();
        String tRUCK_PAKUNPHAI_DATE= truckReq.gettRUCK_PAKUNPHAI_DATE();
        String tRUCK_GPRS= truckReq.gettRUCK_GPRS();
        String tRUCK_JANLARK=truckReq.gettRUCK_JANLARK();
        String tRUCK_VENMONGNAR= truckReq.gettRUCK_VENMONGNAR();
        String tRUCK_TANGSIT= truckReq.gettRUCK_TANGSIT();
        String tRUCK_FOYPUDNUMFON= truckReq.gettRUCK_FOYPUDNUMFON();
        String tRUCK_FAINAR=truckReq.gettRUCK_FAINAR();
        String tRUCK_VENMONGLG= truckReq.gettRUCK_VENMONGLG();
        String bACK_TRUCK_THABIENLOD= truckReq.getbACK_TRUCK_THABIENLOD();
        String bACK_TRUCK_PAO3= truckReq.getbACK_TRUCK_PAO3();
        String bACK_TRUCK_SO= truckReq.getbACK_TRUCK_SO();
        String bACK_TRUCK_BGTOM= truckReq.getbACK_TRUCK_BGTOM();
        String bACK_TRUCK_LEKKUNZEE= truckReq.getbACK_TRUCK_LEKKUNZEE();
        String bACK_TRUCK_PAO4= truckReq.getbACK_TRUCK_PAO4();
        String bACK_TRUCK_PHABUD= truckReq.getbACK_TRUCK_PHABUD();
        String bACK_TRUCK_PAO1= truckReq.getbACK_TRUCK_PAO1();
        String bACK_TRUCK_KORKC= truckReq.getbACK_TRUCK_KORKC();
        String bACK_TRUCK_FAIKHANG= truckReq.getbACK_TRUCK_FAIKHANG();
        String bACK_TRUCK_PAO2= truckReq.getbACK_TRUCK_PAO2();
        String bACK_TRUCK_LOCKTU= truckReq.getbACK_TRUCK_LOCKTU();
        String bACK_TRUCK_FAITHAIY= truckReq.getbACK_TRUCK_FAITHAIY();
        try {

            String SQL = "  UPDATE TRUCK SET PLATENO = '"+plateNo+"' , MODELNO =  '"+modelNo+"' , VEHICLETYPENAME =  '"+vehiName+"' ,ENGINENO =  '"+engNo+"' , \n " +
                    " CHASSISNO = '"+chassNo+"' , FEEYEAR = '"+year+"'  , IS_ID = '"+isId+"' , DATE_EXP_IS = '"+dateExpIs+"',  \n" +
                    "  GLASS = '"+glass+"' , DATE_EXP_TEP = '"+dateExpTep+"' , USERID =  '"+userId+"' , L01 =  '"+l01+"' , L01EXP = '"+l01Exp+"' , R01 = '"+r01+"' , R01EXP =  '"+r01Exp+"' , \n " +
                    " LIN01 = '"+lIn01+"' , LIN01EXP=  '"+lIn01Exp+"' , LIN02 = '"+lIn02+"' , LIN02EXP =  '"+lIn02Exp+"' , LIN03 =  '"+lIn03+"' , LIN03EXP =  '"+lIn03Exp+"' ,\n" +
                    "  LOUT01 = '"+lOut01+"' , LOUT01EXP = '"+lOut01Exp+"' , LOUT02 =  '"+lOut02+"' , LOUT02EXP = '"+lOut02Exp+"' , LOUT03 = '"+lOut03+"' , LOUT03EXP = '"+lOut03Exp+"' , \n" +
                    " RIN01 = '"+rIn01+"' , RIN01EXP =  '"+rIn01Exp+"' ,  RIN02 = '"+rIn02+"' , RIN02EXP = '"+rIn02Exp+"' , RIN03= '"+rIn03+"' , RIN03EXP = '"+rIn03Exp+"' , \n " +
                    "  ROUT01= '"+rOut01+"' , ROUT01EXP =  '"+rOut01Exp+"' , ROUT02 = '"+rOut02+"' , ROUT02EXP = '"+rOut02Exp+"' , ROUT03 = '"+rOut03+"' , ROUT03EXP = '"+rOut03Exp+"' , \n" +
                    " L01KM  = '"+l01Km+"',\n" +
                    "R01KM  = '"+r01Km+"',\n" +
                    "LIN01KM = '"+lIn01Km+"',\n" +
                    "LIN02KM = '"+lIn02Km+"',\n" +
                    "LIN03KM = '"+lIn03Km+"',\n" +
                    "LOUT01KM = '"+lOut01Km+"',\n" +
                    "LOUT02KM = '"+lOut02Km+"',\n" +
                    "LOUT03KM = '"+lOut03Km+"',\n" +
                    "RIN01KM = '"+rIn01Km+"',\n" +
                    "RIN02KM = '"+rIn02Km+"',\n" +
                    "RIN03KM = '"+rIn03Km+"',\n" +
                    "ROUT01KM = '"+rOut01Km+"',\n" +
                    "ROUT02KM = '"+rOut02Km+"',\n" +
                    "ROUT03KM = '"+rOut03Km+"',\n" +
            "       tRUCK_NO='"+tRUCK_NO+"' ,\n" +
            "       tRUCK_SERIAL='"+tRUCK_SERIAL+"',\n" +
            "       tRUCK_MOFAI='"+tRUCK_MOFAI+"',\n" +
            "       tRUCK_FAITHAIY='"+tRUCK_FAITHAIY+"',\n" +
            "       tRUCK_VENKHANG='"+tRUCK_VENKHANG+"',\n" +
            "       tRUCK_PAKUNPHAI='"+tRUCK_PAKUNPHAI+"',\n" +
            "       tRUCK_LEKTHUNG='"+tRUCK_LEKTHUNG+"',\n" +
            "       tRUCK_BUNGTHOM='"+tRUCK_BUNGTHOM+"',\n" +
            "       tRUCK_FAIKHANG='"+tRUCK_FAIKHANG+"',\n" +
            "       tRUCK_LEKYANGLOD='"+tRUCK_LEKYANGLOD+"',\n" +
            "       tRUCK_PAKUNPHAI_DATE='"+tRUCK_PAKUNPHAI_DATE+"',\n" +
            "       tRUCK_GPRS='"+tRUCK_GPRS+"',\n" +
            "       tRUCK_JANLARK='"+tRUCK_JANLARK+"',\n" +
            "       tRUCK_VENMONGNAR='"+tRUCK_VENMONGNAR+"',\n" +
            "       tRUCK_TANGSIT='"+tRUCK_TANGSIT+"',\n" +
            "       tRUCK_FOYPUDNUMFON='"+tRUCK_FOYPUDNUMFON+"',\n" +
            "       tRUCK_FAINAR='"+tRUCK_FAINAR+"',\n" +
            "       tRUCK_VENMONGLG='"+tRUCK_VENMONGLG+"',\n" +
            "       bACK_TRUCK_THABIENLOD='"+bACK_TRUCK_THABIENLOD+"',\n" +
            "       bACK_TRUCK_PAO3='"+bACK_TRUCK_PAO3+"',\n" +
            "       bACK_TRUCK_SO='"+bACK_TRUCK_SO+"',\n" +
            "       bACK_TRUCK_BGTOM='"+bACK_TRUCK_BGTOM+"',\n" +
            "       bACK_TRUCK_LEKKUNZEE='"+bACK_TRUCK_LEKKUNZEE+"',\n" +
            "       bACK_TRUCK_PAO4='"+bACK_TRUCK_PAO4+"',\n" +
            "       bACK_TRUCK_PHABUD='"+bACK_TRUCK_PHABUD+"',\n" +
            "       bACK_TRUCK_PAO1='"+bACK_TRUCK_PAO1+"',\n" +
            "       bACK_TRUCK_KORKC='"+bACK_TRUCK_KORKC+"',\n" +
            "       bACK_TRUCK_FAIKHANG='"+bACK_TRUCK_FAIKHANG+"',\n" +
            "       bACK_TRUCK_PAO2='"+bACK_TRUCK_PAO2+"',\n" +
            "       bACK_TRUCK_LOCKTU='"+bACK_TRUCK_LOCKTU+"',\n" +
            "       bACK_TRUCK_FAITHAIY='"+bACK_TRUCK_FAITHAIY+"'\n" +
                    " WHERE KEY_ID = '"+id+"' ";
           // System.out.println(SQL);
            i= EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int deleteTruck(int truckId) {
        int i = 0;
        try {
            String SQL = "UPDATE TRUCK  SET STATUS = 'D' WHERE KEY_ID = "+truckId+" ";
          //  System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public List<DeliveryOut> getAllDelivery() {

        List<DeliveryOut> result = new ArrayList<>();
        try {

            String SQL = " SELECT \n" +
                    "KEY_ID ,            \n" +
                    "ID_DELIVERY ,          \n" +
                    "DATE_TIME ,               \n" +
                    "TRUCK_ID ,               \n" +
                    "PLATENO  ,                  \n" +
                    "STAFT_ID ,                  \n" +
                    "STAFT_NAME  ,              \n" +
                    "STAFT_MOBILE ,            \n" +
                    "CUSTOMER_NAME  ,         \n" +
                    "ADDRESS_START ,          \n" +
                    "ADDRESS_END  ,       \n" +
                    "DISTANCE    ,      \n" +
                    "PRODUCT_ID ,             \n" +
                    "PRODUCT_NAME  ,          \n" +
                    "AMOUNT     ,                \n" +
                    "WEIGHT    ,                  \n" +
                    "GLASS_AMOUNT    ,              \n" +
                    "ADDRESS_START_DATE  ,          \n" +
                    "ADDRESS_END_DATE   ,           \n" +
                    "ADDRESS_START_DETAIL  ,       \n" +
                    "ADDRESS_END_DETAIL   ,     \n" +
                    "PAY   FROM DELIVERY WHERE STATUS = 'A'  ";
             result = EBankJdbcTemplate.query(SQL , new getAllDeliveryMapper());
        }catch (Exception e){
            e.printStackTrace();
            return  result;
        }
        return result;
    }
    @Override
    public int StoreDelivery(DeliveryReq deliveryReq) {
        return 0;
    }
    @Override
    public int UpdateDelivery(DeliveryReq deliveryReq) {
        return 0;
    }
    @Override
    public int DeleteDelivery(String id) {
        int i = 0;
        try {
            String SQL = "UPDATE DELIVERY  SET STATUS = 'D' ";
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
String SQL="";
    @Override
    public List<TruckDetails> ReportGive(ResFromDateReq resFromDateReq) {
        try{
            if(resFromDateReq.getStartDate() == null || resFromDateReq.getEndDate() == null ){
                SQL = "SELECT H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,H_VICIVLE_BRANCHTYPE,\n" +
                        "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) AS carGive,\n" +
                        "SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)+cast(replace(staff02_payAll, ',', '') as unsigned)) AS carPay,\n" +
                        "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) + SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned))- sum (cast(replace(staff02_payAll, ',', '') as unsigned)) AS kumLaiy\n" +
                        "FROM V_REPORT_GIVE_OUT_CAR   GROUP BY  H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,H_VICIVLE_BRANCHTYPE";
log.info("sql:"+SQL);
            }else {
                SQL = "SELECT H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,H_VICIVLE_BRANCHTYPE,\n" +
                        "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) AS carGive,\n" +
                        "SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)+cast(replace(staff02_payAll, ',', '') as unsigned)) AS carPay,\n" +
                        "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) + SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned))- sum (cast(replace(staff02_payAll, ',', '') as unsigned)) AS kumLaiy\n" +
                        "FROM V_REPORT_GIVE_OUT_CAR  WHERE OUT_DATE BETWEEN '" + resFromDateReq.getStartDate() + "' and '" + resFromDateReq.getEndDate() + "' GROUP BY  H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,H_VICIVLE_BRANCHTYPE";
                log.info("sql:"+SQL);
            }
            return EBankJdbcTemplate.query(SQL, new RowMapper<TruckDetails>() {
                @Override
                public TruckDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TruckDetails tr = new TruckDetails();
                    tr.setCarTabienLod(rs.getString("H_VICIVLE_NUMBER"));
                 //   tr.setDetailsNo(rs.getString("LAHUD_POYLOD"));
                    tr.setCarModel(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setCarType(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setCarGive(rs.getString("carGive"));
                    tr.setCarPay(rs.getString("carPay"));
                    tr.setKumLaiy(rs.getString("kumLaiy"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TruckDetailsGroupDataDetails> ReportGiveDetails(TruckDetailsReq truckDetailsReq) {
        try{
            SQL ="SELECT OUT_DATE,IN_DATE,PRO_NAME,DETAIL,H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,LAHUD_POYLOD,H_VICIVLE_BRANCHTYPE,\n" +
                    "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) AS carGive,\n" +
                    "SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)+cast(replace(staff02_payAll, ',', '') as unsigned)) AS carPay,\n" +
                    "SUM(cast(replace(TOTAL_PRICE, ',', '') as unsigned)) + SUM (cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)\n" +
                    "-cast(replace(staff02_payAll, ',', '') as unsigned)) AS kumLaiy\n" +
                    "FROM V_REPORT_GIVE_OUT_CAR where H_VICIVLE_NUMBER='"+truckDetailsReq.getCarLodNo()+"' GROUP BY  H_VICIVLE_NUMBER,H_VICIVLE_BRANCH,H_VICIVLE_BRANCHTYPE,LAHUD_POYLOD,OUT_DATE,IN_DATE,PRO_NAME,DETAIL";
            return EBankJdbcTemplate.query(SQL, new RowMapper<TruckDetailsGroupDataDetails>() {
                @Override
                public TruckDetailsGroupDataDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TruckDetailsGroupDataDetails tr = new TruckDetailsGroupDataDetails();
                    tr.setCarTabienLod(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setDetailsNo(rs.getString("LAHUD_POYLOD"));
                    tr.setCarModel(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setCarType(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setCarGive(rs.getDouble("carGive"));
                    tr.setCarPay(rs.getDouble("carPay"));
                    tr.setKumLaiy(rs.getDouble("kumLaiy"));
                    tr.setOutDate(rs.getString("OUT_DATE"));
                    tr.setInDate(rs.getString("IN_DATE"));
                    tr.setProName(rs.getString("PRO_NAME"));
                    tr.setPlName(rs.getString("DETAIL"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //=======================================================report GIVE ---------------PAY  =====   KUM LAIY
}
