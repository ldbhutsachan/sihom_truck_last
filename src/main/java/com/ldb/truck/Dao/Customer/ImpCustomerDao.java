package com.ldb.truck.Dao.Customer;

import com.ldb.truck.Entity.ItemPayment.ItemDetailsEntity;
import com.ldb.truck.Entity.ItemPayment.ItemPaymentViewEntity;
import com.ldb.truck.Entity.ItemPayment.PaymentModel;
import com.ldb.truck.Model.Login.FuelStation.FuelStationOut;
import com.ldb.truck.Model.Login.FuelStation.FuelStationReq;
import com.ldb.truck.Model.Login.ReportStaff.AmountThatPaidStaffModel;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffRanking;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.product.ProductOut;
import com.ldb.truck.Model.Login.product.ProductReq;
import com.ldb.truck.Model.Login.staft.*;
import com.ldb.truck.RowMapper.FuelStation.getAllFuelStationMapper;
import com.ldb.truck.RowMapper.Location.LocationOutMapper;
import com.ldb.truck.RowMapper.Product.ProductMapper;
import com.ldb.truck.RowMapper.customer.getAllcustomerMapper;
import com.ldb.truck.RowMapper.staftMapper.getAllStaftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class ImpCustomerDao  implements CustomerDao{

    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    @Override
    public List<StaffDetails> ListDetailStaff(StaffPayReq staffPayReq) {
        try{

            String SQL="SELECT * from V_CHECK_REPROT_STAFF where STAFT_ID='"+staffPayReq.getStaffID()+"'";

            System.out.println("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<StaffDetails>() {
                @Override
                public StaffDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                    StaffDetails tr = new StaffDetails();
                    tr.setStaffID(rs.getString("STAFT_ID"));
                    tr.setStaffName(rs.getString("STAFT_NAME"));
                    tr.setStaffSurname(rs.getString("STAFT_SURNAME"));
                    tr.setIdCard(rs.getString("ID_CARD"));
                    tr.setIdCardExpried(rs.getString("LICENCE_ID_EXP"));
                    tr.setLicenId(rs.getString("LICENCE_ID"));

                    tr.setKeyId(rs.getString("LAHUD_POYLOD"));
                    tr.setHeaderNo(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setFooterNo(rs.getString("F_CARD_NO"));
                    tr.setCusName(rs.getString("CUSTOMER_NAME"));
                    tr.setProNo(rs.getString("PRO_ID"));
                    tr.setProName(rs.getString("PRO_NAME"));
                    tr.setProvinceName(rs.getString("PROVINCE"));
                    tr.setPlaceName(rs.getString("DETAIL"));
                    tr.setStartDate(rs.getString("OUT_DATE"));
                    tr.setEndDate(rs.getString("IN_DATE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StaffPay> ListStaffPay(StaffPayReq staffPayReq) {
        try{
            String SQL="";
            if(staffPayReq.getStartDate() == null || staffPayReq.getEndDate() == null){
                 SQL="SELECT\n" +
                         "                        a.ID_CARD,a.LICENCE_ID,a.VILLAGE,a.DISTRICT,a.PROVINCE, \n" +
                         "                        a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,COUNT(*) AS TOTALROW,  \n" +
                         "                         cast(replace(a.STAFF_BIALIENG, ',', '') as unsigned)+ cast(replace(a.staff02_payAll, ',', '') as unsigned) AS staff02_payAll, \n" +
                         "                         cast(replace(a.STAFF_BIALIENG_FRIST, ',', '') as unsigned)+ cast(replace(a.staff02_beforepay, ',', '') as unsigned) AS staff02_beforepay, \n" +
                         "                         cast(replace(a.STAFF_BIALINEG_KANGJAIY, ',', '') as unsigned)+ cast(replace(a.staff02_notpay, ',', '') as unsigned) AS staff02_notpay \n" +
                         "                        FROM CEHCK_PAY_STATFF a INNER JOIN LOGIN b ON a.userId =b.KEY_ID WHERE b.BRANCH ='"+staffPayReq.getBranch()+"' GROUP BY a.STAFT_ID,STAFT_NAME,a.STAFT_SURNAME ORDER BY a.OUT_DATE  ASC";
            }else {
                 SQL = "SELECT\n" +
                         "                        a.ID_CARD,a.LICENCE_ID,a.VILLAGE,a.DISTRICT,a.PROVINCE, \n" +
                         "                        a.STAFT_ID,a.STAFT_NAME,a.STAFT_SURNAME,COUNT(*) AS TOTALROW,  \n" +
                         "                         cast(replace(a.STAFF_BIALIENG, ',', '') as unsigned)+ cast(replace(a.staff02_payAll, ',', '') as unsigned) AS staff02_payAll, \n" +
                         "                         cast(replace(a.STAFF_BIALIENG_FRIST, ',', '') as unsigned)+ cast(replace(a.staff02_beforepay, ',', '') as unsigned) AS staff02_beforepay, \n" +
                         "                         cast(replace(a.STAFF_BIALINEG_KANGJAIY, ',', '') as unsigned)+ cast(replace(a.staff02_notpay, ',', '') as unsigned) AS staff02_notpay \n" +
                         "                        FROM CEHCK_PAY_STATFF a INNER JOIN LOGIN b ON a.userId =b.KEY_ID WHERE b.BRANCH ='"+staffPayReq.getBranch()+"' AND OUT_DATE between '" +staffPayReq.getStartDate()+ "' and '" +staffPayReq.getEndDate()+"' GROUP BY a.STAFT_ID,STAFT_NAME,a.STAFT_SURNAME ORDER BY a.OUT_DATE  ASC";
            }
            System.out.println("SQL:"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<StaffPay>() {
                @Override
                public StaffPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                    StaffPay tr = new StaffPay();
                    tr.setStaffID(rs.getString("STAFT_ID"));
                    tr.setStaffName(rs.getString("STAFT_NAME"));
                    tr.setStaffSurname(rs.getString("STAFT_SURNAME"));
                    tr.setStaff02_PayAll(rs.getString("staff02_payAll"));
                    tr.setStaff02_Beforepay(rs.getString("staff02_beforepay"));
                    tr.setStaff02_Notpay(rs.getString("staff02_notpay"));
                    tr.setTotalRow(rs.getString("TOTALROW"));
                    tr.setIdCard(rs.getString("ID_CARD"));
                    tr.setLicenId(rs.getString("LICENCE_ID"));
                    tr.setVill(rs.getString("VILLAGE"));
                    tr.setDis(rs.getString("DISTRICT"));
                    tr.setPro(rs.getString("PROVINCE"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportStaff> ListStaffPaydetailsByStaffId(StaffPayReq staffPayReq) {
        try
        {
            String sql="select * from CEHCK_PAY_STATFF where  STAFT_ID='"+staffPayReq.getStaffID()+"' OR  LAHUD_POYLOD ='"+staffPayReq.getLahudPoyLod()+"'  order by OUT_DATE asc";
            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
                @Override
                public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaff tr =new ReportStaff();
                    tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                    tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                    tr.setSTAFT_ID02(rs.getString("STAFT_ID02"));
                    tr.setSTAFT_NAME02(rs.getString("STAFT_NAME02"));
                    tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setF_BRANCH (rs.getString("F_BRANCH"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setPROVINCE(rs.getString("PROVINCE"));
                    tr.setDETAIL(rs.getString("DETAIL"));
                    tr.setOUT_DATE(rs.getString("OUT_DATE"));
                    tr.setIN_DATE(rs.getString("IN_DATE"));
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
                    tr.setStaffPaystatus01(rs.getString("staff_01_status"));
                    tr.setStaffPaystatus02(rs.getString("staff_02_status"));
                    tr.setLahudPoyLod(rs.getString("LAHUD_POYLOD"));
                    tr.setTotalDay(rs.getString("totalDay"));
//                    tr.setBatStartDate(rs.getString(""));
//                    tr.setBatEndDate(rs.getString(""));

                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportStaff> ListWaiyPaymentStaff(StaffPaymentReq staffPaymentReq) {
        String sql = null;
        try
        {
            switch (staffPaymentReq.getStartDate() == null ? "null" : "not_null") {
                case "null":
                    // Check if product ID is not null
                    if (staffPaymentReq.getProductID() != null) {
                        sql = "select * from CEHCK_PAY_STATFF a join LOGIN b ON a.userId =b.KEY_ID  where b.BRANCH ='" + staffPaymentReq.getBranch() + "' AND PRODUCT_ID ='" + staffPaymentReq.getProductID() + "' AND (a.staff_01_status='not-pay')";
                        System.out.println("SQL4:" + sql);
                    } else {
                        // Existing logic from original case (no dates and no product ID)
                        sql = "select * from CEHCK_PAY_STATFF a join LOGIN b ON a.userId =b.KEY_ID  where b.BRANCH ='" + staffPaymentReq.getBranch() + "' AND (a.staff_01_status='not-pay')";
                        System.out.println("SQL1:" + sql);
                    }
                    break;
                case "not_null":
                    // Existing logic from original case (dates provided)
                    if (staffPaymentReq.getProductID() != null) {
                        sql = "select * from CEHCK_PAY_STATFF a join LOGIN b ON a.userId =b.KEY_ID  where b.BRANCH ='" + staffPaymentReq.getBranch() + "' AND OUT_DATE BETWEEN '" + staffPaymentReq.getStartDate() + "' AND '" + staffPaymentReq.getEndDate() + "' AND PRODUCT_ID ='"+ staffPaymentReq.getProductID() +"' AND (a.staff_01_status='not-pay')";
                    } else {
                        sql = "select * from CEHCK_PAY_STATFF a join LOGIN b ON a.userId =b.KEY_ID  where b.BRANCH ='" + staffPaymentReq.getBranch() + "' AND a.OUT_DATE BETWEEN '" + staffPaymentReq.getStartDate() + "' AND '" + staffPaymentReq.getEndDate() + "' AND (a.staff_01_status='not-pay')";
                    }
                    System.out.println("SQL2 or SQL3:" + sql);
                    break;
            }



            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
                @Override
                public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaff tr =new ReportStaff();
                    tr.setKeyIds(rs.getString("KEY_ID"));
                    tr.setDels(rs.getString("LAHUD_POYLOD"));
                    tr.setLahudPoyLod(rs.getString("LAHUD_POYLOD"));
                    tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                    tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                    tr.setSTAFT_ID02(rs.getString("STAFT_ID02"));
                    tr.setSTAFT_NAME02(rs.getString("STAFT_NAME02"));
                    tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                    tr.setSTAFT_SURNAME02(rs.getString("STAFT_SURNAME02"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setF_BRANCH (rs.getString("F_BRANCH"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setPROVINCE(rs.getString("PROVINCE"));
                    tr.setDETAIL(rs.getString("DETAIL"));
                    tr.setOUT_DATE(rs.getString("OUT_DATE"));
                    tr.setIN_DATE(rs.getString("IN_DATE"));
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
                    tr.setStaffPaystatus01(rs.getString("staff_01_status"));
                    tr.setStaffPaystatus02(rs.getString("staff_02_status"));

                    tr.setTotalDay(rs.getString("totalDay"));
                    tr.setStaff02_payAll(rs.getString("staff02_payAll"));
                    tr.setStaff02_beforepay(rs.getString("staff02_beforepay"));
                    tr.setStaff02_notpay(rs.getString("staff02_notpay"));
                    tr.setProductID(rs.getString("PRODUCT_ID"));
                    tr.setProductName(rs.getString("PRO_NAME"));

                    String numtotalStaffbialieng01 = rs.getString("STAFF_BIALINEG_KANGJAIY").replaceAll(",","");
                    double conVertStaffbialieng01  = Double.parseDouble(numtotalStaffbialieng01);
                    tr.setTotalBialiengKangjaiy(conVertStaffbialieng01);
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    amount that paid staff bialieng
@Override
public List<AmountThatPaidStaffModel>AmountThatPaidStaffDAOs (StaffPaymentReq staffPaymentReq) {
    try
    {
        String sql ="select a.ALL_OF_THEM from PAYMENT_STAFF a join LOGIN b on a.userId = b.KEY_ID where b.BRANCH = '"+staffPaymentReq.getBranch()+"' and a.PAY_DATE between '"+staffPaymentReq.getStartDate()+"' and '"+staffPaymentReq.getEndDate()+"'";
        return  EBankJdbcTemplate.query(sql, new RowMapper<AmountThatPaidStaffModel>() {
            @Override
            public AmountThatPaidStaffModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                AmountThatPaidStaffModel tr =new AmountThatPaidStaffModel();
                tr.setAmoutTotalpaid(rs.getDouble("ALL_OF_THEM"));
                return tr;
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }
    return null;
}
    //============Qeury top 5 ranking staff
    public List<ReportStaffRanking> TopFiveRankingDao(StaffPaymentReq staffPaymentReq) {
        String sql = null;
        try
        {
            if (staffPaymentReq.getStartDate()==null){
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "  SELECT COUNT(a.LAHUD_POYLOD) AS allTiew, a.STAFT_NAME,STAFT_SURNAME\n" +
                        "  FROM CEHCK_PAY_STATFF a\n" +
                        "  JOIN LOGIN b ON a.userId = b.KEY_ID\n" +
                        "  WHERE b.BRANCH = '"+staffPaymentReq.getBranch()+"'\n" +
                        "  AND (a.staff_01_status='not-pay') \n" +
                        "  GROUP BY a.STAFT_ID\n" +
                        "  ORDER BY allTiew DESC\n" +
                        ") AS ranked_data\n" ;

            } else{
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "  SELECT COUNT(a.LAHUD_POYLOD) AS allTiew, a.STAFT_NAME,STAFT_SURNAME\n" +
                        "  FROM CEHCK_PAY_STATFF a\n" +
                        "  JOIN LOGIN b ON a.userId = b.KEY_ID\n" +
                        "  WHERE b.BRANCH = '"+staffPaymentReq.getBranch()+"'\n" +
                        "  AND (a.staff_01_status='not-pay') AND a.OUT_DATE BETWEEN '"+staffPaymentReq.getStartDate()+"' and '"+staffPaymentReq.getEndDate()+"'\n" +
                        "  GROUP BY a.STAFT_ID\n" +
                        "  ORDER BY allTiew DESC\n" +
                        ") AS ranked_data\n";
            }

            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaffRanking>() {
                @Override
                public ReportStaffRanking mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaffRanking tr =new ReportStaffRanking();
                    tr.setAllTiew(rs.getString("allTiew"));
                    tr.setStaffName(rs.getString("STAFT_NAME"));
                    tr.setStaffSurname(rs.getString("STAFT_SURNAME"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //============Qeury top 5 ranking staff
    @Override
    public List<ReportStaff> ReportStaffPeymnet(ResFromDateReq resFromDateReq) {
        try
        {
            String sql="select * from CEHCK_PAY_STATFF where OUT_DATE='"+resFromDateReq.getStartDate()+"' and OUT_DATE='"+resFromDateReq.getStartDate()+"'  order by OUT_DATE asc";
            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
                @Override
                public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaff tr =new ReportStaff();
                    tr.setSTAFT_ID(rs.getString("STAFT_ID"));
                    tr.setSTAFT_NAME(rs.getString("STAFT_NAME"));
                    tr.setSTAFT_ID02(rs.getString("STAFT_ID02"));
                    tr.setSTAFT_NAME02(rs.getString("STAFT_NAME02"));
                    tr.setSTAFT_SURNAME(rs.getString("STAFT_SURNAME"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setF_BRANCH (rs.getString("F_BRANCH"));
                    tr.setF_CAR_TYPE(rs.getString("F_CAR_TYPE"));
                    tr.setPROVINCE(rs.getString("PROVINCE"));
                    tr.setDETAIL(rs.getString("DETAIL"));
                    tr.setOUT_DATE(rs.getString("OUT_DATE"));
                    tr.setIN_DATE(rs.getString("IN_DATE"));
                    tr.setSTAFF_BIALIENG(rs.getString("STAFF_BIALIENG"));
                    tr.setSTAFF_BIALIENG_FRIST(rs.getString("STAFF_BIALIENG_FRIST"));
                    tr.setSTAFF_BIALINEG_KANGJAIY(rs.getString("STAFF_BIALINEG_KANGJAIY"));
                    tr.setCurrency(rs.getString("CURRENCY"));
                    tr.setStaff_Cur(rs.getString("STAFF_BIALIENG_CUR"));
                    tr.setStaffPaystatus01(rs.getString("staff_01_status"));
                    tr.setStaffPaystatus02(rs.getString("staff_02_status"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    @Override
//    public int paymentStaff(StaffPaymentReq staffPaymentReq) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = sdf.parse(staffPaymentReq.getPayDate());
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        try{
//            String SQL="insert into PAYMENT_STAFF (PAY_DATE,staffID01,staffID02,payAmount_NotPay01,payAmount_NotPay02,payAmount_Pay01,payAmount_Pay02," +
//                    "payAmount_TotalPay01,payAmount_TotalPay02,payAmount_status01,payAmount_status02,userId) values (?,?,?,?,?,?,?,?,?,?,?,?)";
//            List<Object> paramList = new ArrayList<Object>();
//            paramList.add(sqlDate);
//            paramList.add(staffPaymentReq.getStaffID01());
//            paramList.add(staffPaymentReq.getStaffID02());
//            paramList.add(staffPaymentReq.getPayAmount_NotPay01());
//            paramList.add(staffPaymentReq.getPayAmount_NotPay02());
//            paramList.add(staffPaymentReq.getPayAmount_Pay01());
//            paramList.add(staffPaymentReq.getPayAmount_Pay02());
//            paramList.add(staffPaymentReq.getPayAmount_TotalPay01());
//            paramList.add(staffPaymentReq.getPayAmount_TotalPay02());
//            paramList.add(staffPaymentReq.getPayAmount_status01());
//            paramList.add(staffPaymentReq.getPayAmount_status02());
//            paramList.add(staffPaymentReq.getUserId());
//            return EBankJdbcTemplate.update(SQL,paramList.toArray());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return 0;
//    }
    ///====================================new
@Override
public int paymentStaff(StaffPaymentReq staffPaymentReq) {
    // Initialize a placeholder for sqlDate
    java.sql.Date sqlDate = null;

    try {
        // Ensure payDate is not null before parsing
        if (staffPaymentReq.getPayDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(staffPaymentReq.getPayDate());
            sqlDate = new java.sql.Date(date.getTime());
        } else {
            // Handle the case where payDate is null
            System.out.println("WARNING: payDate is null. Using current date for PAY_DATE.");
            sqlDate = new java.sql.Date(System.currentTimeMillis()); // Use current date
        }

        String SQL = "insert into PAYMENT_STAFF (PAY_DATE, ALL_OF_THEM, userId) values (?, ?, ?)";
        List<Object> paramList = new ArrayList<>();
        paramList.add(sqlDate);
        paramList.add(staffPaymentReq.getAllofthem());
        paramList.add(staffPaymentReq.getUserId());

        System.out.println("SQL:" + SQL);
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    } catch (ParseException e) {
        System.err.println("Error parsing payDate: " + e.getMessage());
        e.printStackTrace();
        return 0;
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
        e.printStackTrace();
        return 0;
    }
}

    //git alll
    @Override
    public int paymentStaffUpdate(StaffPaymentReq staffPaymentReq) throws ParseException {
//        System.out.println("KEY DETAILS 01:"+staffPaymentReq.getKey_Id());
//        System.out.println("staffPaymentReq 01:"+staffPaymentReq.getStaffID01());
//        System.out.println("staffPaymentReq 02:"+staffPaymentReq.getStaffID02());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(staffPaymentReq.getPayDate());
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        try{
//            String SQL="update TB_DETAILS set staff_01_status='"+staffPaymentReq.getPayAmount_status01()+"',staff_02_status='"+staffPaymentReq.getPayAmount_status02()+"' where LAHUD_POYLOD='"+staffPaymentReq.getKey_Id()+"'";
//            System.out.println("SQL:"+SQL);
//            List<Object> paramList = new ArrayList<Object>();
//            paramList.add(sqlDate);
//            paramList.add(staffPaymentReq.getStaffID01());
//            paramList.add(staffPaymentReq.getStaffID02());
//            paramList.add(staffPaymentReq.getPayAmount_NotPay01());
//            paramList.add(staffPaymentReq.getPayAmount_NotPay02());
//            paramList.add(staffPaymentReq.getPayAmount_Pay01());
//            paramList.add(staffPaymentReq.getPayAmount_Pay02());
//            paramList.add(staffPaymentReq.getPayAmount_TotalPay01());
//            paramList.add(staffPaymentReq.getPayAmount_TotalPay02());
//            paramList.add(staffPaymentReq.getPayAmount_status01());
//            paramList.add(staffPaymentReq.getPayAmount_status02());
//            return EBankJdbcTemplate.update(SQL,paramList.toArray());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return 0;

        // ======================================================= ข้างเทิงโตเก่า
        int totalUpdated_2 = 0;

        List<String> dels = staffPaymentReq.getDels();
        List<String> keyIds = staffPaymentReq.getKeyIds();

        if (dels.size() != keyIds.size()) {
            // Handle potential size mismatch between dels and keyIds
            throw new IllegalArgumentException("Number of dels and keyIds must be equal");
        }

        try {
            for (int i = 0; i < dels.size(); i++) {
                String del = dels.get(i);
                String keyId = keyIds.get(i);

                String SQL = "UPDATE TB_DETAILS SET staff_01_status ='done',STAFF_BIALINEG_KANGJAIY='0',STAFF_BIALIENG=STAFF_BIALIENG_FRIST  WHERE LAHUD_POYLOD = '" + del + "' AND KEY_ID='" + keyId + "'";
                System.out.println(SQL);
                totalUpdated_2 += EBankJdbcTemplate.update(SQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return totalUpdated_2;
        }

        return totalUpdated_2;
    }
    @Override
    public List<CustomerOut> getAllCustomer(CustomerReq custoerReq) {
        List<CustomerOut> result = new ArrayList<>();
        try {

//            String SQL = "SELECT KEY_ID , CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL  FROM CUSTOMER  WHERE STATUS='A' ";
            String SQL = "SELECT *  FROM CUSTOMER c INNER JOIN LOGIN l ON c.userId =l.KEY_ID WHERE l.BRANCH  ='"+custoerReq.getBranch()+"' and c.STATUS ='A' ";
            System.out.println(SQL);

            result = EBankJdbcTemplate.query(SQL , new getAllcustomerMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //===================start-fuel-station-dao===============================================
    public List<FuelStationOut> getAllFuelStation(FuelStationReq fuelStationReq) {
        List<FuelStationOut> result = new ArrayList<>();
        try {
            String SQL = "SELECT *  FROM FUEL_STATION a INNER JOIN LOGIN b ON a.userId =b.KEY_ID WHERE b.BRANCH  ='"+fuelStationReq.getBranch()+"' ";
            System.out.println(SQL);

            result = EBankJdbcTemplate.query(SQL , new getAllFuelStationMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //===================start-fuel-station-dao===============================================
    @Override
    public List<CustomerOut> getCustomerById(CustomerReq custoerReq) {
        List<CustomerOut> result = new ArrayList<>();
        try {
//            String SQL = "SELECT KEY_ID , CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL  FROM CUSTOMER  WHERE STATUS='A'  AND KEY_ID = '"+id+"' ";
            String SQL = "SELECT *  FROM CUSTOMER c INNER JOIN LOGIN l ON c.userId =l.KEY_ID WHERE l.BRANCH  ='"+custoerReq.getBranch()+"' and c.STATUS ='A' AND KEY_ID = '"+custoerReq.getId()+"'";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new getAllcustomerMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public int StoreCustomer(CustomerReq custoerReq) {
        int i = 0;
        String custId = custoerReq.getCustomerId();
        String custName = custoerReq.getCustomerName();
        String addrss =  custoerReq.getAddress();
        String village = custoerReq.getVilage();
        String district = custoerReq.getDistrict();
        String provicnce = custoerReq.getProvince();
        String mobile = custoerReq.getMobile();
        String mobile1 = custoerReq.getMobile1();
        String email = custoerReq.getEmail();
        try {
            String SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL , STATUS,userId)\n" +
                    "VALUES ( '"+custId+"' , '"+custName+"' , '"+addrss+"' , '"+village+"' , '"+district+"' , '"+provicnce+"' , '"+mobile+"' , '"+mobile1+"' , '"+email+"' , 'A','"+custoerReq.getUserId()+"') ";
            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    // ===================store fuel tation =========================================================
    public int StoreFuelStation(FuelStationReq fuelStationReq) {
        int i = 0;
        String fuelId = fuelStationReq.getFuelStationId();
        String fuelName = fuelStationReq.getFuelStationName();
        String addrss =  fuelStationReq.getAddress();
        String village = fuelStationReq.getVillage();
        String district = fuelStationReq.getDistrict();
        String provicnce = fuelStationReq.getProvince();
        String mobile = fuelStationReq.getMobile();
        String email = fuelStationReq.getEmail();
        String userId = fuelStationReq.getUserId();
        try {
//            String SQL = "INSERT INTO FUEL_STATION (FUEL_STATION_ID,FUEL_STATION_NAME,ADDRESS,VILLAGE,DISTRICT,PROVICNE,MOBILE,EMAIL,userId) VALUES(?,?,?,?,?,?,?,?,'"+fuelStationReq.getUserId()+"') ";
            String SQL = "INSERT INTO FUEL_STATION (FUEL_STATION_ID,FUEL_STATION_NAME,ADDRESS,VILLAGE,DISTRICT,PROVICNE,MOBILE,EMAIL,userId) " +
                    "VALUES ('"+fuelId+"','"+fuelName+"','"+addrss+"','"+village+"','"+district+"','"+provicnce+"', '"+mobile+"','"+email+"','"+userId+"')";

            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    // ===================store fuel tation =========================================================
    public int UpdateStatusFuelStation(FuelStationReq fuelStationReq) {
        int i = 0;

        String key_id = fuelStationReq.getKey_id();
        String del = fuelStationReq.getDel();
        try {
            String SQL = "UPDATE  TB_DETAILS SET FUEL_STATUS ='P'  WHERE LAHUD_POYLOD = '"+del+"' and KEY_ID='"+key_id+"'" ;

            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    //update multi
    public int UpdateStatusFuelStationMulti(FuelStationReq fuelStationReq) {
        int totalUpdated = 0;

        List<String> dels = fuelStationReq.getDels(); // Assuming dels is a list of strings
        List<String> keyIds = fuelStationReq.getKeyIds(); // Assuming keyIds is a list of strings

        if (dels.size() != keyIds.size()) {
            // Handle potential size mismatch between dels and keyIds
            throw new IllegalArgumentException("Number of dels and keyIds must be equal");
        }

        try {
            for (int i = 0; i < dels.size(); i++) {
                String del = dels.get(i);
                String keyId = keyIds.get(i);

                String SQL = "UPDATE TB_DETAILS SET FUEL_STATUS ='P'  WHERE LAHUD_POYLOD = '" + del + "' AND KEY_ID='" + keyId + "'";
                System.out.println(SQL);
                totalUpdated += EBankJdbcTemplate.update(SQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return totalUpdated; // Return the number of rows updated so far in case of error
        }

        return totalUpdated;
    }
//    insert totoalprice to table
public int insertTotalprice (FuelStationReq fuelStationReq) {
    try{
        String SQL = "insert into SPEND_OILS (TOTAL_PRICE,DATECREATE,userId)values (?,?,'"+fuelStationReq.getUserId()+"')";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(fuelStationReq.getTotalPriceOil());
        paramList.add(fuelStationReq.getDatecreate());
        paramList.add(fuelStationReq.getUserId());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}

    // ===================Update fuel tation =========================================================

    public int UpdateFuelStation(FuelStationReq fuelStationReq) {
        int i = 0;
        int id = fuelStationReq.getId();
        String fuelId = fuelStationReq.getFuelStationId();
        String fuelName = fuelStationReq.getFuelStationName();
        String addrss =  fuelStationReq.getAddress();
        String village = fuelStationReq.getVillage();
        String district = fuelStationReq.getDistrict();
        String provicnce = fuelStationReq.getProvince();
        String mobile = fuelStationReq.getMobile();
        String email = fuelStationReq.getEmail();
        String userId = fuelStationReq.getUserId();
        try {

            String SQL = " UPDATE  FUEL_STATION SET    FUEL_STATION_ID ='"+fuelId+"'  , FUEL_STATION_NAME  = '"+fuelName+"' , ADDRESS = '"+addrss+"' , VILLAGE ='"+village+"' , DISTRICT ='"+district+"' , PROVICNE ='"+provicnce+"', \n" +
                    " MOBILE = '"+mobile+"', EMAIL ='"+email+"',userId='"+userId+"'  WHERE KEY_ID = '"+id+"' " ;
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return  i;
        }

        return i;
    }
    // ===================Update fuel tation =========================================================

    @Override
    public int UpdateCustomer(CustomerReq custoerReq) {

        int i = 0;
        int id = custoerReq.getId();
        String custId = custoerReq.getCustomerId();
        String custName = custoerReq.getCustomerName();
        String addrss =  custoerReq.getAddress();
        String village = custoerReq.getVilage();
        String district = custoerReq.getDistrict();
        String provicnce = custoerReq.getProvince();
        String mobile = custoerReq.getMobile();
        String mobile1 = custoerReq.getMobile1();
        String email = custoerReq.getEmail();
        String userId = custoerReq.getUserId();


        try {

            String SQL = " UPDATE  CUSTOMER SET    CUSTOMER_ID ='"+custId+"'  , CUSTOMER_NAME  = '"+custName+"' , ADDRESS = '"+addrss+"' , VILLAGE ='"+village+"' , DISTRICT ='"+district+"' , PROVICNE ='"+provicnce+"', \n" +
                    " MOBILE1 = '"+mobile+"', MOBILE2  ='"+mobile1+"', EMAIL ='"+email+"',userId='"+userId+"'  WHERE KEY_ID = '"+id+"' " ;
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return  i;
        }

        return i;
    }

    @Override
    public int deleteCustomer(String id) {

        int i = 0;

        try {

            String SQL = "DELETE FROM CUSTOMER WHERE KEY_ID = '"+id+"' ";

            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    //delete fuel station=================================================

    public int deleteFuelStation(FuelStationReq fuelStationReq) {

        int i = 0;

        try {

            String SQL = "DELETE FROM FUEL_STATION WHERE KEY_ID = '"+fuelStationReq.getId()+"' ";

            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    //delete fuel station=================================================


    @Override
    public List<staftOut> getAllStaft() {
        List<staftOut>  result = new ArrayList<>();
        try {
              String SQL = "SELECT  KEY_ID , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , " +
              "LICENCE_ID_EXP , VILLAGE , DISTRICT , PROVINCE , MOBILE1 , MOBILE2, GENDER , GENDER_STATUS , GENDER_STATUS ,DATE_INSERT, USERID,IMAGE_STAFF   FROM STAFF WHERE STATUS = 'A' ";
            System.out.println("show SQL:"+SQL);
       result = EBankJdbcTemplate.query(SQL , new getAllStaftMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    //-----choose staff 01
    @Override
    public List<staftOut> getChooseStaft01(stafReq stafReq) {
        List<staftOut>  result = new ArrayList<>();
        try {
//            String SQL = "SELECT  KEY_ID , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , " +
//                    "LICENCE_ID_EXP , VILLAGE , DISTRICT , PROVINCE , MOBILE1 , MOBILE2, GENDER , GENDER_STATUS , GENDER_STATUS ,DATE_INSERT, USERID,IMAGE_STAFF   FROM STAFF WHERE OUT_STATUS = 'N'  and STATUS='A'";
            String SQL = "SELECT * FROM STAFF s INNER JOIN LOGIN l ON s.saveById=l.KEY_ID WHERE s.STATUS='A' and BRANCH='"+stafReq.getBranch()+"'";
//            เอาออก 5/11/2024  s.OUT_STATUS = 'N'  and
            System.out.println("show SQL:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new getAllStaftMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<staftOut> getChooseStaft02(stafReq stafReq) {
        List<staftOut>  result = new ArrayList<>();
        try {
//            String SQL = "\n" +
//                    "select \n" +
//                    "'116' as KEY_ID , '0' as STAFT_ID ,'ບໍ່ເລືອກຄົນຂັບທີ 2' as  STAFT_NAME , '' as STAFT_SURNAME ,null as  ID_CARD ,null as  LICENCE_ID ,null as  VERIFY_BY ,\n" +
//                    "null as LICENCE_ID_EXP , null as VILLAGE ,null as  DISTRICT ,null as  PROVINCE ,null as  MOBILE1 ,null as  MOBILE2,null as  GENDER ,\n" +
//                    "null as  GENDER_STATUS , \n" +
//                    "null as GENDER_STATUS ,null as  DATE_INSERT, null as  USERID,null as  IMAGE_STAFF \n" +
//                    "union \n" +
//                    "SELECT \n" +
//                    "KEY_ID , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY ,\n" +
//                    "LICENCE_ID_EXP , VILLAGE , DISTRICT , PROVINCE , MOBILE1 , MOBILE2, GENDER , GENDER_STATUS , \n" +
//                    "GENDER_STATUS ,DATE_INSERT, USERID,IMAGE_STAFF \n" +
//                    "FROM STAFF WHERE OUT_STATUS = 'N'  and STATUS='A'";
            String SQL = "select '116' as KEY_ID , '0' as STAFT_ID ,'ບໍ່ເລືອກຄົນຂັບທີ 2' as  STAFT_NAME , '' as STAFT_SURNAME ,null as  ID_CARD ,null as  LICENCE_ID ,null as  VERIFY_BY , \n" +
                    "                    null as LICENCE_ID_EXP , null as VILLAGE ,null as  DISTRICT ,null as  PROVINCE ,null as  MOBILE1 ,null as  MOBILE2,null as  GENDER , \n" +
                    "                    null as  GENDER_STATUS ,  \n" +
                    "                    null as GENDER_STATUS ,null as  DATE_INSERT, null as  USERID,null as  IMAGE_STAFF  \n" +
                    "                    union  \n" +
                    "                    SELECT  \n" +
                    "                    a.KEY_ID , a.STAFT_ID , a.STAFT_NAME , a.STAFT_SURNAME , a.ID_CARD , a.LICENCE_ID , a.VERIFY_BY , \n" +
                    "                    a.LICENCE_ID_EXP , a.VILLAGE , a.DISTRICT , a.PROVINCE , a.MOBILE1 , a.MOBILE2, a.GENDER , a.GENDER_STATUS ,  \n" +
                    "                    a.GENDER_STATUS ,a.DATE_INSERT, a.USERID,a.IMAGE_STAFF  \n" +
                    "                    FROM STAFF a inner join LOGIN b ON a.saveById=b.KEY_ID WHERE a.OUT_STATUS = 'N'  and a.STATUS='A' and b.BRANCH='"+stafReq.getBranch()+"'";
            System.out.println("show SQL:"+SQL);
            result = EBankJdbcTemplate.query(SQL , new getAllStaftMapper());
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<staftOut> getStaftById(String id) {
        List<staftOut>  result = new ArrayList<>();
        try {
            String SQL = "SELECT  KEY_ID , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , " +
                    "LICENCE_ID_EXP , VILLAGE , DISTRICT , PROVINCE , MOBILE1 , MOBILE2, GENDER , GENDER_STATUS , GENDER_STATUS ,DATE_INSERT, USERID,IMAGE_STAFF   FROM STAFF WHERE STATUS = 'A' AND KEY_ID = '"+id+"' ";
            System.out.println(SQL);
            result = EBankJdbcTemplate.query(SQL , new getAllStaftMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public int StoreStaft(stafReq stafReq) {
        int i = 0 ;

        String staftId = stafReq.getStaftId();
        String name = stafReq.getName();
        String surname = stafReq.getSurname();
        String idCard = stafReq.getIdCard();
        String licenceId = stafReq.getLicenceId();
        String verBy = stafReq.getVerBy();
        String licenceExp = stafReq.getLicenceExp();
        String vaillage = stafReq.getVaillage();
        String district = stafReq.getDistrict();
        String province = stafReq.getProvince();
        String mobile = stafReq.getMobile();
        String mobile1 = stafReq.getMobile1();
        String gender = stafReq.getGender();
        String genderStatus = stafReq.getGenderStatus();
        String userId = stafReq.getUserId();
        String SaveById = stafReq.getSaveById();
        String path="http://khounkham.com/images/staff/";
        String imageStaff = path+stafReq.getImageStaff();
        try {
            String SQL  = " INSERT INTO STAFF ( OUT_STATUS,STATUS , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , LICENCE_ID_EXP , VILLAGE , DISTRICT ,\n" +
                    "PROVINCE , MOBILE1 , MOBILE2 ,GENDER , GENDER_STATUS ,  USERID,IMAGE_STAFF ,SaveById) VALUES ( 'N','A' ,'"+staftId+"' , '"+name+"' , '"+surname+"' , '"+idCard+"' ,\n " +
                    " '"+licenceId+"' , '"+verBy+"' , '"+licenceExp+"' , '"+vaillage+"' , '"+district+"'  ,'"+province+"' , '"+mobile+"' , '"+mobile1+"' , '"+gender+"' , '"+genderStatus+"' ,'"+userId+"','"+imageStaff+"','"+SaveById+"') ";
           System.out.println("SQL:"+SQL);
            i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public int UpdateStaft(stafReq stafReq) {
        int i = 0 ;
        String id = stafReq.getId();
        String staftId = stafReq.getStaftId();
        String name = stafReq.getName();
        String surname = stafReq.getSurname();
        String idCard = stafReq.getIdCard();
        String licenceId = stafReq.getLicenceId();
        String verBy = stafReq.getVerBy();
        String licenceExp = stafReq.getLicenceExp();
        String vaillage = stafReq.getVaillage();
        String district = stafReq.getDistrict();
        String province = stafReq.getProvince();
        String mobile = stafReq.getMobile();
        String mobile1 = stafReq.getMobile1();
        String gender = stafReq.getGender();
        String genderStatus = stafReq.getGenderStatus();
        String userId = stafReq.getUserId();
        String path="http://khounkham.com/images/staff/";
        String imageStaff = path+stafReq.getImageStaff();
        String saveById = path+stafReq.getSaveById();
        try {

            String SQL = " UPDATE STAFF  SET STAFT_NAME = '"+name+"' , STAFT_SURNAME ='"+surname+"' , ID_CARD = '"+idCard+"' , LICENCE_ID = '"+licenceId+"'  , VERIFY_BY = '"+verBy+"' , \n" +
                "LICENCE_ID_EXP = '"+licenceExp+"'  , VILLAGE = '"+vaillage+"'  , DISTRICT = '"+district+"'  ,\n" +
                    "PROVINCE ='"+province+"'  , MOBILE1 ='"+mobile+"'  , MOBILE2 = '"+mobile1+"'  ,GENDER = '"+gender+"'  , GENDER_STATUS = '"+genderStatus+"' , USERID = '"+userId+"' ,IMAGE_STAFF='"+imageStaff+"',SaveById='"+saveById+"'  WHERE KEY_ID =  '"+id+"' " ;
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    } @Override
    public int UpdateStaftNoUpdate(stafReq stafReq) {
        int i = 0 ;
        String id = stafReq.getId();
        String staftId = stafReq.getStaftId();
        String name = stafReq.getName();
        String surname = stafReq.getSurname();
        String idCard = stafReq.getIdCard();
        String licenceId = stafReq.getLicenceId();
        String verBy = stafReq.getVerBy();
        String licenceExp = stafReq.getLicenceExp();
        String vaillage = stafReq.getVaillage();
        String district = stafReq.getDistrict();
        String province = stafReq.getProvince();
        String mobile = stafReq.getMobile();
        String mobile1 = stafReq.getMobile1();
        String gender = stafReq.getGender();
        String genderStatus = stafReq.getGenderStatus();
        String userId = stafReq.getUserId();
        String saveById = stafReq.getSaveById();
        String path="http://khounkham.com/images/staff/";
        String imageStaff = path+stafReq.getImageStaff();
        try {

            String SQL = " UPDATE STAFF  SET STAFT_NAME = '"+name+"' , STAFT_SURNAME ='"+surname+"' , ID_CARD = '"+idCard+"' , LICENCE_ID = '"+licenceId+"'  , VERIFY_BY = '"+verBy+"' , \n" +
                "LICENCE_ID_EXP = '"+licenceExp+"'  , VILLAGE = '"+vaillage+"'  , DISTRICT = '"+district+"'  ,\n" +
                    "PROVINCE ='"+province+"'  , MOBILE1 ='"+mobile+"'  , MOBILE2 = '"+mobile1+"'  ,GENDER = '"+gender+"'  , GENDER_STATUS = '"+genderStatus+"' , USERID = '"+userId+"', SaveById='"+saveById+"'   WHERE KEY_ID =  '"+id+"' " ;
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int deleteStaft(String id) {
        int i = 0;
        try {
            String SQL = " UPDATE  STAFF SET STATUS = 'D' WHERE KEY_ID = '"+id+"' ";
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public List<ProductOut> getAllProduct(ProductReq productReq) {

        List<ProductOut> restult = new ArrayList<>();

        try {

            String SQL = "SELECT p.KEY_ID , p.PRO_ID , p.PRO_NAME , p.PRO_TYPE , p.URL , p.USERID , p.DATE_INSERT, l.BRANCH  FROM PRODUCT p INNER JOIN LOGIN l ON p.saveById =l.KEY_ID  WHERE p.STATUS = 'A' AND l.BRANCH = '"+productReq.getBranch()+"' ORDER BY KEY_ID DESC ";
            System.out.println(SQL);

            restult = EBankJdbcTemplate.query(SQL , new ProductMapper());

        }catch (Exception e){
            e.printStackTrace();
            return restult;
        }

        return restult;
    }

    @Override
    public List<ProductOut> getProductById(ProductReq productReq) {
        List<ProductOut> restult = new ArrayList<>();

        try {

            String SQL = "SELECT p.KEY_ID , p.PRO_ID , p.PRO_NAME , p.PRO_TYPE , p.URL , p.USERID , p.DATE_INSERT, l.BRANCH  FROM PRODUCT p INNER JOIN LOGIN l ON p.saveById =l.KEY_ID  WHERE p.STATUS = 'A' AND l.BRANCH = '"+productReq.getBranch()+"'  AND KEY_ID = '"+productReq.getId()+"' ORDER BY KEY_ID DESC ";
            System.out.println(SQL);

            restult = EBankJdbcTemplate.query(SQL , new ProductMapper());

        }catch (Exception e){
            e.printStackTrace();
            return restult;
        }

        return restult;
    }

    @Override
    public int StoreProduct(ProductReq productReq) {
        int i = 0;

        String proId = productReq.getProId();
        String proName = productReq.getProName();
        String proType = productReq.getProType();
        String proUrl = productReq.getProUrl();
        String userId = productReq.getUserId();
        String saveById = productReq.getSaveById();


        try {

            String SQL = "INSERT INTO PRODUCT ( PRO_ID , PRO_NAME , PRO_TYPE , URL , USERID , DATE_INSERT , STATUS,SaveById) VALUES ( '"+proId+"' , '"+proName+"' , '"+proType+"' , '"+proUrl+"' , '"+userId+"' , now() , 'A','"+saveById+"' )";
            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }

        return i;
    }

    @Override
    public int UpdateProduct(ProductReq productReq) {

        int i = 0;

        String proId = productReq.getProId();
        String proName = productReq.getProName();
        String proType = productReq.getProType();
        String proUrl = productReq.getProUrl();
        String userId = productReq.getUserId();
        String saveById = productReq.getSaveById();

        try {

            String SQL = " UPDATE PRODUCT  SET  PRO_ID = '"+proId+"'  , PRO_NAME = '"+proName+"'  , PRO_TYPE = '"+proType+"'  , URL  = '"+proUrl+"' , USERID  = '"+userId+"',SaveById='"+saveById+"' WHERE KEY_ID = '"+productReq.getId()+"' ";
            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int deleteProduct(String id) {

        int i = 0;

        try {

            String SQL = " DELETE FROM PRODUCT  WHERE KEY_ID ='"+id+"' ";
            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }


    @Override
    public List<LocationOut> getAllLocatino(LocationReq locationReq) {

        List<LocationOut> result = new ArrayList<>();

        try {
            String SQL = "SELECT l.KEY_ID, l.PROVINCE , l.DETAIL , l.STATUS, a.BRANCH FROM LOCATION l INNER JOIN LOGIN a ON l.userId=a.KEY_ID WHERE l.STATUS = 'A' AND a.BRANCH ='"+locationReq.getBranch()+"'";

            result = EBankJdbcTemplate.query(SQL , new LocationOutMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<LocationOut> getLocationById(LocationReq locationReq) {
        List<LocationOut> result = new ArrayList<>();

        try {

            String SQL = "SELECT l.KEY_ID, l.PROVINCE , l.DETAIL , l.STATUS, a.BRANCH FROM LOCATION l INNER JOIN LOGIN a ON l.userId=a.KEY_ID WHERE l.STATUS = 'A' AND a.BRANCH ='"+locationReq.getBranch()+"' AND l.KEY_ID = '"+locationReq.getId()+"' ";

            result = EBankJdbcTemplate.query(SQL , new LocationOutMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public int StoreLocation(LocationReq locationReq) {

        String province = locationReq.getProvince();
        String detail = locationReq.getDetail();
        String userid = locationReq.getUserId();

        int i = 0;

        try {

            String SQL = "INSERT INTO LOCATION ( PROVINCE , DETAIL , STATUS,userId) VALUES ( '"+province+"' , '"+detail+"' , 'A','"+userid+"' ) " ;

            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return  i;
        }
        return  i;
    }

    @Override
    public int UpdateLocation(LocationReq locationReq) {

        int i = 0;

        try {

            String SQL = " UPDATE LOCATION SET PROVINCE =  '"+locationReq.getProvince()+"', DETAIL = '"+locationReq.getDetail()+"',userId='"+locationReq.getUserId()+"' WHERE KEY_ID = '"+locationReq.getId()+"' " ;

            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    @Override
    public int DeleteLocation(LocationReq locationReq) {
        int i = 0;

        try {
            String SQL = " UPDATE LOCATION SET STATUS = 'D' WHERE KEY_ID = '"+locationReq.getId()+"' ";

            i = EBankJdbcTemplate.update(SQL);


        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

    public List<ItemPaymentViewEntity> getPaymentItem() {
        String SQL = "SELECT * FROM v_payment_item ORDER BY status DESC";
        System.out.println(SQL);

        return EBankJdbcTemplate.query(SQL, new RowMapper<ItemPaymentViewEntity>() {
            @Override
            public ItemPaymentViewEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ItemPaymentViewEntity tr = new ItemPaymentViewEntity();
                tr.setPaymentNo(rs.getLong("payment_no"));
                tr.setPaymentSaveBy(rs.getString("payment_saveby"));
                tr.setPaymentSaveDate(rs.getTimestamp("payment_savedate"));
                tr.setPayBy(rs.getString("payby"));
                tr.setPayDate(rs.getTimestamp("pay_date"));
                tr.setDetail_id(rs.getInt("detail_id"));
                tr.setBillNo(rs.getString("bill_no"));
                tr.setInvoiceNo(rs.getString("invoice_no"));
                tr.setBorname(rs.getString("borname"));
                tr.setBlocation(rs.getString("blocation"));
                tr.setItemId(rs.getInt("item_id"));
                tr.setItemName(rs.getString("item_name"));
                tr.setUnit(rs.getString("unit"));
                tr.setSize(rs.getString("size"));
                tr.setQty(rs.getInt("qty"));
                tr.setPrice(rs.getBigDecimal("price"));
                tr.setTotal(rs.getFloat("total"));
                tr.setSaveBy(rs.getString("saveby"));
                tr.setSaveDate(rs.getTimestamp("savedate"));
                tr.setApproveBy(rs.getString("approveby"));
                tr.setApproveDate(rs.getTimestamp("approvedate"));
                tr.setBrandName(rs.getString("brand_name"));
                tr.setItemTypeName(rs.getString("itemtype_name"));
                tr.setImage(rs.getString("image"));
                tr.setStatus(rs.getString("status"));
                tr.setAmount(rs.getFloat("amount"));
                tr.setCcy(rs.getString("ccy"));
                tr.setExp(rs.getTimestamp("exp"));
                tr.setTotalcal(rs.getFloat("totalcal"));
                tr.setQtycal(rs.getInt("qtycal"));
                tr.setAmountscal(rs.getFloat("amountscal"));
                return tr;
            }
        });
    }

    public List<ItemDetailsEntity> getPaymentItemDetails() {
        String SQL = "SELECT * FROM v_payment_item_del ORDER BY status DESC";
        System.out.println(SQL);

        return EBankJdbcTemplate.query(SQL, new RowMapper<ItemDetailsEntity>() {
            @Override
            public ItemDetailsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ItemDetailsEntity tr = new ItemDetailsEntity();
                tr.setPayment_no(rs.getLong("payment_no"));
                tr.setBill_no(rs.getString("bill_no"));
                tr.setStatus(rs.getString("status"));
                tr.setInvoice_no(rs.getString("invoice_no"));
                tr.setTotal(rs.getFloat("total"));
                tr.setQty(rs.getInt("qty"));
                tr.setAmount(rs.getFloat("amount"));
                tr.setCcy(rs.getString("ccy"));
                tr.setPayment_type(rs.getString("payment_type"));
                tr.setRexchange_rate(rs.getFloat("rexchange_rate"));
                tr.setSavedate(rs.getTimestamp("savedate"));
                tr.setSaveby(rs.getString("saveby"));
                tr.setType(rs.getString("type"));
                tr.setExp(rs.getTimestamp("exp"));
                return tr;
            }
        });
    }
}
