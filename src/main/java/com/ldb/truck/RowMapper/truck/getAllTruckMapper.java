package com.ldb.truck.RowMapper.truck;

import com.ldb.truck.Model.Login.Truck.TruckOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAllTruckMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        TruckOut data = new TruckOut();
        try {
            data.setId(rs.getString("KEY_ID"));
            data.setPlateNo(rs.getString("PLATENO"));
            data.setModelNo(rs.getString("MODELNO"));
            data.setVehiName(rs.getString("VEHICLETYPENAME"));
            data.setEngNo(rs.getString("ENGINENO"));
            data.setChassNo(rs.getString("CHASSISNO"));
            data.setYear(rs.getString("FEEYEAR"));
            data.setIsId(rs.getString("IS_ID"));
            data.setDateExpIs(rs.getString("DATE_EXP_IS"));
            data.setGlass(rs.getString("GLASS"));
            data.setDateExpTep(rs.getString("DATE_EXP_TEP"));
            data.setUserId(rs.getString("USERID"));
            data.setL01(rs.getString("L01"));
            data.setL01Exp(rs.getString("L01EXP"));
            data.setR01(rs.getString("R01"));
            data.setR01Exp(rs.getString("R01EXP"));
            data.setlIn01(rs.getString("LIN01"));
            data.setlIn01Exp(rs.getString("LIN01EXP"));
            data.setlIn02(rs.getString("LIN02"));
            data.setlIn02Exp(rs.getString("LIN02EXP"));
            data.setlIn03(rs.getString("LIN03"));
            data.setlIn03Exp(rs.getString("LIN03EXP"));
            data.setlOut01(rs.getString("LOUT01"));
            data.setlOut01Exp(rs.getString("LOUT01EXP"));
            data.setlOut02(rs.getString("LOUT02"));
            data.setlOut02Exp(rs.getString("LOUT02EXP"));
            data.setlOut03(rs.getString("LOUT03"));
            data.setlOut03Exp(rs.getString("LOUT03EXP"));
            data.setrIn01(rs.getString("RIN01"));
            data.setrIn01Exp(rs.getString("RIN01EXP"));
            data.setrIn02(rs.getString("RIN02"));
            data.setrIn02Exp(rs.getString("RIN02EXP"));
            data.setrIn03(rs.getString("RIN03"));
            data.setrIn03Exp(rs.getString("RIN03EXP"));
            data.setrOut01(rs.getString("ROUT01"));
            data.setrOut01Exp(rs.getString("ROUT01EXP"));
            data.setrOut02(rs.getString("ROUT02"));
            data.setrOut02Exp(rs.getString("ROUT02EXP"));
            data.setrOut03(rs.getString("ROUT03"));
            data.setrOut03Exp(rs.getString("ROUT03EXP"));
            data.setL01Km(rs.getString("L01KM"));
            data.setR01Km(rs.getString("R01KM"));
            data.setlIn01Km(rs.getString("LIN01KM"));
            data.setlIn02Km(rs.getString("LIN02KM"));
            data.setlIn03Km(rs.getString("LIN03KM"));
            data.setlOut01Km(rs.getString("LOUT01KM"));
            data.setlOut02Km(rs.getString("LOUT02KM"));
            data.setlOut03Km(rs.getString("LOUT03KM"));
            data.setrIn01Km(rs.getString("RIN01KM"));
            data.setrIn02Km(rs.getString("RIN02KM"));
            data.setrIn03Km(rs.getString("RIN03KM"));
            data.setrOut01Km(rs.getString("ROUT01KM"));
            data.setrOut02Km(rs.getString("ROUT02KM"));
            data.setrOut03Km(rs.getString("ROUT03KM"));
            //---
            data.settRUCK_NO(rs.getString("tRUCK_NO"));
            data.settRUCK_SERIAL(rs.getString("tRUCK_SERIAL"));
            data.settRUCK_MOFAI(rs.getString("tRUCK_MOFAI"));
            data.settRUCK_FAITHAIY(rs.getString("tRUCK_FAITHAIY"));
            data.settRUCK_VENKHANG(rs.getString("tRUCK_VENKHANG"));
            data.settRUCK_PAKUNPHAI(rs.getString("tRUCK_PAKUNPHAI"));
            data.settRUCK_LEKTHUNG(rs.getString("tRUCK_LEKTHUNG"));
            data.settRUCK_BUNGTHOM(rs.getString("tRUCK_BUNGTHOM"));
            data.settRUCK_FAIKHANG(rs.getString("tRUCK_FAIKHANG"));
            data.settRUCK_LEKYANGLOD(rs.getString("tRUCK_LEKYANGLOD"));
            data.settRUCK_PAKUNPHAI_DATE(rs.getString("tRUCK_PAKUNPHAI_DATE"));
            data.settRUCK_GPRS(rs.getString("tRUCK_GPRS"));
            data.settRUCK_JANLARK(rs.getString("tRUCK_JANLARK"));
            data.settRUCK_VENMONGNAR(rs.getString("tRUCK_VENMONGNAR"));
            data.settRUCK_TANGSIT(rs.getString("tRUCK_TANGSIT"));
            data.settRUCK_FOYPUDNUMFON(rs.getString("tRUCK_FOYPUDNUMFON"));
            data.settRUCK_FAINAR(rs.getString("tRUCK_FAINAR"));
            data.settRUCK_VENMONGLG(rs.getString("tRUCK_VENMONGLG"));
            data.setbACK_TRUCK_THABIENLOD(rs.getString("bACK_TRUCK_THABIENLOD"));
            data.setbACK_TRUCK_PAO3(rs.getString("bACK_TRUCK_PAO3"));
            data.setbACK_TRUCK_SO(rs.getString("bACK_TRUCK_SO"));
            data.setbACK_TRUCK_BGTOM(rs.getString("bACK_TRUCK_BGTOM"));
            data.setbACK_TRUCK_LEKKUNZEE(rs.getString("bACK_TRUCK_LEKKUNZEE"));
            data.setbACK_TRUCK_PAO4(rs.getString("bACK_TRUCK_PAO4"));
            data.setbACK_TRUCK_PHABUD(rs.getString("bACK_TRUCK_PHABUD"));
            data.setbACK_TRUCK_PAO1(rs.getString("bACK_TRUCK_PAO1"));
            data.setbACK_TRUCK_KORKC(rs.getString("bACK_TRUCK_KORKC"));
            data.setbACK_TRUCK_FAIKHANG(rs.getString("bACK_TRUCK_FAIKHANG"));
            data.setbACK_TRUCK_PAO2(rs.getString("bACK_TRUCK_PAO2"));
            data.setbACK_TRUCK_LOCKTU(rs.getString("bACK_TRUCK_LOCKTU"));
            data.setbACK_TRUCK_FAITHAIY(rs.getString("bACK_TRUCK_FAITHAIY"));
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
        return data;
    }
}
