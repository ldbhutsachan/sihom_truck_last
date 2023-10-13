package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.v_performance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class v_performanceMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        v_performance data = new v_performance();
        try {
                    data.setKEY_ID(rs.getString("key_id"));
                    data.setPERFORMANCEBILLNO(rs.getString("PERFORMANCEBILLNO"));
                    data.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
                    data.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
                    data.setPRO_NAME(rs.getString("PRO_NAME"));
                    data.setPRO_TYPE(rs.getString("PRO_TYPE"));
                    data.setLAIYATHANG(rs.getString("LAIYATHANG"));
                    data.setSAINUMMUN(rs.getString("SAINUMMUN"));
                    data.setPRIECENUMNUN(rs.getString("PRIECENUMNUN"));
                    data.setTOTAL_PRICE(rs.getString("TOTAL_PRICE"));
                    data.setFEETOTAL(rs.getString("FEETOTAL"));
                    data.setTOTALNUMMUN(rs.getString("TOTALNUMMUN"));
                    data.setStatus(rs.getString("status"));
                    data.setPERFORMANCEDATE(rs.getString("PERFORMANCEDATE"));
                    data.setTotalDay(rs.getString("totalDay"));
                    data.setCurrency(rs.getString("CURRENCY"));
                    data.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));

            // CURRENCY
            // STAFF_BIALIENG_CUR

        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
