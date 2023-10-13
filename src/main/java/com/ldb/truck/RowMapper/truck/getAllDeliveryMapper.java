package com.ldb.truck.RowMapper.truck;

import com.ldb.truck.Model.Login.delivery.DeliveryOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAllDeliveryMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        DeliveryOut data = new DeliveryOut();

        try {

            data.setId(rs.getString("KEY_ID"));
            data.setIdDelivery(rs.getString("ID_DELIVERY"));
            data.setDateTime(rs.getString("DATE_TIME"));
            data.setTruckId(rs.getString("TRUCK_ID"));
            data.setPlatNo(rs.getString("PLATENO"));
            data.setStaftId(rs.getString("STAFT_ID"));
            data.setStaftName(rs.getString("STAFT_NAME"));
            data.setStaftMobile(rs.getString("STAFT_MOBILE"));
            data.setCustomerId(rs.getString("CUSTOMER_ID"));
            data.setCustomerName(rs.getString("CUSTOMER_NAME"));
            data.setAddressProvinceStart(rs.getString("ADDRESS_START"));
            data.setAdddressProvinceEnd(rs.getString("ADDRESS_END"));
            data.setDistance(rs.getString("DISTANCE"));
            data.setProductId(rs.getString("PRODUCT_ID"));
            data.setProductName(rs.getString("PRODUCT_NAME"));
            data.setPayToatalAmount(rs.getString("AMOUNT"));
            data.setWeight(rs.getString("WEIGHT"));
            data.setTotalGlass(rs.getString("GLASS_AMOUNT"));
            data.setAddressStartDate(rs.getString("ADDRESS_START_DATE"));
            data.setAddressEndDate(rs.getString("ADDRESS_END_DATE"));
            data.setAddressStartDetail(rs.getString("ADDRESS_START_DETAIL"));
            data.setAddressEndDetail(rs.getString("ADDRESS_END_DETAIL"));
            data.setPayAmount(rs.getString("PAY"));
            data.setPriceGlass(rs.getString("GLASS_PRICE"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
