package com.ldb.truck.RowMapper.Revert;


import com.ldb.truck.Model.Login.Performance.v_performance;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReverPerFormanceMapper implements RowMapper
{
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
            data.setPerformanceReDate(rs.getString("PERFORMANCEREDATE"));
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
            data.setCurrency(rs.getString("CURRENCY"));
            data.setStaff_Curr(rs.getString("STAFF_BIALIENG_CUR"));
            data.setProSize(rs.getDouble("PRODUCT_SIZE"));
            data.setPrice(rs.getString("PRICE"));

//          ============================  new ============================================
            data.setAdd_feeOvertime1(rs.getString("add_feeOvertime1"));
            data.setAdd_feeJumPo2(rs.getString("add_feeJumPo2"));
            data.setAdd_feePolish3(rs.getString("add_feePolish3"));
            data.setAdd_feeTaxung4(rs.getString("add_feeTaxung4"));
            data.setAdd_feeTiew5(rs.getString("add_feeTiew5"));
            data.setAdd_feesing(rs.getString("add_feesing"));
            data.setAdd_feesaphan(rs.getString("add_feesaphan"));
            data.setAdd_feeyoktu(rs.getString("add_feeyoktu"));
            data.setAdd_feecontrainer(rs.getString("add_feecontrainer"));
            data.setAdd_feepayang(rs.getString("add_feepayang"));
//          ============================  new ============================================
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
