package com.ldb.truck.RowMapper.DetailsMapper;

import com.ldb.truck.Model.Login.Details.Details;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class DetailsMapper implements RowMapper
{
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Details tr =new Details();
        tr.setKEY_ID(rs.getString("KEY_ID"));
        tr.setLaHud_poyLod(rs.getString("LAHUD_POYLOD"));
        tr.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
        tr.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
        tr.setPRODUCT_AMOUNT(rs.getString("PRODUCT_AMOUNT"));
        tr.setPRODUCT_SIZE(rs.getString("PRODUCT_SIZE"));
        tr.setPRODUCT_DETAILS(rs.getString("PRODUCT_DETAILS"));
        tr.setPRODUCT_FROM(rs.getString("PRODUCT_FROM"));
        tr.setPRODUCT_TO(rs.getString("PRODUCT_TO"));
        tr.setPLACE_PD_FROM(rs.getString("PLACE_PD_FROM"));
        tr.setPLACE_PD_TO(rs.getString("PLACE_PD_TO"));
        tr.setSTAFF_ID_NUM1(rs.getString("STAFF_ID_NUM1"));
        tr.setSTAFF_ID_NUM2(rs.getString("STAFF_ID_NUM2"));
        tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
        tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
        tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
        tr.setHEADER_ID(rs.getString("HEADER_ID"));
        tr.setFOOTER_ID(rs.getString("FOOTER_ID"));
        tr.setOUT_DATE(rs.getString("OUT_DATE"));
        tr.setIN_DATE(rs.getString("IN_DATE"));
        tr.setLAIYATHANG(rs.getString("LAIYATHANG"));
        tr.setSAINUMMUN(rs.getString("SAINUMMUN"));
        tr.setNUMNUKLOD(rs.getString("NUMNUKLOD"));
        tr.setKONGNARLOD(rs.getString("KONGNARLOD"));
        tr.setKHG_MUE_TIDLOD(rs.getString("KHG_MUE_TIDLOD"));
        tr.setKim_KILO(rs.getString("KIM_KILO"));
        tr.setPRICE(rs.getString("PRICE"));
        tr.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
        tr.setD_STATUS(rs.getString("d_status"));
        tr.setPriceNamMun(rs.getString("PRIECENUMNUN"));
        tr.setTotalDay(rs.getString("totalDay"));
        tr.setCurrency(rs.getString("CURRENCY"));
        tr.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
        return tr;
    }
}
