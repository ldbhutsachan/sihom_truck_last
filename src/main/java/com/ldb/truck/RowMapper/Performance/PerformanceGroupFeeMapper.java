package com.ldb.truck.RowMapper.Performance;
import com.ldb.truck.Model.Login.Performance.performanceGroupFee;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PerformanceGroupFeeMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        performanceGroupFee data = new performanceGroupFee();
        try {
            data.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
            data.setPERFORMANCEOVERTIME(rs.getString("PERFORMANCEOVERTIME"));
            data.setPERFORMANCEJUMPHO(rs.getString("PERFORMANCEJUMPHO"));
            data.setPERFORMANCEFEEPOLISH(rs.getString("PERFORMANCEFEEPOLISH"));
            data.setPERFORMANCEFEETAXUNG(rs.getString("PERFORMANCEFEETAXUNG"));
            data.setPERFORMANCEFEETIEW(rs.getString("PERFORMANCEFEETIEW"));
            data.setPERFORMANCEOVERVN(rs.getString("PERFORMANCEOVERVN"));
            data.setPERFORMANCEBODERLAK20(rs.getString("PERFORMANCEBODERLAK20"));
            data.setPERFORMANCEPASSPORT(rs.getString("PERFORMANCEPASSPORT"));
            data.setPERFORMANCEVACCINE(rs.getString("PERFORMANCEVACCINE"));
            data.setPERFORMANCEFEESING(rs.getString("PERFORMANCEFEESING"));
            data.setPERFORMANCEFEESAPHAN(rs.getString("PERFORMANCEFEESAPHAN"));
            data.setPERFORMANCEFEEYOKTU(rs.getString("PERFORMANCEFEEYOKTU"));
            data.setPERFORMANCEFEEOUTCONTAINER(rs.getString("PERFORMANCEFEEOUTCONTAINER"));
            //data.setFEEUNIT(rs.getString("FEEUNIT"));
            data.setFEETOTAL(rs.getString("FEETOTAL"));
            data.setPERFORMANCEFE_PAYANG(rs.getString("PERFORMANCEFE_PAYANG"));
            data.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));

        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
