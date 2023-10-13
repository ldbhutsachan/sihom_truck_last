package com.ldb.truck.RowMapper.Performance;

import com.ldb.truck.Model.Login.Performance.Performance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Performance data = new Performance();
        try {
            data.setPerformanceBillNo(rs.getString("performanceBillNo"));
            data.setPerformanceReDate(rs.getString("performanceReDate"));
            data.setPerformanceDate(rs.getString("performanceDate"));
            data.setPerformanceTotal(rs.getString("performanceTotal"));
            data.setPerformanceOvertime(rs.getString("performanceOvertime"));
            data.setPerformanceJumPho(rs.getString("performanceJumPho"));
            data.setPerformanceFeePolish(rs.getString("performanceFeePolish"));
            data.setPerformanceFeeTaxung(rs.getString("performanceFeeTaxung"));
            data.setPerformanceFeeTiew(rs.getString("performanceFeeTiew"));
            data.setPerformanceOverVN(rs.getString("performanceOverVN"));
            data.setPerformanceBoderLak20(rs.getString("performanceBoderLak20"));
            data.setPerformancePassport(rs.getString("performancePassport"));
            data.setPerformanceVaccine(rs.getString("performanceVaccine"));
            data.setPerformanceFeeSing(rs.getString("performanceFeeSing"));
            data.setPerformanceFeeSaPhan(rs.getString("performanceFeeSaPhan"));
            data.setPerformanceFeeYoktu(rs.getString("performanceFeeYoktu"));
            data.setPerformanceFeeOutContainer(rs.getString("performanceFeeOutContainer"));
            data.setFeeUnit(rs.getString("feeUnit"));
            data.setFeeTotal(rs.getString("feeTotal"));
        }catch (Exception e){
            e.printStackTrace();
            return  data;
        }
        return  data;
    }
}
