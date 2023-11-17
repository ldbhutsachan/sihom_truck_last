package com.ldb.truck.Dao.Customer;

import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.customer.CustomerOut;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.product.ProductOut;
import com.ldb.truck.Model.Login.product.ProductReq;
import com.ldb.truck.Model.Login.staft.*;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
                 SQL="SELECT \n" +
                        "ID_CARD,LICENCE_ID,VILLAGE,DISTRICT,PROVINCE,\n" +
                        "STAFT_ID,STAFT_NAME,STAFT_SURNAME,COUNT(*) AS TOTALROW, \n" +
                        " cast(replace(STAFF_BIALIENG, ',', '') as unsigned)+ cast(replace(staff02_payAll, ',', '') as unsigned) AS staff02_payAll,\n" +
                        " cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)+ cast(replace(staff02_beforepay, ',', '') as unsigned) AS staff02_beforepay,\n" +
                        " cast(replace(STAFF_BIALINEG_KANGJAIY, ',', '') as unsigned)+ cast(replace(staff02_notpay, ',', '') as unsigned) AS staff02_notpay "+
                        "FROM CEHCK_PAY_STATFF GROUP BY STAFT_ID,STAFT_NAME,STAFT_SURNAME ORDER BY OUT_DATE  ASC";
            }else {
                 SQL = "SELECT \n" +
                        "ID_CARD,LICENCE_ID,VILLAGE,DISTRICT,PROVINCE,\n" +
                        "STAFT_ID,STAFT_NAME,STAFT_SURNAME,COUNT(*) AS TOTALROW, \n" +
                        " cast(replace(STAFF_BIALIENG, ',', '') as unsigned)+ cast(replace(staff02_payAll, ',', '') as unsigned) AS staff02_payAll,\n" +
                        " cast(replace(STAFF_BIALIENG_FRIST, ',', '') as unsigned)+ cast(replace(staff02_beforepay, ',', '') as unsigned) AS staff02_beforepay,\n" +
                        " cast(replace(STAFF_BIALINEG_KANGJAIY, ',', '') as unsigned)+ cast(replace(staff02_notpay, ',', '') as unsigned) AS staff02_notpay " +
                        "FROM CEHCK_PAY_STATFF where OUT_DATE between '" + staffPayReq.getStartDate() + "' and '" + staffPayReq.getEndDate() + "'\n" +
                        "GROUP BY STAFT_ID,STAFT_NAME,STAFT_SURNAME ORDER BY OUT_DATE  ASC";
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
    public List<ReportStaff> ListWaiyPaymentStaff() {
        try
        {
            String sql="select * from CEHCK_PAY_STATFF where staff_01_status='not-pay' or staff_01_status='not-pay' order by OUT_DATE asc";
            return  EBankJdbcTemplate.query(sql, new RowMapper<ReportStaff>() {
                @Override
                public ReportStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ReportStaff tr =new ReportStaff();
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
    @Override
    public int paymentStaff(StaffPaymentReq staffPaymentReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(staffPaymentReq.getPayDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try{
            String SQL="insert into PAYMENT_STAFF (PAY_DATE,staffID01,staffID02,payAmount_NotPay01,payAmount_NotPay02,payAmount_Pay01,payAmount_Pay02," +
                    "payAmount_TotalPay01,payAmount_TotalPay02,payAmount_status01,payAmount_status02) values (?,?,?,?,?,?,?,?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(sqlDate);
            paramList.add(staffPaymentReq.getStaffID01());
            paramList.add(staffPaymentReq.getStaffID02());
            paramList.add(staffPaymentReq.getPayAmount_NotPay01());
            paramList.add(staffPaymentReq.getPayAmount_NotPay02());
            paramList.add(staffPaymentReq.getPayAmount_Pay01());
            paramList.add(staffPaymentReq.getPayAmount_Pay02());
            paramList.add(staffPaymentReq.getPayAmount_TotalPay01());
            paramList.add(staffPaymentReq.getPayAmount_TotalPay02());
            paramList.add(staffPaymentReq.getPayAmount_status01());
            paramList.add(staffPaymentReq.getPayAmount_status02());
            return EBankJdbcTemplate.update(SQL,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //git alll
    @Override
    public int paymentStaffUpdate(StaffPaymentReq staffPaymentReq) throws ParseException {
        System.out.println("KEY DETAILS 01:"+staffPaymentReq.getKey_Id());
        System.out.println("staffPaymentReq 01:"+staffPaymentReq.getStaffID01());
        System.out.println("staffPaymentReq 02:"+staffPaymentReq.getStaffID02());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(staffPaymentReq.getPayDate());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try{
            String SQL="update TB_DETAILS set staff_01_status='"+staffPaymentReq.getPayAmount_status01()+"',staff_02_status='"+staffPaymentReq.getPayAmount_status02()+"' where LAHUD_POYLOD='"+staffPaymentReq.getKey_Id()+"'";
            System.out.println("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(sqlDate);
            paramList.add(staffPaymentReq.getStaffID01());
            paramList.add(staffPaymentReq.getStaffID02());
            paramList.add(staffPaymentReq.getPayAmount_NotPay01());
            paramList.add(staffPaymentReq.getPayAmount_NotPay02());
            paramList.add(staffPaymentReq.getPayAmount_Pay01());
            paramList.add(staffPaymentReq.getPayAmount_Pay02());
            paramList.add(staffPaymentReq.getPayAmount_TotalPay01());
            paramList.add(staffPaymentReq.getPayAmount_TotalPay02());
            paramList.add(staffPaymentReq.getPayAmount_status01());
            paramList.add(staffPaymentReq.getPayAmount_status02());
            return EBankJdbcTemplate.update(SQL,paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<CustomerOut> getAllCustomer() {
        List<CustomerOut> result = new ArrayList<>();
        try {

            String SQL = "SELECT KEY_ID , CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL  FROM CUSTOMER  WHERE STATUS='A' ";
            System.out.println(SQL);

            result = EBankJdbcTemplate.query(SQL , new getAllcustomerMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
    @Override
    public List<CustomerOut> getCustomerById(String id) {
        List<CustomerOut> result = new ArrayList<>();
        try {
            String SQL = "SELECT KEY_ID , CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL  FROM CUSTOMER  WHERE STATUS='A'  AND KEY_ID = '"+id+"' ";
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
            String SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID , CUSTOMER_NAME , ADDRESS , VILLAGE , DISTRICT , PROVICNE , MOBILE1 , MOBILE2 , EMAIL , STATUS)\n" +
                    "VALUES ( '"+custId+"' , '"+custName+"' , '"+addrss+"' , '"+village+"' , '"+district+"' , '"+provicnce+"' , '"+mobile+"' , '"+mobile1+"' , '"+email+"' , 'A') ";
            System.out.println(SQL);
            i = EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }

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


        try {

            String SQL = " UPDATE  CUSTOMER SET    CUSTOMER_ID ='"+custId+"'  , CUSTOMER_NAME  = '"+custName+"' , ADDRESS = '"+addrss+"' , VILLAGE ='"+village+"' , DISTRICT ='"+district+"' , PROVICNE ='"+provicnce+"', \n" +
                    " MOBILE1 = '"+mobile+"', MOBILE2  ='"+mobile1+"', EMAIL ='"+email+"'  WHERE KEY_ID = '"+id+"' " ;
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

            String SQL = "UPDATE CUSTOMER SET STATUS = 'D'  WHERE KEY_ID = '"+id+"' ";

            i = EBankJdbcTemplate.update(SQL);

        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }


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
    public List<staftOut> getChooseStaft01() {
        List<staftOut>  result = new ArrayList<>();
        try {
            String SQL = "SELECT  KEY_ID , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , " +
                    "LICENCE_ID_EXP , VILLAGE , DISTRICT , PROVINCE , MOBILE1 , MOBILE2, GENDER , GENDER_STATUS , GENDER_STATUS ,DATE_INSERT, USERID,IMAGE_STAFF   FROM STAFF WHERE OUT_STATUS = 'N'  and STATUS='A'";
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
        String path="http://khounkham.com/images/staff/";
        String imageStaff = path+stafReq.getImageStaff();
        try {
            String SQL  = " INSERT INTO STAFF ( OUT_STATUS,STATUS , STAFT_ID , STAFT_NAME , STAFT_SURNAME , ID_CARD , LICENCE_ID , VERIFY_BY , LICENCE_ID_EXP , VILLAGE , DISTRICT ,\n" +
                    "PROVINCE , MOBILE1 , MOBILE2 ,GENDER , GENDER_STATUS ,  USERID,IMAGE_STAFF ) VALUES ( 'N','A' ,'"+staftId+"' , '"+name+"' , '"+surname+"' , '"+idCard+"' ,\n " +
                    " '"+licenceId+"' , '"+verBy+"' , '"+licenceExp+"' , '"+vaillage+"' , '"+district+"'  ,'"+province+"' , '"+mobile+"' , '"+mobile1+"' , '"+gender+"' , '"+genderStatus+"' ,'"+userId+"','"+imageStaff+"') ";
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
        try {

            String SQL = " UPDATE STAFF  SET STAFT_NAME = '"+name+"' , STAFT_SURNAME ='"+surname+"' , ID_CARD = '"+idCard+"' , LICENCE_ID = '"+licenceId+"'  , VERIFY_BY = '"+verBy+"' , \n" +
                "LICENCE_ID_EXP = '"+licenceExp+"'  , VILLAGE = '"+vaillage+"'  , DISTRICT = '"+district+"'  ,\n" +
                    "PROVINCE ='"+province+"'  , MOBILE1 ='"+mobile+"'  , MOBILE2 = '"+mobile1+"'  ,GENDER = '"+gender+"'  , GENDER_STATUS = '"+genderStatus+"' , USERID = '"+userId+"' ,IMAGE_STAFF='"+imageStaff+"'  WHERE KEY_ID =  '"+id+"' " ;
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
        String path="http://khounkham.com/images/staff/";
        String imageStaff = path+stafReq.getImageStaff();
        try {

            String SQL = " UPDATE STAFF  SET STAFT_NAME = '"+name+"' , STAFT_SURNAME ='"+surname+"' , ID_CARD = '"+idCard+"' , LICENCE_ID = '"+licenceId+"'  , VERIFY_BY = '"+verBy+"' , \n" +
                "LICENCE_ID_EXP = '"+licenceExp+"'  , VILLAGE = '"+vaillage+"'  , DISTRICT = '"+district+"'  ,\n" +
                    "PROVINCE ='"+province+"'  , MOBILE1 ='"+mobile+"'  , MOBILE2 = '"+mobile1+"'  ,GENDER = '"+gender+"'  , GENDER_STATUS = '"+genderStatus+"' , USERID = '"+userId+"'   WHERE KEY_ID =  '"+id+"' " ;
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
    public List<ProductOut> getAllProduct() {

        List<ProductOut> restult = new ArrayList<>();

        try {

            String SQL = "SELECT KEY_ID , PRO_ID , PRO_NAME , PRO_TYPE , URL , USERID , DATE_INSERT  FROM PRODUCT  WHERE STATUS = 'A' ORDER BY KEY_ID DESC ";
            System.out.println(SQL);

            restult = EBankJdbcTemplate.query(SQL , new ProductMapper());

        }catch (Exception e){
            e.printStackTrace();
            return restult;
        }

        return restult;
    }

    @Override
    public List<ProductOut> getProductById(String id) {
        List<ProductOut> restult = new ArrayList<>();

        try {

            String SQL = "SELECT KEY_ID , PRO_ID , PRO_NAME , PRO_TYPE , URL , USERID , DATE_INSERT  FROM PRODUCT  WHERE STATUS = 'A'  AND KEY_ID = '"+id+"' ORDER BY KEY_ID DESC ";
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


        try {

            String SQL = "INSERT INTO PRODUCT ( PRO_ID , PRO_NAME , PRO_TYPE , URL , USERID , DATE_INSERT , STATUS) VALUES ( '"+proId+"' , '"+proName+"' , '"+proType+"' , '"+proUrl+"' , '"+userId+"' , now() , 'A' )";
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

        try {

            String SQL = " UPDATE PRODUCT  SET  PRO_ID = '"+proId+"'  , PRO_NAME = '"+proName+"'  , PRO_TYPE = '"+proType+"'  , URL  = '"+proUrl+"' , USERID  = '"+userId+"' WHERE KEY_ID = '"+productReq.getId()+"' ";
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
    public List<LocationOut> getAllLocatino() {

        List<LocationOut> result = new ArrayList<>();

        try {

            String SQL = "SELECT KEY_ID, PROVINCE , DETAIL , STATUS FROM LOCATION WHERE STATUS = 'A' ";

            result = EBankJdbcTemplate.query(SQL , new LocationOutMapper());

        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<LocationOut> getLocationById(String id) {
        List<LocationOut> result = new ArrayList<>();

        try {

            String SQL = "SELECT KEY_ID, PROVINCE , DETAIL , STATUS FROM LOCATION WHERE STATUS = 'A' WHERE KEY_ID = '"+id+"' ";

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

        int i = 0;

        try {

            String SQL = "INSERT INTO LOCATION ( PROVINCE , DETAIL , STATUS) VALUES ( '"+province+"' , '"+detail+"' , 'A' ) " ;

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

            String SQL = " UPDATE LOCATION SET PROVINCE =  '"+locationReq.getProvince()+"', DETAIL = '"+locationReq.getDetail()+"' WHERE KEY_ID = '"+locationReq.getId()+"' " ;

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
}
