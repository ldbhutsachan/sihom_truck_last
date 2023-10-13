package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.getBillNo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBillNoMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        getBillNo data = new getBillNo();
        try {
            data.setLAHUD_POYLOD(rs.getString("LAHUD_POYLOD"));
            data.setOUT_DATE(rs.getString("OUT_DATE"));
            data.setSTAFFNAME(rs.getString("STAFFNAME"));
            data.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
            data.setF_CARD_NO(rs.getString("F_CARD_NO"));
            data.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
            data.setRECIVER(rs.getString("RECIVER"));
            data.setPrice(rs.getString("price"));
            data.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
            data.setSTAFFNAME1(rs.getString("STAFFNAME1"));
            data.setPRO_NAME(rs.getString("PRO_NAME"));
            data.setROAD_DURATION(rs.getString("ROAD_DURATION"));
            data.setKim_KM(rs.getString("KIM_KM"));
            data.setCurrency(rs.getString("CURRENCY"));
            data.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
            data.setProSize(rs.getString("PRODUCT_SIZE"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
