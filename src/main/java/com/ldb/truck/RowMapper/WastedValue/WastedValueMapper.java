package com.ldb.truck.RowMapper.WastedValue;


import com.ldb.truck.Model.Login.Performance.v_performance;
import com.ldb.truck.Model.Login.Performance.v_wastedValue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WastedValueMapper implements RowMapper
{
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        v_wastedValue data = new v_wastedValue();
        try {

            data.setFeeOvertime1(rs.getString("feeOvertime1"));
            data.setFeeJumPo2(rs.getString("feeJumPo2"));
            data.setFeePolish3(rs.getString("feePolish3"));
            data.setFeeTaxung4(rs.getString("feeTaxung4"));
            data.setFeeTiew5(rs.getString("feeTiew5"));
            data.setFeeLakSao(rs.getString("feeLakSao"));
            data.setFeePassport(rs.getString("feePassport"));
            data.setFeevacin(rs.getString("feevacin"));
            data.setFeesing(rs.getString("feesing"));
            data.setFeesaphan(rs.getString("feesaphan"));
            data.setFeeyoktu(rs.getString("feeyoktu"));
            data.setFeecontrainer(rs.getString("feecontrainer"));
            data.setFeepayang(rs.getString("feepayang"));
            data.setKilometer_before(rs.getString("KIM_KILO"));
            data.setDistance(rs.getString("LAIYATHANG"));
            data.setDistance_sum(rs.getString("LAIYATHANG_SUM"));
//            data.setPERFORMANCEOVERTIME(rs.getString("PERFORMANCEOVERTIME"));
//            data.setPERFORMANCEJUMPHO(rs.getString("PERFORMANCEJUMPHO"));
//            data.setPERFORMANCEFEEPOLISH(rs.getString("PERFORMANCEFEEPOLISH"));
//            data.setPERFORMANCEFEETAXUNG(rs.getString("PERFORMANCEFEETAXUNG"));
//            data.setPERFORMANCEFEETIEW(rs.getString("PERFORMANCEFEETIEW"));
//            data.setPERFORMANCEOVERVN(rs.getString("PERFORMANCEOVERVN"));
//            data.setPERFORMANCEBODERLAK20(rs.getString("PERFORMANCEBODERLAK20"));
//            data.setPERFORMANCEPASSPORT(rs.getString("PERFORMANCEPASSPORT"));
//            data.setPERFORMANCEVACCINE(rs.getString("PERFORMANCEVACCINE"));
//            data.setPERFORMANCEFEESING(rs.getString("PERFORMANCEFEESING"));
//            data.setPERFORMANCEFEESAPHAN(rs.getString("PERFORMANCEFEESAPHAN"));
//            data.setPERFORMANCEFEEYOKTU(rs.getString("PERFORMANCEFEEYOKTU"));
//            data.setPERFORMANCEFEEOUTCONTAINER(rs.getString("PERFORMANCEFEEOUTCONTAINER"));
//            data.setPERFORMANCEFE_PAYANG(rs.getString("PERFORMANCEFE_PAYANG"));

        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
