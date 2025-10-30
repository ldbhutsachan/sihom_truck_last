package com.ldb.truck.Dao.VicicleHeaderDao;

import com.ldb.truck.Model.Login.CarOffice.*;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeader;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Service.GenTransectionID.TransactionIDGenerator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.net.URI;
import java.math.BigDecimal;

@Component
@Repository
public class VicicleHeaderServiceDao implements VicicleHeaderDao {
    TransactionIDGenerator transactionIDGenerator;
    private static final Logger log = LogManager.getLogger(VicicleHeaderServiceDao.class);
    @Autowired
    @Qualifier("EBankJdbcTemplate")
    private JdbcTemplate EBankJdbcTemplate;

    @Override
    public List<VicicleHeader> listVicicleHeader(VicicleHeaderReq vicicleHeaderReq) {
        try{
      String SQL ="select * from V_All_HEADER_TRUCK a left join MORFAI b on a.batNo =b.KEY_ID INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where c.BRANCH='"+vicicleHeaderReq.getBranch()+"' ";
//            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on " +
//                    "a.batNo =b.KEY_ID AND (a.batNo2 IS NULL OR a.batNo2 = b.KEY_ID) INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where c.BRANCH='"+vicicleHeaderReq.getBranch()+"'";
            log.info("SQL"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_reson"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));
                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));
                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setToBatRowGalanty(rs.getString("toBatRowGalanty"));
                    tr.setToBatRowtabienLod(rs.getString("toBatRowtabienLod"));

                    tr.setSaiystay(rs.getString("saiystay"));
                    tr.setGalick(rs.getString("galick"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
                    tr.setPha_But(rs.getString("pha_But"));
                    tr.setLektungsit(rs.getString("lektungsit"));
                    tr.setDate_change_lean(rs.getString("date_change_lean"));
                    tr.setDateExTungsit(rs.getString("dateExTungsit"));
                    tr.setDateExTungsit_status(rs.getString("dateExTungsit_status"));
                    tr.setBrand_wheel_car(rs.getString("brand_wheel_car"));
                    tr.setStatus_use_unuse_car(rs.getString("status_use_unuse_car"));
                    tr.setComment(rs.getString("comment"));
                    tr.setTechnique_date(rs.getString("technique_date"));
                    tr.setTechnique_date_per_month(rs.getString("technique_date_per_month"));
                    tr.setTechnique_date_status(rs.getString("technique_date_status"));

                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

  //list car Office
//    public List<CarOfficeModel> listCarOfficeDAOs(CarOfficeReq carOfficeReq) {
//        try {
//            String SQL = "SELECT * FROM V_OFFIE_CAR_STATUS a JOIN LOGIN c ON a.userId = c.KEY_ID WHERE c.BRANCH='" + carOfficeReq.getBranch() + "' ORDER BY arange ASC";
//            log.info("SQL: " + SQL);
//
//            return EBankJdbcTemplate.query(SQL, new RowMapper<CarOfficeModel>() {
//                @SneakyThrows
//                @Override
//                public CarOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    CarOfficeModel tr = new CarOfficeModel();
//                    tr.setKEY_ID(rs.getString("KEY_ID"));
//                    tr.setImg(rs.getString("img"));
//                    tr.setLicense_plate(rs.getString("license_plate"));
//                    tr.setBattery_code_name(rs.getString("battery_code_name"));
//                    tr.setLicense_plate_end(rs.getString("license_plate_end"));
//                    tr.setLicense_plate_start(rs.getString("license_plate_start"));
//                    tr.setCar_year(rs.getString("car_year"));
//                    tr.setCar_type(rs.getString("car_type"));
//                    tr.setCar_brand(rs.getString("car_brand"));
//                    tr.setLekJuk(rs.getString("lekJuk"));
//                    tr.setLekThung(rs.getString("lekThung"));
//                    tr.setCarColor(rs.getString("carColor"));
//                    tr.setFont_light(rs.getString("font_light"));
//                    tr.setBack_light(rs.getString("back_light"));
//                    tr.setMillor_back(rs.getString("millor_back"));
//                    tr.setMillor_side(rs.getString("millor_side"));
//                    tr.setCar_mileage_now(rs.getString("car_mileage_now"));
//                    tr.setCc(rs.getString("cc"));
//                    tr.setLeanGia(rs.getString("leanGia"));
//                    tr.setInsurance_Lao(rs.getString("insurance_Lao"));
//                    tr.setInsurance_viet(rs.getString("insurance_viet"));
//                    tr.setInsurance_thai(rs.getString("insurance_thai"));
//                    tr.setInsurance_Lao_expireDate(rs.getString("insurance_Lao_expireDate"));
//                    tr.setInsurance_viet_expireDate(rs.getString("insurance_viet_expireDate"));
//                    tr.setInsurance_thai_expireDate(rs.getString("insurance_thai_expireDate"));
//                    tr.setTechnic_check_dateStart(rs.getString("technic_check_dateStart"));
//                    tr.setTechnic_check_dateEnd(rs.getString("technic_check_dateEnd"));
//                    tr.setTotal_weigh_car(rs.getString("total_weigh_car"));
//                    tr.setOil(rs.getString("oil"));
//                    tr.setCar_model(rs.getString("car_model"));
//                    tr.setOwner_car(rs.getString("owner_car"));
//                    tr.setSteering_wheel(rs.getString("steering_wheel"));
//                    tr.setDao(rs.getString("dao"));
//                    tr.setWide(rs.getString("wide"));
//                    tr.setLongg(rs.getString("longg"));
//                    tr.setTall(rs.getString("tall"));
//                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
//                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
//                    tr.setSerial_wheel_left_font(rs.getString("serial_wheel_left_font"));
//                    tr.setSerial_wheel_left_back(rs.getString("serial_wheel_left_back"));
//                    tr.setSerial_wheel_right_font(rs.getString("serial_wheel_right_font"));
//                    tr.setSerial_wheel_right_back(rs.getString("serial_wheel_right_back"));
//                    tr.setLICENSE_STATUS(rs.getString("LICENSE_STATUS"));
//                    tr.setInsurance_Lao_STATUS(rs.getString("insurance_Lao_STATUS"));
//                    tr.setInsurance_thai_STATUS(rs.getString("insurance_thai_STATUS"));
//                    tr.setInsurance_viet_STATUS(rs.getString("insurance_viet_STATUS"));
//                    tr.setTechnic_check_STATUS(rs.getString("technic_check_STATUS"));
//                    tr.setLean_STATUS(rs.getString("LEAN_STATUS"));
//                    tr.setLean(rs.getString("lean"));
//                    tr.setLean_gia_STATUS(rs.getString("LEAN_GIA_STATUS"));
//                    tr.setTungsitnumber(rs.getString("tungsitnumber"));
//                    tr.setTungsitDateExpire(rs.getString("tungsitDateExpire"));
//                    tr.setTUNGSIT_STATUS(rs.getString("TUNGSIT_STATUS"));
//                    tr.setLekmai_next(rs.getString("lekmai_next"));
//                    tr.setSerial_tire_second(rs.getString("serial_tire_second"));
//                    tr.setLean_engine_date_next_status(rs.getString("LEAN_ENGINE_DATE_NEXT_STATUS"));
//                    tr.setLekmai_next_status(rs.getString("LEKMAI_NEXT_STATUS"));
//                    tr.setDateChangeLeean(rs.getString("date_change_lean"));
//                    tr.setDateChangeLeeanNext(rs.getString("date_change_lean_next"));
//                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
//                    tr.setLeanGiaNextday(rs.getString("leanGiaNextday"));
////                    add new
//                    tr.setStartdate_kongnam(rs.getString("startdate_kongnam"));
//                    tr.setEnddate_kongnam(rs.getString("enddate_kongnam"));
//
//                    String phoneNumber = "8562092661111"; // Replace with actual phone number
//                    String carInfo = "Car Brand: " + tr.getCar_brand() + ", License Plate: " + tr.getLicense_plate();
//
//                    // Send SMS reminders based on status conditions
//                    if ("E".equals(rs.getString("technic_check_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+"ໃບກວດກາເຕັກນິກໃກ້ຈະໝົດອາຍຸແລ້ວ.ໃນວັນທີ");
//                    } else if ("E".equals(rs.getString("LICENSE_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+"ໃບທະບຽນລົດໃກ້ຈະໝົດອາຍຸແລ້ວ.");
//                    } else if ("E".equals(rs.getString("insurance_Lao_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+"ປະກັນໄພລາວໃກ້ຈະໝົດອາຍຸແລ້ວ.");
//                    } else if ("E".equals(rs.getString("insurance_thai_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+"ປະກັນໄພໄທໃກ້ຈະໝົດອາຍຸແລ້ວ.");
//                    } else if ("E".equals(rs.getString("insurance_viet_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+"ປະກັນໄພຫວຽດໃກ້ຈະໝົດອາຍຸແລ້ວ.");
//                    } else if ("E".equals(rs.getString("LEAN_ENGINE_DATE_NEXT_STATUS"))) {
//                        sendSmsReminder(phoneNumber, carInfo, "ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+" ໃກ້ຈະຄົບກຳນົດວັນທີປ່ຽນນ້ຳມັນເຄື່ອງແລ້ວ.");
//                    } else if ("E".equals(rs.getString("TUNGSIT_STATUS")))
//                    {
//                        sendSmsReminder(phoneNumber, carInfo, "ເລກຕັງຊິດ"+"ລົດ "+tr.getCar_brand()+" ທະບຽນ "+tr.getLicense_plate()+" ໃກ້ຈະໝົດອາຍຸແລ້ວ");
//                    }
//                    return tr;
//                }
//            });
//
//        } catch (Exception e) {
//            log.error("Error retrieving car office data:", e);
//            throw new RuntimeException("Error retrieving car office data", e);
//        }
//    }
public List<CarOfficeModel> listCarOfficeDAOs(CarOfficeReq carOfficeReq, String role, String branch, String bor_no) {
    try {
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT * FROM V_OFFIE_CAR_STATUS a ")
                .append("JOIN LOGIN c ON a.userId = c.KEY_ID ")
                .append("WHERE 1 = 1 ");

        // ตรวจสอบ borNo ตาม role
        if ("PADMIN".equalsIgnoreCase(role) || "USERSTOCK".equalsIgnoreCase(role)) {
            if (carOfficeReq.getBorNo() != null && !carOfficeReq.getBorNo().trim().isEmpty()) {
                SQL.append("AND a.borNo = '").append(carOfficeReq.getBorNo()).append("' ");
                log.info("Query condition: BRANCH + BOR_NO");
            } else if (bor_no != null && !bor_no.isEmpty()) {
                SQL.append(" AND a.borNo = '").append(bor_no).append("'");
            } else {
                SQL.append("AND a.borNo IS NOT NULL AND TRIM(a.borNo) <> '' ");
                log.info("Query condition: BRANCH + BOR_NO IS NOT NULL");
            }
        } else {
            SQL.append("AND c.BRANCH = '").append(branch).append("' ");
            SQL.append("AND a.borNo IS NULL ");
            log.info("Query condition: BOR_NO IS NULL for non-PADMIN");
        }

        // สุดท้ายเรียงลำดับ
        SQL.append("ORDER BY arange ASC");

        log.info("Final SQL: " + SQL.toString());

        return EBankJdbcTemplate.query(SQL.toString(), new RowMapper<CarOfficeModel>() {
            @SneakyThrows
            @Override
            public CarOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                CarOfficeModel tr = new CarOfficeModel();

                // ==================== Map fields ====================
                tr.setKEY_ID(rs.getString("KEY_ID"));
                tr.setImg(rs.getString("img"));
                tr.setLicense_plate(rs.getString("license_plate"));
                tr.setBattery_code_name(rs.getString("battery_code_name"));
                tr.setLicense_plate_end(rs.getString("license_plate_end"));
                tr.setLicense_plate_start(rs.getString("license_plate_start"));
                tr.setCar_year(rs.getString("car_year"));
                tr.setCar_type(rs.getString("car_type"));
                tr.setCar_brand(rs.getString("car_brand"));
                tr.setLekJuk(rs.getString("lekJuk"));
                tr.setLekThung(rs.getString("lekThung"));
                tr.setCarColor(rs.getString("carColor"));
                tr.setFont_light(rs.getString("font_light"));
                tr.setBack_light(rs.getString("back_light"));
                tr.setMillor_back(rs.getString("millor_back"));
                tr.setMillor_side(rs.getString("millor_side"));
                tr.setCar_mileage_now(rs.getString("car_mileage_now"));
                tr.setCc(rs.getString("cc"));
                tr.setLeanGia(rs.getString("leanGia"));
                tr.setInsurance_Lao(rs.getString("insurance_Lao"));
                tr.setInsurance_viet(rs.getString("insurance_viet"));
                tr.setInsurance_thai(rs.getString("insurance_thai"));
                tr.setInsurance_Lao_expireDate(rs.getString("insurance_Lao_expireDate"));
                tr.setInsurance_viet_expireDate(rs.getString("insurance_viet_expireDate"));
                tr.setInsurance_thai_expireDate(rs.getString("insurance_thai_expireDate"));
                tr.setTechnic_check_dateStart(rs.getString("technic_check_dateStart"));
                tr.setTechnic_check_dateEnd(rs.getString("technic_check_dateEnd"));
                tr.setTotal_weigh_car(rs.getString("total_weigh_car"));
                tr.setOil(rs.getString("oil"));
                tr.setCar_model(rs.getString("car_model"));
                tr.setOwner_car(rs.getString("owner_car"));
                tr.setSteering_wheel(rs.getString("steering_wheel"));
                tr.setDao(rs.getString("dao"));
                tr.setWide(rs.getString("wide"));
                tr.setLongg(rs.getString("longg"));
                tr.setTall(rs.getString("tall"));
                tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
                tr.setSerial_wheel_left_font(rs.getString("serial_wheel_left_font"));
                tr.setSerial_wheel_left_back(rs.getString("serial_wheel_left_back"));
                tr.setSerial_wheel_right_font(rs.getString("serial_wheel_right_font"));
                tr.setSerial_wheel_right_back(rs.getString("serial_wheel_right_back"));
                tr.setLICENSE_STATUS(rs.getString("LICENSE_STATUS"));
                tr.setInsurance_Lao_STATUS(rs.getString("insurance_Lao_STATUS"));
                tr.setInsurance_thai_STATUS(rs.getString("insurance_thai_STATUS"));
                tr.setInsurance_viet_STATUS(rs.getString("insurance_viet_STATUS"));
                tr.setTechnic_check_STATUS(rs.getString("technic_check_STATUS"));
                tr.setLean_STATUS(rs.getString("LEAN_STATUS"));
                tr.setLean(rs.getString("lean"));
                tr.setLean_gia_STATUS(rs.getString("LEAN_GIA_STATUS"));
                tr.setTungsitnumber(rs.getString("tungsitnumber"));
                tr.setTungsitDateExpire(rs.getString("tungsitDateExpire"));
                tr.setTUNGSIT_STATUS(rs.getString("TUNGSIT_STATUS"));
                tr.setLekmai_next(rs.getString("lekmai_next"));
                tr.setSerial_tire_second(rs.getString("serial_tire_second"));
                tr.setLean_engine_date_next_status(rs.getString("LEAN_ENGINE_DATE_NEXT_STATUS"));
                tr.setLekmai_next_status(rs.getString("LEKMAI_NEXT_STATUS"));
                tr.setDateChangeLeean(rs.getString("date_change_lean"));
                tr.setDateChangeLeeanNext(rs.getString("date_change_lean_next"));
                tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
                tr.setLeanGiaNextday(rs.getString("leanGiaNextday"));
                tr.setStartdate_kongnam(rs.getString("startdate_kongnam"));
                tr.setEnddate_kongnam(rs.getString("enddate_kongnam"));
                tr.setBorNo(rs.getString("borNo"));
                tr.setBorName(rs.getString("borName"));
                // =======================================================

                // =================== ส่ง SMS (comment ไว้) ===================
                /*
                String phoneNumber = "8562092661111";
                String carInfo = "Car Brand: " + tr.getCar_brand() + ", License Plate: " + tr.getLicense_plate();

                if ("E".equals(rs.getString("technic_check_STATUS"))) {
                    sendSmsReminder(phoneNumber, carInfo,
                            "ລົດ " + tr.getCar_brand() + " ທະບຽນ " + tr.getLicense_plate() + " ໃບກວດກາເຕັກນິກໃກ້ຈະໝົດອາຍຸ.");
                } else if ("E".equals(rs.getString("LICENSE_STATUS"))) {
                    sendSmsReminder(phoneNumber, carInfo,
                            "ລົດ " + tr.getCar_brand() + " ທະບຽນ " + tr.getLicense_plate() + " ໃບທະບຽນລົດໃກ້ຈະໝົດອາຍຸ.");
                }
                // ... เงื่อนไขอื่นๆ ...
                */
                // ============================================================

                return tr;
            }
        });

    } catch (Exception e) {
        log.error("Error retrieving car office data:", e);
        throw new RuntimeException("Error retrieving car office data", e);
    }
}

//query car bor
public List<CarBorModel> listCarBorDao(CarBorReq CarBorReq, String role, String borNoClient, String borNoProfile) {
    StringBuilder SQL = new StringBuilder();

    SQL.append("SELECT a.KEY_ID, a.img, a.borNo, a.borName, a.license_plate, a.dao ")
            .append("FROM V_OFFIE_CAR_STATUS a WHERE 1=1 ");

    if ("PADMIN".equalsIgnoreCase(role)) {
        if (borNoClient != null && !borNoClient.trim().isEmpty()) {
            // filter ตาม borNo จาก client
            SQL.append("AND a.borNo = '").append(borNoClient).append("' ");
        } else {
            // ถ้า client ไม่ส่ง borNo → query ทุก borNo ที่ไม่ null
            SQL.append("AND a.borNo IS NOT NULL ");
        }
    } else {
        // สำหรับ user ปกติ → query ตาม borNo ของ profile
        SQL.append("AND a.borNo = '").append(borNoProfile).append("' ");
    }

    SQL.append("ORDER BY a.arange ASC");
    log.info("query: " + SQL);

    return EBankJdbcTemplate.query(SQL.toString(), (rs, rowNum) -> {
        CarBorModel tr = new CarBorModel();
        tr.setKEY_ID(rs.getString("KEY_ID"));
        tr.setImg(rs.getString("img"));
        tr.setBorNo(rs.getString("borNo"));
        tr.setBorName(rs.getString("borName"));
        tr.setLicense_plate(rs.getString("license_plate"));
        tr.setDao(rs.getString("dao"));
        return tr;
    });
}







private void sendSmsReminder(String phoneNumber, String carInfo, String messageBody) {
    String transactionID = transactionIDGenerator.generateTransactionID(); // Ensure proper resource management in generateTransactionID()

    for (int i = 0; i < 1; i++) {
        String baseJsonBody = "{\n" +
                "  \"transaction_id\": \"" + transactionID + "\",\n" +
                "  \"header\": \"Khounkham\",\n" +
                "  \"phoneNumber\": \"" + phoneNumber + "\",\n" +
                "  \"message\": \"" + messageBody + "\"\n" +
                "}";

        try {
            HttpResponse<JsonNode> response = Unirest.post("https://apicenter.laotel.com:9443/api/sms_center/submit_sms")
                    .header("apikey", "jkurfS6hxJiyf9Ag6rAodo7AiU1rEda6")
                    .header("Content-Type", "application/json")
                    .body(baseJsonBody)
                    .asJson();

            if (response.getStatus() == 200) {
                log.info("SMS sent to " + phoneNumber + " successfully (Attempt " + (i + 1) + ")");
            } else {
                log.error("Error sending SMS to " + phoneNumber + ": " + response.getStatus() + " - " + response.getBody());
            }
        } catch (Exception e) {
            log.error("Error sending SMS reminder:", e);
        }
    }
}

    // list car dao that paid
    @Override
    public List<CarPaidModel> listCarDaoPaid (CarOfficeReq carOfficeReq) {
        String SQL =null;
        try{
            if (carOfficeReq.getStartDate()!=null && carOfficeReq.getEndDate()!=null ) {
                SQL = "select * from V_CAR_PAID_HIS  where BRANCH='" + carOfficeReq.getBranch() + "' and dateCreate between '"+carOfficeReq.getStartDate()+"' and '"+carOfficeReq.getEndDate()+"'";
                log.info("SQL_select_date" + SQL);
            }else
            {
                SQL = "select * from V_CAR_PAID_HIS  where BRANCH='" + carOfficeReq.getBranch() + "' ";
                log.info("SQL_select_all:" + SQL);
            }
            return EBankJdbcTemplate.query(SQL, new RowMapper<CarPaidModel>() {
                @Override
                public CarPaidModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CarPaidModel tr = new CarPaidModel();
                    tr.setImg(rs.getString("img"));
                    tr.setLicense_plate(rs.getString("license_plate"));
                    tr.setPdfFile(rs.getString("pdfFile"));
                    tr.setCur(rs.getString("cur"));
                    tr.setPricePaid(rs.getString("pricePaid"));
                    tr.setDateCreate(rs.getString("dateCreate"));
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // list lod dao
    @Override
    public List<CarOfficeModel> listLodDaoOfficeDAOs (CarOfficeReq carOfficeReq) {
        try{
            String SQL ="select * from V_OFFIE_CAR_STATUS a INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where c.BRANCH='"+carOfficeReq.getBranch()+"' and a.dao='YES'";
            log.info("SQL"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<CarOfficeModel>() {
                @Override
                public CarOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CarOfficeModel tr = new CarOfficeModel();
                    tr.setKEY_ID(rs.getString("KEY_ID"));
                    tr.setImg(rs.getString("img"));
                    tr.setLicense_plate(rs.getString("license_plate"));
                    tr.setBattery_code_name(rs.getString("battery_code_name"));
                    tr.setLicense_plate_end(rs.getString("license_plate_end"));
                    tr.setLicense_plate_start(rs.getString("license_plate_start"));
                    tr.setCar_year(rs.getString("car_year"));
                    tr.setCar_type(rs.getString("car_type"));
                    tr.setCar_brand(rs.getString("car_brand"));
                    tr.setLekJuk(rs.getString("lekJuk"));
                    tr.setLekThung(rs.getString("lekThung"));
                    tr.setCarColor(rs.getString("carColor"));
                    tr.setFont_light(rs.getString("font_light"));
                    tr.setBack_light(rs.getString("back_light"));
                    tr.setMillor_back(rs.getString("millor_back"));
                    tr.setMillor_side(rs.getString("millor_side"));
                    tr.setCar_mileage_now(rs.getString("car_mileage_now"));
                    tr.setCc(rs.getString("cc"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setInsurance_Lao(rs.getString("insurance_Lao"));
                    tr.setInsurance_viet(rs.getString("insurance_viet"));
                    tr.setInsurance_thai(rs.getString("insurance_thai"));
                    tr.setInsurance_Lao_expireDate(rs.getString("insurance_Lao_expireDate"));
                    tr.setInsurance_viet_expireDate(rs.getString("insurance_viet_expireDate"));
                    tr.setInsurance_thai_expireDate(rs.getString("insurance_thai_expireDate"));
                    tr.setTechnic_check_dateStart(rs.getString("technic_check_dateStart"));
                    tr.setTechnic_check_dateEnd(rs.getString("technic_check_dateEnd"));
                    tr.setTotal_weigh_car(rs.getString("total_weigh_car"));
                    tr.setOil(rs.getString("oil"));
                    tr.setCar_model(rs.getString("car_model"));
                    tr.setOwner_car(rs.getString("owner_car"));
                    tr.setSteering_wheel(rs.getString("steering_wheel"));
                    tr.setDao(rs.getString("dao"));
                    tr.setWide(rs.getString("wide"));
                    tr.setLongg(rs.getString("longg"));
                    tr.setTall(rs.getString("tall"));
                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
                    tr.setSerial_wheel_left_font(rs.getString("serial_wheel_left_font"));
                    tr.setSerial_wheel_left_back(rs.getString("serial_wheel_left_back"));
                    tr.setSerial_wheel_right_font(rs.getString("serial_wheel_right_font"));
                    tr.setSerial_wheel_right_back(rs.getString("serial_wheel_right_back"));
                    tr.setLICENSE_STATUS(rs.getString("LICENSE_STATUS"));
                    tr.setInsurance_Lao_STATUS(rs.getString("insurance_Lao_STATUS"));
                    tr.setInsurance_thai_STATUS(rs.getString("insurance_thai_STATUS"));
                    tr.setInsurance_viet_STATUS(rs.getString("insurance_viet_STATUS"));
                    tr.setTechnic_check_STATUS(rs.getString("technic_check_STATUS"));
                    tr.setLean_STATUS(rs.getString("LEAN_STATUS"));
                    tr.setLean(rs.getString("lean"));
                    tr.setTungsitnumber(rs.getString("tungsitnumber"));
                    tr.setTungsitDateExpire(rs.getString("tungsitDateExpire"));
                    tr.setLean_gia_STATUS(rs.getString("LEAN_GIA_STATUS"));
                    tr.setTUNGSIT_STATUS(rs.getString("TUNGSIT_STATUS"));
                    tr.setLekmai_next(rs.getString("lekmai_next"));
                    tr.setSerial_tire_second(rs.getString("serial_tire_second"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));     //fix 12-12-2024 time 10:32

                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // car office detail
    @Override
    public List<CarOfficeModel> listCarOfficeDAOsDetailById (CarOfficeReq carOfficeReq) {
//        final String ACCOUNT_SID = "AC0f41da64f12a09afba5f8e84efa72eda";
//        final String AUTH_TOKEN = "83f8118f3b5dbc1ece33af5b8b9a1d28";
        try{
//            public class Example {
                // Find your Account Sid and Token at twilio.com/console

//                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//                     Message message = Message.creator(
//                            new com.twilio.type.PhoneNumber("whatsapp:+8562091056567"),
//                            new com.twilio.type.PhoneNumber("whatsapp:+15005550006"),
//                            "HXXXXXXXXX")
//                    .setContentVariables("{\"1\":\"pid\"}")
//                    .setMessagingServiceSid("MGXXXXXXXX")
//                    .create();
//            System.out.println(message.getBody());
// ________________________________________________________________________________________________________
            String SQL ="select * from V_OFFIE_CAR_STATUS a INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where a.KEY_ID ='"+carOfficeReq.getKeyId()+"' ";
            log.info("SQL"+SQL);
            return EBankJdbcTemplate.query(SQL, new RowMapper<CarOfficeModel>() {
                @Override
                public CarOfficeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CarOfficeModel tr = new CarOfficeModel();
                    tr.setKEY_ID(rs.getString("KEY_ID"));
                    tr.setImg(rs.getString("img"));
                    tr.setLicense_plate(rs.getString("license_plate"));
                    tr.setBattery_code_name(rs.getString("battery_code_name"));
                    tr.setLicense_plate_end(rs.getString("license_plate_end"));
                    tr.setLicense_plate_start(rs.getString("license_plate_start"));
                    tr.setCar_year(rs.getString("car_year"));
                    tr.setCar_type(rs.getString("car_type"));
                    tr.setCar_brand(rs.getString("car_brand"));
                    tr.setLekJuk(rs.getString("lekJuk"));
                    tr.setLekThung(rs.getString("lekThung"));
                    tr.setCarColor(rs.getString("carColor"));
                    tr.setFont_light(rs.getString("font_light"));
                    tr.setBack_light(rs.getString("back_light"));
                    tr.setMillor_back(rs.getString("millor_back"));
                    tr.setMillor_side(rs.getString("millor_side"));
                    tr.setCar_mileage_now(rs.getString("car_mileage_now"));
                    tr.setCc(rs.getString("cc"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setInsurance_Lao(rs.getString("insurance_Lao"));
                    tr.setInsurance_viet(rs.getString("insurance_viet"));
                    tr.setInsurance_thai(rs.getString("insurance_thai"));
                    tr.setInsurance_Lao_expireDate(rs.getString("insurance_Lao_expireDate"));
                    tr.setInsurance_viet_expireDate(rs.getString("insurance_viet_expireDate"));
                    tr.setInsurance_thai_expireDate(rs.getString("insurance_thai_expireDate"));
                    tr.setTechnic_check_dateStart(rs.getString("technic_check_dateStart"));
                    tr.setTechnic_check_dateEnd(rs.getString("technic_check_dateEnd"));
                    tr.setTotal_weigh_car(rs.getString("total_weigh_car"));
                    tr.setOil(rs.getString("oil"));
                    tr.setCar_model(rs.getString("car_model"));
                    tr.setOwner_car(rs.getString("owner_car"));
                    tr.setSteering_wheel(rs.getString("steering_wheel"));
                    tr.setDao(rs.getString("dao"));
                    tr.setWide(rs.getString("wide"));
                    tr.setLongg(rs.getString("longg"));
                    tr.setTall(rs.getString("tall"));
                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
                    tr.setSitPosition_amount(rs.getString("sitPosition_amount"));
                    tr.setSerial_wheel_left_font(rs.getString("serial_wheel_left_font"));
                    tr.setSerial_wheel_left_back(rs.getString("serial_wheel_left_back"));
                    tr.setSerial_wheel_right_font(rs.getString("serial_wheel_right_font"));
                    tr.setSerial_wheel_right_back(rs.getString("serial_wheel_right_back"));
                    tr.setLICENSE_STATUS(rs.getString("LICENSE_STATUS"));
                    tr.setInsurance_Lao_STATUS(rs.getString("insurance_Lao_STATUS"));
                    tr.setInsurance_thai_STATUS(rs.getString("insurance_thai_STATUS"));
                    tr.setInsurance_viet_STATUS(rs.getString("insurance_viet_STATUS"));
                    tr.setTechnic_check_STATUS(rs.getString("technic_check_STATUS"));
                    tr.setLean_STATUS(rs.getString("LEAN_STATUS"));
                    tr.setLean(rs.getString("lean"));
                    tr.setTungsitnumber(rs.getString("tungsitnumber"));
                    tr.setTungsitDateExpire(rs.getString("tungsitDateExpire"));
                    tr.setLean_gia_STATUS(rs.getString("LEAN_GIA_STATUS"));
                    tr.setTUNGSIT_STATUS(rs.getString("TUNGSIT_STATUS"));
                    tr.setLekmai_next(rs.getString("lekmai_next"));
                    tr.setSerial_tire_second(rs.getString("serial_tire_second"));
                    tr.setLean_engine_date_next_status(rs.getString("LEAN_ENGINE_DATE_NEXT_STATUS"));
                    tr.setLekmai_next_status(rs.getString("LEKMAI_NEXT_STATUS"));
                    tr.setDateChangeLeean(rs.getString("date_change_lean"));
                    tr.setDateChangeLeeanNext(rs.getString("date_change_lean_next"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));     //fix 12-12-2024 time 10:32
                    tr.setLeanGiaNextday(rs.getString("leanGiaNextday"));     //fix 12-12-2024 time 10:32
                    //add new
                    tr.setStartdate_kongnam(rs.getString("startdate_kongnam"));
                    tr.setEnddate_kongnam(rs.getString("enddate_kongnam"));
//                    tr.setStartdate_kongnam(rs.getString("startdate_kongnam"));
//                    tr.setEnddate_kongnam(rs.getString("endate_kongnam"));
                    return tr ;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<VicicleHeader> listVicicleHeaderByID(VicicleHeaderReq vicicleHeaderReq) {
        try{
            String SQL="";
            log.info("key:"+vicicleHeaderReq.getKey_id());
// old           SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID where a.key_id='"+vicicleHeaderReq.getKey_id()+"'";
                 SQL ="select * from V_All_HEADER_TRUCK a left join MORFAI b on a.batNo =b.KEY_ID INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where a.key_id='"+vicicleHeaderReq.getKey_id()+"' and c.BRANCH ='"+vicicleHeaderReq.getBranch()+"' ";
// have 2 morfai                SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID  AND (a.batNo2 IS NULL OR a.batNo2 = b.KEY_ID) INNER JOIN LOGIN c ON a.userId = c.KEY_ID where a.key_id='"+vicicleHeaderReq.getKey_id()+"' and c.BRANCH ='"+vicicleHeaderReq.getBranch()+"' ";
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("KEY_ID"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_NO_7(rs.getString("LL_TIRE_NO_7"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_DATE_7(rs.getString("LL_TIRE_DATE_7"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setLL_TIRE_KM_7(rs.getString("LL_TIRE_KM_7"));

                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_NO_7(rs.getString("R_TIRE_NO_7"));

                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_DATE_7(rs.getString("R_TIRE_DATE_7"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setR_TIRE_KM_7(rs.getString("R_TIRE_KM_7"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_RESON"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KM13(rs.getString("H_KM13"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    tr.setH_KML_13(rs.getString("H_KML_13"));
                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
//                    tr.setBat_StartDate2(rs.getString("Bat_StartDate2"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
//                    tr.setBat_EndDate2(rs.getString("Bat_EndDate2"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
//                    tr.setBatNo2(rs.getString("batNo2"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));
                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));
                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setToBatRowStatus2(rs.getString("toBatRow2"));
                    tr.setToBatRowGalanty(rs.getString("toBatRowGalanty"));
                    tr.setToBatRowtabienLod(rs.getString("toBatRowtabienLod"));

                    tr.setSaiystay(rs.getString("saiystay"));
                    tr.setGalick(rs.getString("galick"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
                    tr.setPha_But(rs.getString("pha_But"));
                    tr.setLeanGia_Status(rs.getString("leanGia_Status"));
                    tr.setLeanFuengThaiy_Status(rs.getString("leanFuengThaiy_Status"));
                    tr.setLektungsit(rs.getString("lektungsit"));
                    tr.setDate_change_lean(rs.getString("date_change_lean"));
                    tr.setDateExTungsit(rs.getString("dateExTungsit"));
                    tr.setDateExTungsit_status(rs.getString("dateExTungsit_status"));
                    tr.setBrand_wheel_car(rs.getString("brand_wheel_car"));
                    tr.setStatus_use_unuse_car(rs.getString("status_use_unuse_car"));
                    tr.setComment(rs.getString("comment"));
                    tr.setTechnique_date(rs.getString("technique_date"));
                    tr.setTechnique_date_per_month(rs.getString("technique_date_per_month"));
                    tr.setTechnique_date_status(rs.getString("technique_date_status"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int saveVicicleHeader(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        log.info("path:"+path+fileName);
        log.info("sqlEndDate:"+sqlEndDate);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_HEADER_TRUCK (H_VICIVLE_NUMBER,H_VICIVLE_GALATY,H_VICIVLE_DATE_GALATY,H_VICIVLE_TNGLOD,H_VICIVLE_BRANCH,H_VICIVLE_YEARLEVEL,H_VICIVLE_BRANCHTYPE,H_VICIVLE_DATEEXPRIRE,H_VICIVLE_LEKJUK, \n" +
                    "H_VICIVLE_LEKTHUNG,H_VICIVLE_GPS,H_VICIVLE_POYPUDNUMFON,H_VICIVLE_MORFAI,H_VICIVLE_BGTOM,H_VICIVLE_JANLARK,H_VICIVLE_FAINAR,H_VICIVLE_FAITHAIY,H_VICIVLE_FAIYKHANG,H_VICIVLE_VENMONGNAR,H_VICIVLE_VENMONGLHG , \n" +
                    "H_VICIVLE_VENKHANG,h_VICIVLE_GLASS,LL_TIRE_NO_1, LL_TIRE_NO_2, LL_TIRE_NO_3,LL_TIRE_NO_4,LL_TIRE_NO_5,LL_TIRE_NO_6,LL_TIRE_NO_7, LL_TIRE_DATE_1, LL_TIRE_DATE_2, LL_TIRE_DATE_3, LL_TIRE_DATE_4,LL_TIRE_DATE_5,LL_TIRE_DATE_6,LL_TIRE_DATE_7, \n" +
                    "LL_TIRE_KM_1 , LL_TIRE_KM_2 , LL_TIRE_KM_3 , LL_TIRE_KM_4 , LL_TIRE_KM_5 ,LL_TIRE_KM_6,LL_TIRE_KM_7,R_TIRE_NO_1,R_TIRE_NO_2,R_TIRE_NO_3,R_TIRE_NO_4,R_TIRE_NO_5,R_TIRE_NO_6,R_TIRE_NO_7, \n" +
                    "R_TIRE_DATE_1, R_TIRE_DATE_2, R_TIRE_DATE_3, R_TIRE_DATE_4, R_TIRE_DATE_5, R_TIRE_DATE_6,R_TIRE_DATE_7, R_TIRE_KM_1,R_TIRE_KM_2,R_TIRE_KM_3,R_TIRE_KM_4,R_TIRE_KM_5,R_TIRE_KM_6,R_TIRE_KM_7,H_LEK_NUMMUNKHG,kim_km," +
                    "H_KM1 ,\n" +
                    "H_KM2 ,\n" +
                    "H_KM3 ,\n" +
                    "H_KM4 ,\n" +
                    "H_KM5 ,\n" +
                    "H_KM6 ,\n" +
                    "H_KM7 ,\n" +
                    "H_KM8 ,\n" +
                    "H_KM9 ,\n" +
                    "H_KM10,\n" +
                    "H_KM11,\n" +
                    "H_KM12,\n" +
                    "H_KM13,\n" +
                    "H_KML_1 ,  \n" +
                    "H_KML_2 ,  \n" +
                    "H_KML_3 ,  \n" +
                    "H_KML_4 ,  \n" +
                    "H_KML_5 ,  \n" +
                    "H_KML_6 ,  \n" +
                    "H_KML_7 ,  \n" +
                    "H_KML_8 ,  \n" +
                    "H_KML_9 ,  \n" +
                    "H_KML_10,  \n" +
                    "H_KML_11,  \n" +
                    "H_KML_12,H_KML_13,Bat_StartDate,Bat_EndDate,IMAGE_TRUK,END_DATE_REGISCAR,COLOR_CAR,HORSEPOWER,batNo,H_STATUS,saiystay,galick,leanGia,leanFuengThaiy,pha_But,lektungsit,userId,date_change_lean,dateExTungsit,brand_wheel_car,status_use_unuse_car,comment,technique_date,technique_date_per_month)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);
            paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getBatNo());

            paramList.add(vicicleHeaderReq.getSaiystay());
            paramList.add(vicicleHeaderReq.getGalick());
            paramList.add(vicicleHeaderReq.getLeanGia().replace(",",""));
            paramList.add(vicicleHeaderReq.getLeanFuengThaiy().replace(",",""));
            paramList.add(vicicleHeaderReq.getPha_But());
            paramList.add(vicicleHeaderReq.getLektungsit());
            paramList.add(vicicleHeaderReq.getUserId());
            paramList.add(vicicleHeaderReq.getDate_change_lean());
            paramList.add(vicicleHeaderReq.getDateExTungsit());
            paramList.add(vicicleHeaderReq.getBrand_wheel_car());
            paramList.add(vicicleHeaderReq.getStatus_use_unuse_car());
            paramList.add(vicicleHeaderReq.getComment());
            paramList.add(vicicleHeaderReq.getTechnique_date());
            paramList.add(vicicleHeaderReq.getTechnique_date_per_month());
//            paramList.add(vicicleHeaderReq.getAdd_doc());
//            paramList.add(vicicleHeaderReq.getBatNo2());
//            paramList.add(vicicleHeaderReq.getBat_StartDate2());
//            paramList.add(vicicleHeaderReq.getBat_EndDate2());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    // insert car office DAOs
    @Override
    public int InsertCarOfficeDAOs (CarOfficeReq carOfficeReq) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
//        Date Insurance_thai_expireDate = sdf.parse(carOfficeReq.getInsurance_thai_expireDate());
//        Date Insurance_viet_expireDate = sdf.parse(carOfficeReq.getInsurance_viet_expireDate());
//        Date Insurance_Lao_expireDate = sdf.parse(carOfficeReq.getInsurance_Lao_expireDate());
//        Date Technic_check_dateEnd = sdf.parse(carOfficeReq.getTechnic_check_dateEnd());
//        Date Technic_check_dateStart = sdf.parse(carOfficeReq.getTechnic_check_dateStart());
//        Date License_plate_start = sdf.parse(carOfficeReq.getLicense_plate_start());
//        Date License_plate_end = sdf.parse(carOfficeReq.getLicense_plate_end());
//        Date LeanGia = sdf.parse(carOfficeReq.getLeanGia());
//
//        java.sql.Date sql_insurance_thai_expireDate = new java.sql.Date(Insurance_thai_expireDate.getTime());
//        java.sql.Date sql_insurance_viet_expireDate = new java.sql.Date(Insurance_viet_expireDate.getTime());
//        java.sql.Date sql_insurance_Lao_expireDate = new java.sql.Date(Insurance_Lao_expireDate.getTime());
//        java.sql.Date sql_technic_check_dateEnd = new java.sql.Date(Technic_check_dateEnd.getTime());
//        java.sql.Date sql_technic_check_dateStart = new java.sql.Date(Technic_check_dateStart.getTime());
//        java.sql.Date sql_license_plate_start = new java.sql.Date(License_plate_start.getTime());
//        java.sql.Date sql_license_plate_end = new java.sql.Date(License_plate_end.getTime());
//        java.sql.Date sql_LeanGia = new java.sql.Date(LeanGia.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = carOfficeReq.getImg();
        log.info("path:"+path+fileName);
//        log.info("sqlEndDate:"+sqlEndDate);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into CARS_OFFICE (img,license_plate,battery_code_name,license_plate_end,license_plate_start," +
                    "car_year,car_type,car_brand,lekJuk,lekThung,carColor,font_light,back_light,millor_back,millor_side,car_mileage_now,cc,leanGia," +
                    "insurance_Lao,insurance_viet,insurance_thai,insurance_Lao_expireDate,insurance_viet_expireDate,insurance_thai_expireDate," +
                    "technic_check_dateStart,technic_check_dateEnd,total_weigh_car,oil,car_model,owner_car,steering_wheel,dao,wide,longg,tall,sitPosition_amount,serial_wheel_left_font,serial_wheel_left_back,serial_wheel_right_font,serial_wheel_right_back,userId,lean,tungsitnumber,tungsitDateExpire,lekmai_next,serial_tire_second,date_change_lean,date_change_lean_next,leanFuengThaiy,leanGiaNextday,startdate_kongnam,enddate_kongnam,borNo) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(path + fileName);
            paramList.add(carOfficeReq.getLicense_plate());
            paramList.add(carOfficeReq.getBattery_code_name());
            paramList.add(carOfficeReq.getLicense_plate_end());
//            paramList.add(sql_license_plate_end);
            paramList.add(carOfficeReq.getLicense_plate_start());
//            paramList.add(sql_license_plate_start);
            paramList.add(carOfficeReq.getCar_year());
            paramList.add(carOfficeReq.getCar_type());
            paramList.add(carOfficeReq.getCar_brand());
            paramList.add(carOfficeReq.getLekJuk());
            paramList.add(carOfficeReq.getLekThung());
            paramList.add(carOfficeReq.getCarColor());
            paramList.add(carOfficeReq.getFont_light());
            paramList.add(carOfficeReq.getBack_light());
            paramList.add(carOfficeReq.getMillor_back());
            paramList.add(carOfficeReq.getMillor_side());
            paramList.add(carOfficeReq.getCar_mileage_now());
            paramList.add(carOfficeReq.getCc());
            paramList.add(carOfficeReq.getLeanGia());
//            paramList.add(sql_LeanGia);
            paramList.add(carOfficeReq.getInsurance_Lao());
            paramList.add(carOfficeReq.getInsurance_viet());
            paramList.add(carOfficeReq.getInsurance_thai());
            paramList.add(carOfficeReq.getInsurance_Lao_expireDate());
//            paramList.add(sql_insurance_Lao_expireDate);
            paramList.add(carOfficeReq.getInsurance_viet_expireDate());
//            paramList.add(sql_insurance_viet_expireDate);
            paramList.add(carOfficeReq.getInsurance_thai_expireDate());
//            paramList.add(sql_insurance_thai_expireDate);
            paramList.add(carOfficeReq.getTechnic_check_dateStart());
//            paramList.add(sql_technic_check_dateStart);
            paramList.add(carOfficeReq.getTechnic_check_dateEnd());
//            paramList.add(sql_technic_check_dateEnd);
            paramList.add(carOfficeReq.getTotal_weigh_car());
            paramList.add(carOfficeReq.getOil());
            paramList.add(carOfficeReq.getCar_model());
            paramList.add(carOfficeReq.getOwner_car());
            paramList.add(carOfficeReq.getSteering_wheel());
            paramList.add(carOfficeReq.getDao());
            paramList.add(carOfficeReq.getWide());
            paramList.add(carOfficeReq.getLongg());
            paramList.add(carOfficeReq.getTall());
            paramList.add(carOfficeReq.getSitPosition_amount());
            paramList.add(carOfficeReq.getSerial_wheel_left_font());
            paramList.add(carOfficeReq.getSerial_wheel_left_back());
            paramList.add(carOfficeReq.getSerial_wheel_right_font());
            paramList.add(carOfficeReq.getSerial_wheel_right_back());
            paramList.add(carOfficeReq.getUserId());
            paramList.add(carOfficeReq.getLean());
            paramList.add(carOfficeReq.getTungsitnumber());
            paramList.add(carOfficeReq.getTungsitDateExpire());
            paramList.add(carOfficeReq.getLekmai_next());
            paramList.add(carOfficeReq.getSerial_tire_second());
            paramList.add(carOfficeReq.getDate_change_lean());
            paramList.add(carOfficeReq.getDate_change_lean_next());
            paramList.add(carOfficeReq.getLeanFuengThaiy());
            paramList.add(carOfficeReq.getLeanGiaNextday());
            //add new
            paramList.add(carOfficeReq.getStartdate_kongnam());
            paramList.add(carOfficeReq.getEnddate_kongnam());
            paramList.add(carOfficeReq.getBorNo());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    //pay car dao
    @Override
    public int PayCarDao (PaidCarDaoReq paidCarDaoReq) throws ParseException {
        String path="http://khounkham.com/images/car/";
        String fileName = paidCarDaoReq.getPdfFile();
        log.info("path:"+path+fileName);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into JAIY_LOD_DAO (pdfFile,carId,cur,pricePaid,dateCreate,userId) value(?,?,?,?,now(),?) ";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(path + fileName);
            paramList.add(paidCarDaoReq.getCarId());
            paramList.add(paidCarDaoReq.getCur());
            paramList.add(paidCarDaoReq.getPricePaid());
            paramList.add(paidCarDaoReq.getUserId());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //update car office
    @Override
    public int UpdateCarOfficeDAOs (CarOfficeReq carOfficeReq) throws ParseException {
//
//        String path="http://khounkham.com/images/car/";
//        String fileName = carOfficeReq.getImg();
//        log.info("path:"+path+fileName);
//        log.info("sqlEndDate:"+sqlEndDate);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "update CARS_OFFICE set license_plate=?,battery_code_name=?,license_plate_end=?,license_plate_start=?,car_year=?,car_type=?,car_brand=?,lekJuk=?,lekThung=?,carColor=?,font_light=?,back_light=?,millor_back=?,millor_side=?,car_mileage_now=?,cc=?,leanGia=?,insurance_Lao=?,insurance_viet=?,insurance_thai=?,insurance_Lao_expireDate=?,insurance_viet_expireDate=?,insurance_thai_expireDate=?,technic_check_dateStart=?,technic_check_dateEnd=?,total_weigh_car=?,oil=?,car_model=?,owner_car=?,steering_wheel=?,dao=?,wide=?,longg=?,tall=?,sitPosition_amount=?,serial_wheel_left_font=?,serial_wheel_left_back=?,serial_wheel_right_font=?,serial_wheel_right_back=?,userId=?,tungsitnumber=?,tungsitDateExpire=?,lekmai_next=?,serial_tire_second=?,date_change_lean=?,date_change_lean_next=?,leanFuengThaiy=?,leanGiaNextday=?,startdate_kongnam=?,enddate_kongnam=? where KEY_ID ='"+carOfficeReq.getKEY_ID()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
//            paramList.add(path + fileName);
            paramList.add(carOfficeReq.getLicense_plate());
            paramList.add(carOfficeReq.getBattery_code_name());
            paramList.add(carOfficeReq.getLicense_plate_end());
            paramList.add(carOfficeReq.getLicense_plate_start());
            paramList.add(carOfficeReq.getCar_year());
            paramList.add(carOfficeReq.getCar_type());
            paramList.add(carOfficeReq.getCar_brand());
            paramList.add(carOfficeReq.getLekJuk());
            paramList.add(carOfficeReq.getLekThung());
            paramList.add(carOfficeReq.getCarColor());
            paramList.add(carOfficeReq.getFont_light());
            paramList.add(carOfficeReq.getBack_light());
            paramList.add(carOfficeReq.getMillor_back());
            paramList.add(carOfficeReq.getMillor_side());
            paramList.add(carOfficeReq.getCar_mileage_now());
            paramList.add(carOfficeReq.getCc());
            paramList.add(carOfficeReq.getLeanGia());
            paramList.add(carOfficeReq.getInsurance_Lao());
            paramList.add(carOfficeReq.getInsurance_viet());
            paramList.add(carOfficeReq.getInsurance_thai());
            paramList.add(carOfficeReq.getInsurance_Lao_expireDate());
            paramList.add(carOfficeReq.getInsurance_viet_expireDate());
            paramList.add(carOfficeReq.getInsurance_thai_expireDate());
            paramList.add(carOfficeReq.getTechnic_check_dateStart());
            paramList.add(carOfficeReq.getTechnic_check_dateEnd());
            paramList.add(carOfficeReq.getTotal_weigh_car());
            paramList.add(carOfficeReq.getOil());
            paramList.add(carOfficeReq.getCar_model());
            paramList.add(carOfficeReq.getOwner_car());
            paramList.add(carOfficeReq.getSteering_wheel());
            paramList.add(carOfficeReq.getDao());
            paramList.add(carOfficeReq.getWide());
            paramList.add(carOfficeReq.getLongg());
            paramList.add(carOfficeReq.getTall());
            paramList.add(carOfficeReq.getSitPosition_amount());
            paramList.add(carOfficeReq.getSerial_wheel_left_font());
            paramList.add(carOfficeReq.getSerial_wheel_left_back());
            paramList.add(carOfficeReq.getSerial_wheel_right_font());
            paramList.add(carOfficeReq.getSerial_wheel_right_back());
            paramList.add(carOfficeReq.getUserId());
//            paramList.add(carOfficeReq.getLean());
            paramList.add(carOfficeReq.getTungsitnumber());
            paramList.add(carOfficeReq.getTungsitDateExpire());
            paramList.add(carOfficeReq.getLekmai_next());
            paramList.add(carOfficeReq.getSerial_tire_second());
            paramList.add(carOfficeReq.getDate_change_lean());
            paramList.add(carOfficeReq.getDate_change_lean_next());
            paramList.add(carOfficeReq.getLeanFuengThaiy());
            paramList.add(carOfficeReq.getLeanGiaNextday());
            //add new
            paramList.add(carOfficeReq.getStartdate_kongnam());
            paramList.add(carOfficeReq.getEnddate_kongnam());

            paramList.add(carOfficeReq.getKEY_ID());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
//    update car office notice status
public int UpdateCarOfficenoticeStatusDAOs (CarOfficeReq carOfficeReq){
    List<VicicleHeader> data = new ArrayList<>();
    try{
        String SQL = "update CARS_OFFICE set lean='"+carOfficeReq.getLean()+"'where KEY_ID ='"+carOfficeReq.getKeyId()+"'";
        log.info("SQL:"+SQL);
        List<Object> paramList = new ArrayList<Object>();

        paramList.add(carOfficeReq.getLean());
        paramList.add(carOfficeReq.getKeyId());
        return EBankJdbcTemplate.update(SQL, paramList.toArray());
    }catch (Exception e){
        e.printStackTrace();
        return -1;
    }
}
    // update car office by already have pic
    @Override
    public int updateCarOfficeUppicHaveData (CarOfficeReq carOfficeReq) throws ParseException {

        String path="http://khounkham.com/images/car/";
        String fileName = carOfficeReq.getImg();
        log.info("path:"+path+fileName);
//        log.info("sqlEndDate:"+sqlEndDate);
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "update CARS_OFFICE set img=? ,license_plate=?,battery_code_name=?,license_plate_end=?,license_plate_start=?,car_year=?,car_type=?,car_brand=?,lekJuk=?,lekThung=?,carColor=?,font_light=?,back_light=?,millor_back=?,millor_side=?,car_mileage_now=?,cc=?,leanGia=?,insurance_Lao=?,insurance_viet=?,insurance_thai=?,insurance_Lao_expireDate=?,insurance_viet_expireDate=?,insurance_thai_expireDate=?,technic_check_dateStart=?,technic_check_dateEnd=?,total_weigh_car=?,oil=?,car_model=?,owner_car=?,steering_wheel=?,dao=?,wide=?,longg=?,tall=?,sitPosition_amount=?,serial_wheel_left_font=?,serial_wheel_left_back=?,serial_wheel_right_font=?,serial_wheel_right_back=?,userId=?,lekmai_next=?,serial_tire_second=?,date_change_lean=?,date_change_lean_next=?,leanFuengThaiy=? where KEY_ID ='"+carOfficeReq.getKEY_ID()+"'";
            log.info("SQL:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(path + fileName);
            paramList.add(carOfficeReq.getLicense_plate());
            paramList.add(carOfficeReq.getBattery_code_name());
            paramList.add(carOfficeReq.getLicense_plate_end());
            paramList.add(carOfficeReq.getLicense_plate_start());
            paramList.add(carOfficeReq.getCar_year());
            paramList.add(carOfficeReq.getCar_type());
            paramList.add(carOfficeReq.getCar_brand());
            paramList.add(carOfficeReq.getLekJuk());
            paramList.add(carOfficeReq.getLekThung());
            paramList.add(carOfficeReq.getCarColor());
            paramList.add(carOfficeReq.getFont_light());
            paramList.add(carOfficeReq.getBack_light());
            paramList.add(carOfficeReq.getMillor_back());
            paramList.add(carOfficeReq.getMillor_side());
            paramList.add(carOfficeReq.getCar_mileage_now());
            paramList.add(carOfficeReq.getCc());
            paramList.add(carOfficeReq.getLeanGia());
            paramList.add(carOfficeReq.getToKen());
            paramList.add(carOfficeReq.getInsurance_Lao());
            paramList.add(carOfficeReq.getInsurance_viet());
            paramList.add(carOfficeReq.getInsurance_thai());
            paramList.add(carOfficeReq.getInsurance_Lao_expireDate());
            paramList.add(carOfficeReq.getInsurance_viet_expireDate());
            paramList.add(carOfficeReq.getInsurance_thai_expireDate());
            paramList.add(carOfficeReq.getTechnic_check_dateStart());
            paramList.add(carOfficeReq.getTechnic_check_dateEnd());
            paramList.add(carOfficeReq.getTotal_weigh_car());
            paramList.add(carOfficeReq.getOil());
            paramList.add(carOfficeReq.getCar_model());
            paramList.add(carOfficeReq.getOwner_car());
            paramList.add(carOfficeReq.getSteering_wheel());
            paramList.add(carOfficeReq.getDao());
            paramList.add(carOfficeReq.getWide());
            paramList.add(carOfficeReq.getLongg());
            paramList.add(carOfficeReq.getTall());
            paramList.add(carOfficeReq.getSitPosition_amount());
            paramList.add(carOfficeReq.getSerial_wheel_left_font());
            paramList.add(carOfficeReq.getSerial_wheel_left_back());
            paramList.add(carOfficeReq.getSerial_wheel_right_font());
            paramList.add(carOfficeReq.getSerial_wheel_right_back());
            paramList.add(carOfficeReq.getUserId());
//            paramList.add(carOfficeReq.getLean());
            paramList.add(carOfficeReq.getLekmai_next());
            paramList.add(carOfficeReq.getSerial_tire_second());
            paramList.add(carOfficeReq.getDate_change_lean());
            paramList.add(carOfficeReq.getDate_change_lean_next());
            paramList.add(carOfficeReq.getLeanFuengThaiy());
            paramList.add(carOfficeReq.getKEY_ID());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateVicicleHeader(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        try {
            String SQL ="update TB_HEADER_TRUCK set H_VICIVLE_NUMBER=?, H_VICIVLE_GALATY=?, H_VICIVLE_DATE_GALATY=?, H_VICIVLE_TNGLOD=?, H_VICIVLE_BRANCH=?, H_VICIVLE_YEARLEVEL=?, \n" +
                    "H_VICIVLE_BRANCHTYPE=?, H_VICIVLE_DATEEXPRIRE=?, H_VICIVLE_LEKJUK=?, H_VICIVLE_LEKTHUNG=?, H_VICIVLE_GPS=?, H_VICIVLE_POYPUDNUMFON=?, \n" +
                    "H_VICIVLE_MORFAI=?, H_VICIVLE_BGTOM=?, H_VICIVLE_JANLARK=?, H_VICIVLE_FAINAR=?, H_VICIVLE_FAITHAIY=?,h_VICIVLE_GLASS=?, H_VICIVLE_FAIYKHANG=?, \n" +
                    "H_VICIVLE_VENMONGNAR=?,H_VICIVLE_VENMONGLHG=?,H_VICIVLE_VENKHANG=?,LL_TIRE_NO_1=?,LL_TIRE_NO_2=?,LL_TIRE_NO_3=?,LL_TIRE_NO_4=?, \n" +
                    "LL_TIRE_NO_5=?,LL_TIRE_NO_6=?,LL_TIRE_NO_7=?,LL_TIRE_DATE_1=?,LL_TIRE_DATE_2=?,LL_TIRE_DATE_3=?,LL_TIRE_DATE_4=?,LL_TIRE_DATE_5=?, \n" +
                    "LL_TIRE_DATE_6=?,LL_TIRE_DATE_7=?,LL_TIRE_KM_1=?,LL_TIRE_KM_2 =?,LL_TIRE_KM_3 =?,LL_TIRE_KM_4 =?,LL_TIRE_KM_5 =?,LL_TIRE_KM_6 =?,LL_TIRE_KM_7 =?, \n" +
                    "R_TIRE_NO_1=?,R_TIRE_NO_2 =?,R_TIRE_NO_3  =?, \n" +
                    "R_TIRE_NO_4=?, \n" +
                    "R_TIRE_NO_5=?, \n" +
                    "R_TIRE_NO_6=?, \n" +
                    "R_TIRE_NO_7=?, \n" +
                    "R_TIRE_DATE_1=?, \n" +
                    "R_TIRE_DATE_2=?, \n" +
                    "R_TIRE_DATE_3=?, \n" +
                    "R_TIRE_DATE_4=?, \n" +
                    "R_TIRE_DATE_5=?, \n" +
                    "R_TIRE_DATE_6=?, \n" +
                    "R_TIRE_DATE_7=?, \n" +
                    "R_TIRE_KM_1=?, \n" +
                    "R_TIRE_KM_2=?, \n" +
                    "R_TIRE_KM_3=?, \n" +
                    "R_TIRE_KM_4=?, \n" +
                    "R_TIRE_KM_5=?, \n" +
                    "R_TIRE_KM_6=?,\n" +
                    "R_TIRE_KM_7=?,\n" +
                    "H_LEK_NUMMUNKHG=?,\n" +
                    "h_STATUS='Y',\n" +
                    "HIS_RESON=?,kim_km=?,H_KM1 =?,\n" +
                    "H_KM2 =?,\n" +
                    "H_KM3 =?,\n" +
                    "H_KM4 =?,\n" +
                    "H_KM5 =?,\n" +
                    "H_KM6 =?,\n" +
                    "H_KM7 =?,\n" +
                    "H_KM8 =?,\n" +
                    "H_KM9 =?,\n" +
                    "H_KM10=?,\n" +
                    "H_KM11=?,\n" +
                    "H_KM12=?,\n" +
                    "H_KM13=?,\n" +
                    "H_KML_1 =?,  \n" +
                    "H_KML_2 =?,  \n" +
                    "H_KML_3 =?,  \n" +
                    "H_KML_4 =?,  \n" +
                    "H_KML_5 =?,  \n" +
                    "H_KML_6 =?,  \n" +
                    "H_KML_7 =?,  \n" +
                    "H_KML_8 =?,  \n" +
                    "H_KML_9 =?,  \n" +
                    "H_KML_10=?,  \n" +
                    "H_KML_11=?,  \n" +
                    "H_KML_12=?,H_KML_13=?,Bat_StartDate=?,Bat_EndDate=?,IMAGE_TRUK=?,END_DATE_REGISCAR=?,COLOR_CAR=?,HORSEPOWER=?,batNo=?,saiystay=?,galick=?,leanGia=?,leanFuengThaiy=?,pha_But=?,lektungsit=?,userId=?,date_change_lean=?,dateExTungsit=?, brand_wheel_car=?, status_use_unuse_car=?, comment=?,technique_date=?,technique_date_per_month=? where  key_id=?";
            log.info("key_id truck is: "+vicicleHeaderReq.getKey_id());
            log.info("key_id BatNo is::"+vicicleHeaderReq.getBatNo());
            log.info("key_id VICIVLE_Morfai is::"+vicicleHeaderReq.getH_VICIVLE_MORFAI());
            log.info("font-send sql:"+SQL);

            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            //paramList.add(vicicleHeaderReq.getH_STATUS());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));

            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);

            paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getBatNo());

            paramList.add(vicicleHeaderReq.getSaiystay());
            paramList.add(vicicleHeaderReq.getGalick());
            paramList.add(vicicleHeaderReq.getLeanGia());
            paramList.add(vicicleHeaderReq.getLeanFuengThaiy());
            paramList.add(vicicleHeaderReq.getPha_But());
            paramList.add(vicicleHeaderReq.getLektungsit());
            paramList.add(vicicleHeaderReq.getUserId());
            paramList.add(vicicleHeaderReq.getDate_change_lean());
            paramList.add(vicicleHeaderReq.getDateExTungsit());
//            paramList.add(vicicleHeaderReq.getBat_StartDate2());
//            paramList.add(vicicleHeaderReq.getBat_EndDate2());
            paramList.add(vicicleHeaderReq.getBrand_wheel_car());
            paramList.add(vicicleHeaderReq.getStatus_use_unuse_car());
            paramList.add(vicicleHeaderReq.getComment());
            paramList.add(vicicleHeaderReq.getTechnique_date());
            paramList.add(vicicleHeaderReq.getTechnique_date_per_month());
            paramList.add(vicicleHeaderReq.getKey_id());
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int updateVicicleHeaderUppicHaveData(VicicleHeaderReq vicicleHeaderReq) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Bat_StartDateGalaty = sdf.parse(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
        java.sql.Date sqlStartDateGalanty = new java.sql.Date(Bat_StartDateGalaty.getTime());
        Date Bat_StartDate = sdf.parse(vicicleHeaderReq.getBat_StartDate());
        Date Bat_EndtDate = sdf.parse(vicicleHeaderReq.getBat_EndDate());
        java.sql.Date sqlStartDate = new java.sql.Date(Bat_StartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(Bat_EndtDate.getTime());
        String path="http://khounkham.com/images/car/";
        String fileName = vicicleHeaderReq.getImageTruck();
        try {
            String SQL ="update TB_HEADER_TRUCK set H_VICIVLE_NUMBER=?, H_VICIVLE_GALATY=?, H_VICIVLE_DATE_GALATY=?, H_VICIVLE_TNGLOD=?, H_VICIVLE_BRANCH=?, H_VICIVLE_YEARLEVEL=?, \n" +
                    "H_VICIVLE_BRANCHTYPE=?, H_VICIVLE_DATEEXPRIRE=?, H_VICIVLE_LEKJUK=?, H_VICIVLE_LEKTHUNG=?, H_VICIVLE_GPS=?, H_VICIVLE_POYPUDNUMFON=?, \n" +
                    "H_VICIVLE_MORFAI=?, H_VICIVLE_BGTOM=?, H_VICIVLE_JANLARK=?, H_VICIVLE_FAINAR=?, H_VICIVLE_FAITHAIY=?,h_VICIVLE_GLASS=?, H_VICIVLE_FAIYKHANG=?, \n" +
                    "H_VICIVLE_VENMONGNAR=?,H_VICIVLE_VENMONGLHG=?,H_VICIVLE_VENKHANG=?,LL_TIRE_NO_1=?,LL_TIRE_NO_2=?,LL_TIRE_NO_3=?,LL_TIRE_NO_4=?, \n" +
                    "LL_TIRE_NO_5=?,LL_TIRE_NO_6=?,LL_TIRE_NO_7=?,LL_TIRE_DATE_1=?,LL_TIRE_DATE_2=?,LL_TIRE_DATE_3=?,LL_TIRE_DATE_4=?,LL_TIRE_DATE_5=?, \n" +
                    "LL_TIRE_DATE_6=?,LL_TIRE_DATE_7=?,LL_TIRE_KM_1=?,LL_TIRE_KM_2 =?,LL_TIRE_KM_3 =?,LL_TIRE_KM_4 =?,LL_TIRE_KM_5 =?,LL_TIRE_KM_6 =?,LL_TIRE_KM_7 =?, \n" +
                    "R_TIRE_NO_1=?,R_TIRE_NO_2 =?,R_TIRE_NO_3  =?, \n" +
                    "R_TIRE_NO_4=?, \n" +
                    "R_TIRE_NO_5=?, \n" +
                    "R_TIRE_NO_6=?, \n" +
                    "R_TIRE_NO_7=?, \n" +
                    "R_TIRE_DATE_1=?, \n" +
                    "R_TIRE_DATE_2=?, \n" +
                    "R_TIRE_DATE_3=?, \n" +
                    "R_TIRE_DATE_4=?, \n" +
                    "R_TIRE_DATE_5=?, \n" +
                    "R_TIRE_DATE_6=?, \n" +
                    "R_TIRE_DATE_7=?, \n" +
                    "R_TIRE_KM_1=?, \n" +
                    "R_TIRE_KM_2=?, \n" +
                    "R_TIRE_KM_3=?, \n" +
                    "R_TIRE_KM_4=?, \n" +
                    "R_TIRE_KM_5=?, \n" +
                    "R_TIRE_KM_6=?,\n" +
                    "R_TIRE_KM_7=?,\n" +
                    "H_LEK_NUMMUNKHG=?,\n" +
                    "h_STATUS='Y',\n" +
                    "HIS_RESON=?,kim_km=?,H_KM1 =?,\n" +
                    "H_KM2 =?,\n" +
                    "H_KM3 =?,\n" +
                    "H_KM4 =?,\n" +
                    "H_KM5 =?,\n" +
                    "H_KM6 =?,\n" +
                    "H_KM7 =?,\n" +
                    "H_KM8 =?,\n" +
                    "H_KM9 =?,\n" +
                    "H_KM10=?,\n" +
                    "H_KM11=?,\n" +
                    "H_KM12=?,\n" +
                    "H_KM13=?,\n" +
                    "H_KML_1 =?,  \n" +
                    "H_KML_2 =?,  \n" +
                    "H_KML_3 =?,  \n" +
                    "H_KML_4 =?,  \n" +
                    "H_KML_5 =?,  \n" +
                    "H_KML_6 =?,  \n" +
                    "H_KML_7 =?,  \n" +
                    "H_KML_8 =?,  \n" +
                    "H_KML_9 =?,  \n" +
                    "H_KML_10=?,  \n" +
                    "H_KML_11=?,  \n" +
                    "H_KML_12=?,H_KML_13=?,Bat_StartDate=?,Bat_EndDate=?,END_DATE_REGISCAR=?,COLOR_CAR=?,HORSEPOWER=?,saiystay=?,galick=?,leanGia=?,leanFuengThaiy=?,pha_But=?,lektungsit=?,userId=?,date_change_lean=?,dateExTungsit=? ,brand_wheel_car=?,status_use_unuse_car=?,comment=?,technique_date=?,technique_date_per_month=? where  key_id=?";
            log.info("key_id truck is: "+vicicleHeaderReq.getKey_id());
            log.info("key_id BatNo is::"+vicicleHeaderReq.getBatNo());
            log.info("key_id VICIVLE_Morfai is::"+vicicleHeaderReq.getH_VICIVLE_MORFAI());
            log.info("font-send sql:"+SQL);
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(sqlStartDateGalanty);
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_7());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6().replace(",",""));
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_7().replace(",",""));
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
            //paramList.add(vicicleHeaderReq.getH_STATUS());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM().replace(",",""));

            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(sqlStartDate);
            paramList.add(sqlEndDate);

            //paramList.add(path+fileName);
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());

            paramList.add(vicicleHeaderReq.getSaiystay());
            paramList.add(vicicleHeaderReq.getGalick());
            paramList.add(vicicleHeaderReq.getLeanGia());
            paramList.add(vicicleHeaderReq.getLeanFuengThaiy());
            paramList.add(vicicleHeaderReq.getPha_But());
            paramList.add(vicicleHeaderReq.getLektungsit());
            paramList.add(vicicleHeaderReq.getUserId());
            paramList.add(vicicleHeaderReq.getDate_change_lean());
            paramList.add(vicicleHeaderReq.getDateExTungsit());
            paramList.add(vicicleHeaderReq.getBrand_wheel_car());
            paramList.add(vicicleHeaderReq.getStatus_use_unuse_car());
            paramList.add(vicicleHeaderReq.getComment());
            paramList.add(vicicleHeaderReq.getTechnique_date());
            paramList.add(vicicleHeaderReq.getTechnique_date_per_month());
//            paramList.add(vicicleHeaderReq.getBat_StartDate2());
//            paramList.add(vicicleHeaderReq.getBat_EndDate2());

            paramList.add(vicicleHeaderReq.getKey_id());
            log.info("SQL:"+SQL);
            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public int delVicicleHeader(VicicleHeaderReq vicicleHeaderReq) {
        int i =0;
        try {
            String SQL = "delete from TB_HEADER_TRUCK where key_id='" + vicicleHeaderReq.getKey_id() + "'";
           i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    // del car office DAOs
    @Override
    public int delCarOfficeDAOs (CarOfficeReq carOfficeReq) {
        String keyId = carOfficeReq.getKeyId();
        int i =0;
        try {
            String SQL = "delete from CARS_OFFICE where KEY_ID = '" + keyId +"'";
            log.info("SQL:"+SQL);
            i= EBankJdbcTemplate.update(SQL);
        }catch (Exception e){
            e.printStackTrace();
            return i;
        }
        return i;
    }
    @Override
    public int saveHeaderHistroty(VicicleHeaderReq vicicleHeaderReq) {
        List<VicicleHeader> data = new ArrayList<>();
        try{
            String SQL = "insert into TB_HEADER_TRUCK_HISTORY (H_VICIVLE_NUMBER,H_VICIVLE_GALATY,H_VICIVLE_DATE_GALATY,H_VICIVLE_TNGLOD,H_VICIVLE_BRANCH,H_VICIVLE_YEARLEVEL,H_VICIVLE_BRANCHTYPE,H_VICIVLE_DATEEXPRIRE,H_VICIVLE_LEKJUK, \n" +
                    "H_VICIVLE_LEKTHUNG,H_VICIVLE_GPS,H_VICIVLE_POYPUDNUMFON,H_VICIVLE_MORFAI,H_VICIVLE_BGTOM,H_VICIVLE_JANLARK,H_VICIVLE_FAINAR,H_VICIVLE_FAITHAIY,H_VICIVLE_FAIYKHANG,H_VICIVLE_VENMONGNAR,H_VICIVLE_VENMONGLHG , \n" +
                    "H_VICIVLE_VENKHANG,h_VICIVLE_GLASS,LL_TIRE_NO_1, LL_TIRE_NO_2, LL_TIRE_NO_3,LL_TIRE_NO_4,LL_TIRE_NO_5,LL_TIRE_NO_6, LL_TIRE_DATE_1, LL_TIRE_DATE_2, LL_TIRE_DATE_3, LL_TIRE_DATE_4,LL_TIRE_DATE_5,LL_TIRE_DATE_6, \n" +
                    "LL_TIRE_KM_1 , LL_TIRE_KM_2 , LL_TIRE_KM_3 , LL_TIRE_KM_4 , LL_TIRE_KM_5 ,LL_TIRE_KM_6,R_TIRE_NO_1,R_TIRE_NO_2,R_TIRE_NO_3,R_TIRE_NO_4,R_TIRE_NO_5,R_TIRE_NO_6, \n" +
                    "R_TIRE_DATE_1, R_TIRE_DATE_2, R_TIRE_DATE_3, R_TIRE_DATE_4, R_TIRE_DATE_5, R_TIRE_DATE_6, R_TIRE_KM_1,R_TIRE_KM_2,R_TIRE_KM_3,R_TIRE_KM_4,R_TIRE_KM_5,R_TIRE_KM_6,H_LEK_NUMMUNKHG,H_STATUS,HIS_DATE,HIS_RESON,kim_km,H_KM1 ,\n" +
                    "H_KM2 ,\n" +
                    "H_KM3 ,\n" +
                    "H_KM4 ,\n" +
                    "H_KM5 ,\n" +
                    "H_KM6 ,\n" +
                    "H_KM7 ,\n" +
                    "H_KM8 ,\n" +
                    "H_KM9 ,\n" +
                    "H_KM10,\n" +
                    "H_KM11,\n" +
                    "H_KM12,\n" +
                    "H_KM13,\n" +
                    "H_KML_1 ,  \n" +
                    "H_KML_2 ,  \n" +
                    "H_KML_3 ,  \n" +
                    "H_KML_4 ,  \n" +
                    "H_KML_5 ,  \n" +
                    "H_KML_6 ,  \n" +
                    "H_KML_7 ,  \n" +
                    "H_KML_8 ,  \n" +
                    "H_KML_9 ,  \n" +
                    "H_KML_10,  \n" +
                    "H_KML_11,  \n" +
                    "H_KML_12)\n" +
                    "H_KML_13,END_DATE_REGISCAR,COLOR_CAR,HORSEPOWER,IMAGE_TRUK,batNo,saiystay,galick,leanGia,leanFuengThaiy,Pha_But,userId)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'N',now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?)";
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(vicicleHeaderReq.getH_VICIVLE_NUMBER());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GALATY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATE_GALATY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_TNGLOD());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCH());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_YEARLEVEL());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BRANCHTYPE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_DATEEXPRIRE());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKJUK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_LEKTHUNG ());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GPS());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_POYPUDNUMFON());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_MORFAI());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_BGTOM());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_JANLARK());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAINAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAITHAIY());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_FAIYKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGNAR());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENMONGLHG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_VENKHANG());
            paramList.add(vicicleHeaderReq.getH_VICIVLE_GLASS());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_1());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_2());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_3());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_4());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_5());
            paramList.add(vicicleHeaderReq.getLL_TIRE_KM_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_NO_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_DATE_6());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_1());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_2());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_3());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_4());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_5());
            paramList.add(vicicleHeaderReq.getR_TIRE_KM_6());
            paramList.add(vicicleHeaderReq.getH_LEK_NUMMUNKHG());
         //   paramList.add(vicicleHeaderReq.getHIS_DATE());
            paramList.add(vicicleHeaderReq.getHis_REASON());
            paramList.add(vicicleHeaderReq.getKim_KM());
            paramList.add(vicicleHeaderReq.getH_KM1 ());
            paramList.add(vicicleHeaderReq.getH_KM2 ());
            paramList.add(vicicleHeaderReq.getH_KM3 ());
            paramList.add(vicicleHeaderReq.getH_KM4 ());
            paramList.add(vicicleHeaderReq.getH_KM5 ());
            paramList.add(vicicleHeaderReq.getH_KM6 ());
            paramList.add(vicicleHeaderReq.getH_KM7 ());
            paramList.add(vicicleHeaderReq.getH_KM8 ());
            paramList.add(vicicleHeaderReq.getH_KM9 ());
            paramList.add(vicicleHeaderReq.getH_KM10());
            paramList.add(vicicleHeaderReq.getH_KM11());
            paramList.add(vicicleHeaderReq.getH_KM12());
            paramList.add(vicicleHeaderReq.getH_KM13());
            paramList.add(vicicleHeaderReq.getH_KML_1 ());
            paramList.add(vicicleHeaderReq.getH_KML_2 ());
            paramList.add(vicicleHeaderReq.getH_KML_3 ());
            paramList.add(vicicleHeaderReq.getH_KML_4 ());
            paramList.add(vicicleHeaderReq.getH_KML_5 ());
            paramList.add(vicicleHeaderReq.getH_KML_6 ());
            paramList.add(vicicleHeaderReq.getH_KML_7 ());
            paramList.add(vicicleHeaderReq.getH_KML_8 ());
            paramList.add(vicicleHeaderReq.getH_KML_9 ());
            paramList.add(vicicleHeaderReq.getH_KML_10());
            paramList.add(vicicleHeaderReq.getH_KML_11());
            paramList.add(vicicleHeaderReq.getH_KML_12());
            paramList.add(vicicleHeaderReq.getH_KML_13());
            paramList.add(vicicleHeaderReq.getExCarDate());
            paramList.add(vicicleHeaderReq.getExCarColor());
            paramList.add(vicicleHeaderReq.getExHangMar());
            paramList.add(vicicleHeaderReq.getImageTruck());
            paramList.add(vicicleHeaderReq.getBatNo());

            paramList.add(vicicleHeaderReq.getSaiystay());
            paramList.add(vicicleHeaderReq.getGalick());
            paramList.add(vicicleHeaderReq.getLeanGia());
            paramList.add(vicicleHeaderReq.getLeanFuengThaiy());
            paramList.add(vicicleHeaderReq.getPha_But());
            paramList.add(vicicleHeaderReq.getUserId());


            return EBankJdbcTemplate.update(SQL, paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public List<VicicleHeader> ReportHistoryHeader(ReportAllReq vicicleHeaderReq) {
        log.info("start:"+vicicleHeaderReq.getStartDate());
        log.info("end:"+vicicleHeaderReq.getEndDate());
        try {
//   old         String  sql = "SELECT * FROM V_RE_HEADER_HIS WHERE HIS_DATE BETWEEN '"+vicicleHeaderReq.getStartDate()+"' AND '"+vicicleHeaderReq.getEndDate()+"' ";
            String  sql = "SELECT * FROM V_RE_HEADER_HIS a inner join LOGIN b ON a.userId =b.KEY_ID WHERE HIS_DATE BETWEEN '"+vicicleHeaderReq.getStartDate()+"' AND '"+vicicleHeaderReq.getEndDate()+"'AND b.BRANCH='"+vicicleHeaderReq.getBranch()+"'";
            return EBankJdbcTemplate.query(sql, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHIS_DATE(rs.getString("HIS_DATE"));
                    tr.setHis_REASON(rs.getString("HIS_RESON"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));

                    tr.setSaiystay(rs.getString("saiystay"));
                    tr.setGalick(rs.getString("galick"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
                    tr.setPha_But(rs.getString("Pha_But"));
                    return tr;
                }
            });
        }catch (Exception e ){
                e.printStackTrace();
        }
        return null;
    }
    //header combo1
    @Override
    public List<VicicleHeader> listVicicleHeaderCombox1(VicicleHeaderReq vicicleHeaderReq) {
        try{
//            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID where a.H_STATUS='Y' ";
//            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where a.H_STATUS='Y' and c.BRANCH ='"+vicicleHeaderReq.getBranch()+"'";
            String SQL ="select * from V_All_HEADER_TRUCK a left join MORFAI b on a.batNo =b.KEY_ID INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where c.BRANCH ='"+vicicleHeaderReq.getBranch()+"'";
//            String SQL ="select * from V_All_HEADER_TRUCK a inner join MORFAI b on a.batNo =b.KEY_ID  AND (a.batNo2 IS NULL OR a.batNo2 = b.KEY_ID) INNER JOIN LOGIN c ON a.userId  = c.KEY_ID where a.H_STATUS='Y' and c.BRANCH ='"+vicicleHeaderReq.getBranch()+"'";
            log.info("SQL: "+SQL);
//   เอาออก 5/11/2024         a.H_STATUS='Y' and
            return EBankJdbcTemplate.query(SQL, new RowMapper<VicicleHeader>() {
                @Override
                public VicicleHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VicicleHeader tr = new VicicleHeader();
                    tr.setKey_id(rs.getString("key_id"));
                    tr.setH_VICIVLE_NUMBER(rs.getString("H_VICIVLE_NUMBER"));
                    tr.setH_VICIVLE_GALATY(rs.getString("H_VICIVLE_GALATY"));
                    tr.setH_VICIVLE_DATE_GALATY(rs.getString("H_VICIVLE_DATE_GALATY"));
                    tr.setH_VICIVLE_TNGLOD(rs.getString("H_VICIVLE_TNGLOD"));
                    tr.setH_VICIVLE_BRANCH(rs.getString("H_VICIVLE_BRANCH"));
                    tr.setH_VICIVLE_YEARLEVEL(rs.getString("H_VICIVLE_YEARLEVEL"));
                    tr.setH_VICIVLE_BRANCHTYPE(rs.getString("H_VICIVLE_BRANCHTYPE"));
                    tr.setH_VICIVLE_DATEEXPRIRE(rs.getString("H_VICIVLE_DATEEXPRIRE"));
                    tr.setH_VICIVLE_LEKJUK(rs.getString("H_VICIVLE_LEKJUK"));
                    tr.setH_VICIVLE_LEKTHUNG (rs.getString("H_VICIVLE_LEKTHUNG"));
                    tr.setH_VICIVLE_GPS(rs.getString("H_VICIVLE_GPS"));
                    tr.setH_VICIVLE_POYPUDNUMFON(rs.getString("H_VICIVLE_POYPUDNUMFON"));
                    tr.setH_VICIVLE_MORFAI(rs.getString("H_VICIVLE_MORFAI"));
                    tr.setH_VICIVLE_BGTOM(rs.getString("H_VICIVLE_BGTOM"));
                    tr.setH_VICIVLE_JANLARK(rs.getString("H_VICIVLE_JANLARK"));
                    tr.setH_VICIVLE_FAINAR(rs.getString("H_VICIVLE_FAINAR"));
                    tr.setH_VICIVLE_FAITHAIY(rs.getString("H_VICIVLE_FAITHAIY"));
                    tr.setH_VICIVLE_FAIYKHANG(rs.getString("H_VICIVLE_FAIYKHANG"));
                    tr.setH_VICIVLE_VENMONGNAR(rs.getString("H_VICIVLE_VENMONGNAR"));
                    tr.setH_VICIVLE_VENMONGLHG(rs.getString("H_VICIVLE_VENMONGLHG"));
                    tr.setH_VICIVLE_VENKHANG(rs.getString("H_VICIVLE_VENKHANG"));
                    tr.setH_VICIVLE_GLASS(rs.getString("H_VICIVLE_GLASS"));
                    tr.setLL_TIRE_NO_1(rs.getString("LL_TIRE_NO_1"));
                    tr.setLL_TIRE_NO_2(rs.getString("LL_TIRE_NO_2"));
                    tr.setLL_TIRE_NO_3(rs.getString("LL_TIRE_NO_3"));
                    tr.setLL_TIRE_NO_4(rs.getString("LL_TIRE_NO_4"));
                    tr.setLL_TIRE_NO_5(rs.getString("LL_TIRE_NO_5"));
                    tr.setLL_TIRE_NO_6(rs.getString("LL_TIRE_NO_6"));
                    tr.setLL_TIRE_DATE_1(rs.getString("LL_TIRE_DATE_1"));
                    tr.setLL_TIRE_DATE_2(rs.getString("LL_TIRE_DATE_2"));
                    tr.setLL_TIRE_DATE_3(rs.getString("LL_TIRE_DATE_3"));
                    tr.setLL_TIRE_DATE_4(rs.getString("LL_TIRE_DATE_4"));
                    tr.setLL_TIRE_DATE_5(rs.getString("LL_TIRE_DATE_5"));
                    tr.setLL_TIRE_DATE_6(rs.getString("LL_TIRE_DATE_6"));
                    tr.setLL_TIRE_KM_1(rs.getString("LL_TIRE_KM_1"));
                    tr.setLL_TIRE_KM_2(rs.getString("LL_TIRE_KM_2"));
                    tr.setLL_TIRE_KM_3(rs.getString("LL_TIRE_KM_3"));
                    tr.setLL_TIRE_KM_4(rs.getString("LL_TIRE_KM_4"));
                    tr.setLL_TIRE_KM_5(rs.getString("LL_TIRE_KM_5"));
                    tr.setLL_TIRE_KM_6(rs.getString("LL_TIRE_KM_6"));
                    tr.setR_TIRE_NO_1(rs.getString("R_TIRE_NO_1"));
                    tr.setR_TIRE_NO_2(rs.getString("R_TIRE_NO_2"));
                    tr.setR_TIRE_NO_3(rs.getString("R_TIRE_NO_3"));
                    tr.setR_TIRE_NO_4(rs.getString("R_TIRE_NO_4"));
                    tr.setR_TIRE_NO_5(rs.getString("R_TIRE_NO_5"));
                    tr.setR_TIRE_NO_6(rs.getString("R_TIRE_NO_6"));
                    tr.setR_TIRE_DATE_1(rs.getString("R_TIRE_DATE_1"));
                    tr.setR_TIRE_DATE_2(rs.getString("R_TIRE_DATE_2"));
                    tr.setR_TIRE_DATE_3(rs.getString("R_TIRE_DATE_3"));
                    tr.setR_TIRE_DATE_4(rs.getString("R_TIRE_DATE_4"));
                    tr.setR_TIRE_DATE_5(rs.getString("R_TIRE_DATE_5"));
                    tr.setR_TIRE_DATE_6(rs.getString("R_TIRE_DATE_6"));
                    tr.setR_TIRE_KM_1(rs.getString("R_TIRE_KM_1"));
                    tr.setR_TIRE_KM_2(rs.getString("R_TIRE_KM_2"));
                    tr.setR_TIRE_KM_3(rs.getString("R_TIRE_KM_3"));
                    tr.setR_TIRE_KM_4(rs.getString("R_TIRE_KM_4"));
                    tr.setR_TIRE_KM_5(rs.getString("R_TIRE_KM_5"));
                    tr.setR_TIRE_KM_6(rs.getString("R_TIRE_KM_6"));
                    tr.setH_LEK_NUMMUNKHG(rs.getString("H_LEK_NUMMUNKHG"));
                    tr.setH_STATUS(rs.getString("H_STATUS"));
                    tr.setHis_REASON(rs.getString("his_reson"));
                    tr.setKim_KM(rs.getString("kim_km"));
                    tr.setH_KM1 (rs.getString("H_KM1"));
                    tr.setH_KM2 (rs.getString("H_KM2"));
                    tr.setH_KM3 (rs.getString("H_KM3"));
                    tr.setH_KM4 (rs.getString("H_KM4"));
                    tr.setH_KM5 (rs.getString("H_KM5"));
                    tr.setH_KM6 (rs.getString("H_KM6"));
                    tr.setH_KM7 (rs.getString("H_KM7"));
                    tr.setH_KM8 (rs.getString("H_KM8"));
                    tr.setH_KM9 (rs.getString("H_KM9"));
                    tr.setH_KM10(rs.getString("H_KM10"));
                    tr.setH_KM11(rs.getString("H_KM11"));
                    tr.setH_KM12(rs.getString("H_KM12"));
                    tr.setH_KML_1 (rs.getString("H_KML_1"));
                    tr.setH_KML_2 (rs.getString("H_KML_2"));
                    tr.setH_KML_3 (rs.getString("H_KML_3"));
                    tr.setH_KML_4 (rs.getString("H_KML_4"));
                    tr.setH_KML_5 (rs.getString("H_KML_5"));
                    tr.setH_KML_6 (rs.getString("H_KML_6"));
                    tr.setH_KML_7 (rs.getString("H_KML_7"));
                    tr.setH_KML_8 (rs.getString("H_KML_8"));
                    tr.setH_KML_9 (rs.getString("H_KML_9"));
                    tr.setH_KML_10(rs.getString("H_KML_10"));
                    tr.setH_KML_11(rs.getString("H_KML_11"));
                    tr.setH_KML_12(rs.getString("H_KML_12"));
                    tr.setToBatRowStatus(rs.getString("toBatRow"));
                    tr.setBat_StartDate(rs.getString("Bat_StartDate"));
//                    tr.setBat_StartDate2(rs.getString("Bat_StartDate2"));
                    tr.setBat_EndDate(rs.getString("Bat_EndDate"));
//                    tr.setBat_EndDate2(rs.getString("Bat_EndDate2"));
                    tr.setImageTruck(rs.getString("IMAGE_TRUK"));
                    tr.setExCarDate(rs.getString("END_DATE_REGISCAR"));
                    tr.setExCarColor(rs.getString("COLOR_CAR"));
                    tr.setExHangMar(rs.getString("HORSEPOWER"));
                    tr.setBatNo(rs.getString("batNo"));
//                    tr.setBatNo2(rs.getString("batNo2"));
                    tr.setIdMorFai(rs.getString("ID_MORFAI"));
                    tr.setImageMorFai(rs.getString("IMAGE_MORFAI"));
                    tr.setModalMorfai(rs.getString("MODAL_MORFAI"));
                    tr.setSizeMorfai(rs.getString("SIZE_MORFAI"));
                    tr.setServiceLife(rs.getString("SERVICE_LIFE"));

                    tr.setSaiystay(rs.getString("saiystay"));
                    tr.setGalick(rs.getString("galick"));
                    tr.setLeanGia(rs.getString("leanGia"));
                    tr.setLeanFuengThaiy(rs.getString("leanFuengThaiy"));
                    tr.setPha_But(rs.getString("Pha_But"));
                    tr.setLektungsit(rs.getString("lektungsit"));
                    tr.setDate_change_lean(rs.getString("date_change_lean"));
                    tr.setDateExTungsit_status(rs.getString("dateExTungsit_status"));
                    tr.setBrand_wheel_car(rs.getString("brand_wheel_car"));
                    tr.setStatus_use_unuse_car(rs.getString("status_use_unuse_car"));
                    tr.setComment(rs.getString("comment"));
                    tr.setTechnique_date(rs.getString("technique_date"));
                    tr.setTechnique_date_per_month(rs.getString("technique_date_per_month"));
                    tr.setTechnique_date_status(rs.getString("technique_date_status"));
                    return tr;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportHeader> listReportHeader(ReportHeaderReq reportHeaderReq) {
        try
        {
        String SQL="select a.LAHUD_POYLOD,b.H_VICIVLE_NUMBER,c.F_CARD_NO,b.H_VICIVLE_BRANCH,COUNT(*) totalRow ,\n" +
                "cast(replace(PRIECENUMNUN, ',', '') as unsigned)*SAINUMMUN AS PRIECENUMNUN,\n" +
                "FEETOTAL,sum(cast(replace(STAFF_BIALIENG, ',', '') as unsigned)) as STAFF_BIALIENG,\n" +
                "sum(cast(replace(staff02_payAll, ',', '') as unsigned)) as staff02_payAll\n" +
                "from TB_DETAILS a \n" +
                "inner join TB_HEADER_TRUCK b on b.KEY_ID  =a.HEADER_ID\n" +
                "INNER JOIN TB_FOOTER_TRUCH c ON c.KEY_ID =a.FOOTER_ID \n" +
                "INNER JOIN TB_PERFORMANCE d ON a.LAHUD_POYLOD=d.PERFORMANCEBILLNO \n" +
                "join STAFF f on f.KEY_ID = a.STAFF_ID_NUM1\n" +
                "join STAFF h on h.KEY_ID =a.STAFF_ID_NUM2  " +
                "where OUT_DATE between '"+reportHeaderReq.getStartDate()+"' and '"+reportHeaderReq.getEndDate()+"'\n" +
                "group by b.H_VICIVLE_NUMBER,c.F_CARD_NO,b.H_VICIVLE_BRANCH";
        return EBankJdbcTemplate.query(SQL, new RowMapper<ReportHeader>() {
            @Override
            public ReportHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
                ReportHeader tr =new ReportHeader();
                tr.setLahudPoyLod(rs.getString("LAHUD_POYLOD"));
                tr.setVicicalNo(rs.getString("H_VICIVLE_NUMBER"));
                tr.setFCardNo(rs.getString("F_CARD_NO"));
                tr.setVicicalBranch(rs.getString("H_VICIVLE_BRANCH"));
                tr.setTotalRow(rs.getString("totalRow"));
                tr.setNumMun(rs.getString("PRIECENUMNUN"));
                tr.setFeeTotal(rs.getString("FEETOTAL"));
                tr.setBeeLieng(rs.getString("STAFF_BIALIENG"));
                tr.setBeeLiengAll(rs.getString("staff02_payAll"));
                return tr;
            }
        });
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
