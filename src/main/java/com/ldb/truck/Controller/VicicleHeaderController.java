package com.ldb.truck.Controller;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.CarOffice.CarOfficeReq;
import com.ldb.truck.Model.Login.CarOffice.CarOfficeRes;
import com.ldb.truck.Model.Login.CarOffice.CarPaidRes;
import com.ldb.truck.Model.Login.CarOffice.PaidCarDaoReq;
import com.ldb.truck.Model.Login.Dept_Must_Receive.DeptMustReceivedRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.staft.stafReq;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
//================================  api whatapp
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
//================================  api whatapp
@RestController
@RequestMapping("${base_url}")
public class VicicleHeaderController {
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    VicicleHeaderService vicicleHeaderService;
    @Autowired
    private MediaUploadService mediaUploadService;

    //    test api whatsapp
//    @CrossOrigin(origins = "*")
//    @PostMapping("/whatsappApi.service")
//    public class Apitest {
//        // Find your Account Sid and Token at twilio.com/console
//        public static final String ACCOUNT_SID = "AC0f41da64f12a09afba5f8e84efa72eda";
//        public static final String AUTH_TOKEN = "83f8118f3b5dbc1ece33af5b8b9a1d28";
//        public static void main(String[] args) {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber("whatsapp:+8562091056567"),
//                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
//                    "kuyy"
//            System.out.println(message.getSid());
//        }
//    }
    @CrossOrigin(origins = "*")
    @PostMapping("/listVicicleHeader.service")
    public VicicleHeaderRes listVicicleHeader(@RequestBody VicicleHeaderReq vicicleHeaderReq){
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.listVicicleHeader(vicicleHeaderReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //---
    @CrossOrigin(origins = "*")
    @PostMapping("/listVicicleHeaderCombo1.service")
    public VicicleHeaderRes listVicicleHeaderCombo1(@RequestBody VicicleHeaderReq vicicleHeaderReq){
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.listVicicleHeaderCombo1(vicicleHeaderReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //
    @CrossOrigin(origins = "*")
    @PostMapping("/listVicicleHeaderByID.service")
    public VicicleHeaderRes listVicicleHeaderByID(@RequestBody  VicicleHeaderReq vicicleHeaderReq){
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.listVicicleHeaderByID(vicicleHeaderReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // List Car office
    @CrossOrigin(origins = "*")
    @PostMapping("/listCarOffice.service")
    public CarOfficeRes listCarsOffice(@RequestBody  CarOfficeReq carOfficeReq){
        CarOfficeRes result = new CarOfficeRes();
        try {
            result = vicicleHeaderService.listCarOfficeService(carOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // list lod t jaiy kha dao history
    @CrossOrigin(origins = "*")
    @PostMapping("/listCarThatPaid.service")
    public CarPaidRes listCarThatPaid(@RequestBody  CarOfficeReq carOfficeReq){
        CarPaidRes result = new CarPaidRes();
        try {
            result = vicicleHeaderService.listCarDaoPaidService(carOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //list show ny lod office
    @CrossOrigin(origins = "*")
    @PostMapping("/listCarOfficeLodDao.service")
    public CarOfficeRes listCarsOfficeDaoCar(@RequestBody  CarOfficeReq carOfficeReq){
        CarOfficeRes result = new CarOfficeRes();
        try {
            result = vicicleHeaderService.listDaoCarOfficeService(carOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // detail car office
    @CrossOrigin(origins = "*")
    @PostMapping("/listCarOfficeDetailById.service")
    public CarOfficeRes listCarsOfficeDetailById(@RequestBody  CarOfficeReq carOfficeReq){
        CarOfficeRes result = new CarOfficeRes();
        try {
            result = vicicleHeaderService.listCarOfficeServiceDetailById(carOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    //--delete
    @CrossOrigin(origins = "*")
    @PostMapping("/DelVicicleHeaderByID.service")
    public VicicleHeaderRes DelVicicleHeaderByID (@RequestBody VicicleHeaderReq vicicleHeaderReq) {

        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.DelVicicleHeaderByID(vicicleHeaderReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // delete car office
    @CrossOrigin(origins = "*")
    @PostMapping("/DelCarOffice.service")
    public CarOfficeRes DelCarOffice (@RequestBody CarOfficeReq carOfficeReq) {

        CarOfficeRes result = new CarOfficeRes();
        try {
            result = vicicleHeaderService.DelCarOfficeService(carOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //save data
//    @CrossOrigin(origins = "*")
//    @PostMapping("/saveVicicleHeaderByID.service")
//    public VicicleHeaderRes saveVicicleHeader(@RequestBody VicicleHeaderReq vicicleHeaderReq) {
//        log.info("=================================================>SaveVicicleHeader service<===============================================");
//        VicicleHeaderRes result = new VicicleHeaderRes();
//        try {
//            result = vicicleHeaderService.saveVicicleHeader(vicicleHeaderReq);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setStatus("01");
//            result.setMessage("exeption");
//            return result;
//        }
//        return result;
//    }
//-================================image truck
@CrossOrigin(origins = "*")
@PostMapping(value = "/saveVicicleHeaderByID.service" , consumes = {"multipart/form-data"})
public Messages saveVicicleHeader(
        @RequestParam("files") MultipartFile files,
        @RequestParam("h_VICIVLE_NUMBER") String  h_VICIVLE_NUMBER,
        @RequestParam("h_VICIVLE_GALATY") String  h_VICIVLE_GALATY,
        @RequestParam("h_VICIVLE_DATE_GALATY") String  h_VICIVLE_DATE_GALATY,
        @RequestParam("h_VICIVLE_TNGLOD") String  h_VICIVLE_TNGLOD,
        @RequestParam("h_VICIVLE_BRANCH") String  h_VICIVLE_BRANCH,
        @RequestParam("h_VICIVLE_YEARLEVEL") String  h_VICIVLE_YEARLEVEL,
        @RequestParam("h_VICIVLE_BRANCHTYPE") String  h_VICIVLE_BRANCHTYPE,
        @RequestParam("h_VICIVLE_DATEEXPRIRE") String  h_VICIVLE_DATEEXPRIRE,
        @RequestParam("h_VICIVLE_LEKJUK") String  h_VICIVLE_LEKJUK,
        @RequestParam("h_VICIVLE_LEKTHUNG") String  h_VICIVLE_LEKTHUNG,
        @RequestParam("h_VICIVLE_GPS") String  h_VICIVLE_GPS,
        @RequestParam("h_VICIVLE_POYPUDNUMFON") String  h_VICIVLE_POYPUDNUMFON,
        @RequestParam("h_VICIVLE_MORFAI") String  h_VICIVLE_MORFAI,
        @RequestParam("h_VICIVLE_BGTOM") String  h_VICIVLE_BGTOM,
        @RequestParam("h_VICIVLE_JANLARK") String  h_VICIVLE_JANLARK,
        @RequestParam("h_VICIVLE_FAINAR") String  h_VICIVLE_FAINAR,
        @RequestParam("h_VICIVLE_FAITHAIY") String  h_VICIVLE_FAITHAIY,
        @RequestParam("h_VICIVLE_FAIYKHANG") String  h_VICIVLE_FAIYKHANG,
        @RequestParam("h_VICIVLE_VENMONGNAR") String  h_VICIVLE_VENMONGNAR,
        @RequestParam("h_VICIVLE_VENMONGLHG") String  h_VICIVLE_VENMONGLHG,
        @RequestParam("h_VICIVLE_VENKHANG") String  h_VICIVLE_VENKHANG,
        @RequestParam("h_VICIVLE_GLASS") String  h_VICIVLE_GLASS,
        @RequestParam("lL_TIRE_NO_1") String  lL_TIRE_NO_1,
        @RequestParam("lL_TIRE_NO_2") String  lL_TIRE_NO_2,
        @RequestParam("lL_TIRE_NO_3") String  lL_TIRE_NO_3,
        @RequestParam("lL_TIRE_NO_4") String  lL_TIRE_NO_4,
        @RequestParam("lL_TIRE_NO_5") String  lL_TIRE_NO_5,
        @RequestParam("lL_TIRE_NO_6") String  lL_TIRE_NO_6,
        @RequestParam("lL_TIRE_NO_7") String  lL_TIRE_NO_7,
        @RequestParam("lL_TIRE_DATE_1") String  lL_TIRE_DATE_1,
        @RequestParam("lL_TIRE_DATE_2") String  lL_TIRE_DATE_2,
        @RequestParam("lL_TIRE_DATE_3") String  lL_TIRE_DATE_3,
        @RequestParam("lL_TIRE_DATE_4") String  lL_TIRE_DATE_4,
        @RequestParam("lL_TIRE_DATE_5") String  lL_TIRE_DATE_5,
        @RequestParam("lL_TIRE_DATE_6") String  lL_TIRE_DATE_6,
        @RequestParam("lL_TIRE_DATE_7") String  lL_TIRE_DATE_7,
        @RequestParam("lL_TIRE_KM_1") String lL_TIRE_KM_1,
        @RequestParam("lL_TIRE_KM_2") String lL_TIRE_KM_2,
        @RequestParam("lL_TIRE_KM_3") String lL_TIRE_KM_3,
        @RequestParam("lL_TIRE_KM_4") String lL_TIRE_KM_4,
        @RequestParam("lL_TIRE_KM_5") String lL_TIRE_KM_5,
        @RequestParam("lL_TIRE_KM_6") String lL_TIRE_KM_6,
        @RequestParam("lL_TIRE_KM_7") String lL_TIRE_KM_7,
        @RequestParam("r_TIRE_NO_1") String r_TIRE_NO_1,
        @RequestParam("r_TIRE_NO_2") String r_TIRE_NO_2,
        @RequestParam("r_TIRE_NO_3") String r_TIRE_NO_3,
        @RequestParam("r_TIRE_NO_4") String r_TIRE_NO_4,
        @RequestParam("r_TIRE_NO_5") String r_TIRE_NO_5,
        @RequestParam("r_TIRE_NO_6") String r_TIRE_NO_6,
        @RequestParam("r_TIRE_NO_7") String  r_TIRE_NO_7,
        @RequestParam("r_TIRE_DATE_1") String  r_TIRE_DATE_1,
        @RequestParam("r_TIRE_DATE_2") String  r_TIRE_DATE_2,
        @RequestParam("r_TIRE_DATE_3") String  r_TIRE_DATE_3,
        @RequestParam("r_TIRE_DATE_4") String  r_TIRE_DATE_4,
        @RequestParam("r_TIRE_DATE_5") String  r_TIRE_DATE_5,
        @RequestParam("r_TIRE_DATE_6") String  r_TIRE_DATE_6,
        @RequestParam("r_TIRE_DATE_7") String  r_TIRE_DATE_7,
        @RequestParam("r_TIRE_KM_1") String  r_TIRE_KM_1,
        @RequestParam("r_TIRE_KM_2") String  r_TIRE_KM_2,
        @RequestParam("r_TIRE_KM_3") String  r_TIRE_KM_3,
        @RequestParam("r_TIRE_KM_4") String  r_TIRE_KM_4,
        @RequestParam("r_TIRE_KM_5") String  r_TIRE_KM_5,
        @RequestParam("r_TIRE_KM_6") String  r_TIRE_KM_6,
        @RequestParam("r_TIRE_KM_7") String  r_TIRE_KM_7,
        @RequestParam("h_LEK") String  h_LEK,
        @RequestParam("kim_KM") String  kim_KM,
        @RequestParam("h_KM1") String  h_KM1,
        @RequestParam("h_KM2") String  h_KM2,
        @RequestParam("h_KM3") String  h_KM3,
        @RequestParam("h_KM4") String  h_KM4,
        @RequestParam("h_KM5") String  h_KM5,
        @RequestParam("h_KM6") String  h_KM6,
        @RequestParam("h_KM7") String  h_KM7,
        @RequestParam("h_KM8") String  h_KM8,
        @RequestParam("h_KM9") String  h_KM9,
        @RequestParam("h_KM10") String  h_KM10,
        @RequestParam("h_KM11") String  h_KM11,
        @RequestParam("h_KM12") String  h_KM12,
        @RequestParam("h_KM13") String  h_KM13,
        @RequestParam("h_KML_1") String  h_KML_1,
        @RequestParam("h_KML_2") String  h_KML_2,
        @RequestParam("h_KML_3") String  h_KML_3,
        @RequestParam("h_KML_4") String  h_KML_4,
        @RequestParam("h_KML_5") String  h_KML_5,
        @RequestParam("h_KML_6") String  h_KML_6,
        @RequestParam("h_KML_7") String  h_KML_7,
        @RequestParam("h_KML_8") String  h_KML_8,
        @RequestParam("h_KML_9") String  h_KML_9,
        @RequestParam("h_KML_10") String  h_KML_10,
        @RequestParam("h_KML_11") String  h_KML_11,
        @RequestParam("h_KML_12") String  h_KML_12,
        @RequestParam("h_KML_13") String  h_KML_13,
        @RequestParam("bat_StartDate") String  bat_StartDate,
        @RequestParam("bat_EndDate") String  bat_EndDate,
//        @RequestParam("bat_StartDate2") String  bat_StartDate2,
//        @RequestParam("bat_EndDate2") String  bat_EndDate2,
        @RequestParam("exCarDate") String  exCarDate,
        @RequestParam("exCarColor") String  exCarColor,
        @RequestParam("exHangMar") String  exHangMar,
        @RequestParam("batNo") String  batNo,
//        @RequestParam("batNo2") String  batNo2,

        @RequestParam("saiystay") String  saiystay,
        @RequestParam("galick") String  galick,
        @RequestParam("leanGia") String  leanGia,
        @RequestParam("leanFuengThaiy") String  leanFuengThaiy,
        @RequestParam("pha_But") String  pha_But,
        @RequestParam("lektungsit") String  lektungsit,
        @RequestParam("dateExTungsit") String  dateExTungsit,
        @RequestParam("date_change_lean") String  date_change_lean,
        @RequestParam("toKen") String  toKen

){
        log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        VicicleHeaderReq data = new VicicleHeaderReq();
        data.setH_VICIVLE_NUMBER(h_VICIVLE_NUMBER);
        data.setH_VICIVLE_GALATY(h_VICIVLE_GALATY);
        data.setH_VICIVLE_DATE_GALATY(h_VICIVLE_DATE_GALATY);
        data.setH_VICIVLE_TNGLOD(h_VICIVLE_TNGLOD);
        data.setH_VICIVLE_BRANCH(h_VICIVLE_BRANCH);
        data.setH_VICIVLE_YEARLEVEL(h_VICIVLE_YEARLEVEL);
        data.setH_VICIVLE_BRANCHTYPE(h_VICIVLE_BRANCHTYPE);
        data.setH_VICIVLE_DATEEXPRIRE(h_VICIVLE_DATEEXPRIRE);
        data.setH_VICIVLE_LEKJUK(h_VICIVLE_LEKJUK);
        data.setH_VICIVLE_LEKTHUNG (h_VICIVLE_LEKTHUNG);
        data.setH_VICIVLE_GPS(h_VICIVLE_GPS);
        data.setH_VICIVLE_POYPUDNUMFON(h_VICIVLE_POYPUDNUMFON);
        data.setH_VICIVLE_MORFAI(h_VICIVLE_MORFAI);
        data.setH_VICIVLE_BGTOM(h_VICIVLE_BGTOM);
        data.setH_VICIVLE_JANLARK(h_VICIVLE_JANLARK);
        data.setH_VICIVLE_FAINAR(h_VICIVLE_FAINAR);
        data.setH_VICIVLE_FAITHAIY(h_VICIVLE_FAITHAIY);
        data.setH_VICIVLE_FAIYKHANG(h_VICIVLE_FAIYKHANG);
        data.setH_VICIVLE_VENMONGNAR(h_VICIVLE_VENMONGNAR);
        data.setH_VICIVLE_VENMONGLHG(h_VICIVLE_VENMONGLHG);
        data.setH_VICIVLE_VENKHANG(h_VICIVLE_VENKHANG);
        data.setH_VICIVLE_GLASS(h_VICIVLE_GLASS);
        data.setLL_TIRE_NO_1(lL_TIRE_NO_1);
        data.setLL_TIRE_NO_2(lL_TIRE_NO_2);
        data.setLL_TIRE_NO_3(lL_TIRE_NO_3);
        data.setLL_TIRE_NO_4(lL_TIRE_NO_4);
        data.setLL_TIRE_NO_5(lL_TIRE_NO_5);
        data.setLL_TIRE_NO_6(lL_TIRE_NO_6);
        data.setLL_TIRE_NO_7(lL_TIRE_NO_7);
        data.setLL_TIRE_DATE_1(lL_TIRE_DATE_1);
        data.setLL_TIRE_DATE_2(lL_TIRE_DATE_2);
        data.setLL_TIRE_DATE_3(lL_TIRE_DATE_3);
        data.setLL_TIRE_DATE_4(lL_TIRE_DATE_4);
        data.setLL_TIRE_DATE_5(lL_TIRE_DATE_5);
        data.setLL_TIRE_DATE_6(lL_TIRE_DATE_6);
        data.setLL_TIRE_DATE_7(lL_TIRE_DATE_7);
        data.setLL_TIRE_KM_1(lL_TIRE_KM_1);
        data.setLL_TIRE_KM_2(lL_TIRE_KM_2);
        data.setLL_TIRE_KM_3(lL_TIRE_KM_3);
        data.setLL_TIRE_KM_4(lL_TIRE_KM_4);
        data.setLL_TIRE_KM_5(lL_TIRE_KM_5);
        data.setLL_TIRE_KM_6(lL_TIRE_KM_6);
        data.setLL_TIRE_KM_7(lL_TIRE_KM_7);
        data.setR_TIRE_NO_1(r_TIRE_NO_1);
        data.setR_TIRE_NO_2(r_TIRE_NO_2);
        data.setR_TIRE_NO_3(r_TIRE_NO_3);
        data.setR_TIRE_NO_4(r_TIRE_NO_4);
        data.setR_TIRE_NO_5(r_TIRE_NO_5);
        data.setR_TIRE_NO_6(r_TIRE_NO_6);
        data.setR_TIRE_NO_7(r_TIRE_NO_7);
        data.setR_TIRE_DATE_1(r_TIRE_DATE_1);
        data.setR_TIRE_DATE_2(r_TIRE_DATE_2);
        data.setR_TIRE_DATE_3(r_TIRE_DATE_3);
        data.setR_TIRE_DATE_4(r_TIRE_DATE_4);
        data.setR_TIRE_DATE_5(r_TIRE_DATE_5);
        data.setR_TIRE_DATE_6(r_TIRE_DATE_6);
        data.setR_TIRE_DATE_7(r_TIRE_DATE_7);
        data.setR_TIRE_KM_1(r_TIRE_KM_1);
        data.setR_TIRE_KM_2(r_TIRE_KM_2);
        data.setR_TIRE_KM_3(r_TIRE_KM_3);
        data.setR_TIRE_KM_4(r_TIRE_KM_4);
        data.setR_TIRE_KM_5(r_TIRE_KM_5);
        data.setR_TIRE_KM_6(r_TIRE_KM_6);
        data.setR_TIRE_KM_7(r_TIRE_KM_7);
        data.setH_LEK_NUMMUNKHG(h_LEK);
        data.setKim_KM(kim_KM);
        data.setH_KM1(h_KM1);
        data.setH_KM2(h_KM2);
        data.setH_KM3(h_KM3);
        data.setH_KM4(h_KM4);
        data.setH_KM5(h_KM5);
        data.setH_KM6(h_KM6);
        data.setH_KM7(h_KM7);
        data.setH_KM8(h_KM8);
        data.setH_KM9(h_KM9);
        data.setH_KM10(h_KM10);
        data.setH_KM11(h_KM11);
        data.setH_KM12(h_KM12);
        data.setH_KM13(h_KM13);
        data.setH_KML_1(h_KML_1);
        data.setH_KML_2(h_KML_2);
        data.setH_KML_3(h_KML_3);
        data.setH_KML_4(h_KML_4);
        data.setH_KML_5(h_KML_5);
        data.setH_KML_6(h_KML_6);
        data.setH_KML_7(h_KML_7);
        data.setH_KML_8(h_KML_8);
        data.setH_KML_9(h_KML_9);
        data.setH_KML_10(h_KML_10);
        data.setH_KML_11(h_KML_11);
        data.setH_KML_12(h_KML_12);
        data.setH_KML_13(h_KML_13);
        data.setBat_StartDate(bat_StartDate);
        data.setBat_EndDate(bat_EndDate);
//        data.setBat_StartDate2(bat_StartDate2);
//        data.setBat_EndDate2(bat_EndDate2);
        data.setExCarDate(exCarDate);
        data.setExCarColor(exCarColor);
        data.setExHangMar(exHangMar);
        data.setBatNo(batNo);
//        data.setBatNo2(batNo2);

        data.setSaiystay(saiystay);
        data.setGalick(galick);
        data.setLeanGia(leanGia);
        data.setLeanFuengThaiy(leanFuengThaiy);
        data.setPha_But(pha_But);
        data.setLektungsit(lektungsit);
        data.setDateExTungsit(dateExTungsit);
        data.setDate_change_lean(date_change_lean);
        data.setToKen(toKen);
        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setImageTruck("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadMediacar(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setImageTruck(fileName);
        }
        result = vicicleHeaderService.saveVicicleHeader(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateVicicleHeaderByID.service" , consumes = {"multipart/form-data"})
    public Messages updateVicicleHeaderByID(
            @RequestParam(name="files" , required=false) MultipartFile[] files,
            @RequestParam("key_id") String key_id,
            @RequestParam("h_VICIVLE_NUMBER") String  h_VICIVLE_NUMBER,
            @RequestParam("h_VICIVLE_GALATY") String  h_VICIVLE_GALATY,
            @RequestParam("h_VICIVLE_DATE_GALATY") String  h_VICIVLE_DATE_GALATY,
            @RequestParam("h_VICIVLE_TNGLOD") String  h_VICIVLE_TNGLOD,
            @RequestParam("h_VICIVLE_BRANCH") String  h_VICIVLE_BRANCH,
            @RequestParam("h_VICIVLE_YEARLEVEL") String  h_VICIVLE_YEARLEVEL,
            @RequestParam("h_VICIVLE_BRANCHTYPE") String  h_VICIVLE_BRANCHTYPE,
            @RequestParam("h_VICIVLE_DATEEXPRIRE") String  h_VICIVLE_DATEEXPRIRE,
            @RequestParam("h_VICIVLE_LEKJUK") String  h_VICIVLE_LEKJUK,
            @RequestParam("h_VICIVLE_LEKTHUNG") String  h_VICIVLE_LEKTHUNG,
            @RequestParam("h_VICIVLE_GPS") String  h_VICIVLE_GPS,
            @RequestParam("h_VICIVLE_POYPUDNUMFON") String  h_VICIVLE_POYPUDNUMFON,
            @RequestParam("h_VICIVLE_MORFAI") String  h_VICIVLE_MORFAI,
            @RequestParam("h_VICIVLE_BGTOM") String  h_VICIVLE_BGTOM,
            @RequestParam("h_VICIVLE_JANLARK") String  h_VICIVLE_JANLARK,
            @RequestParam("h_VICIVLE_FAINAR") String  h_VICIVLE_FAINAR,
            @RequestParam("h_VICIVLE_FAITHAIY") String  h_VICIVLE_FAITHAIY,
            @RequestParam("h_VICIVLE_FAIYKHANG") String  h_VICIVLE_FAIYKHANG,
            @RequestParam("h_VICIVLE_VENMONGNAR") String  h_VICIVLE_VENMONGNAR,
            @RequestParam("h_VICIVLE_VENMONGLHG") String  h_VICIVLE_VENMONGLHG,
            @RequestParam("h_VICIVLE_VENKHANG") String  h_VICIVLE_VENKHANG,
            @RequestParam("h_VICIVLE_GLASS") String  h_VICIVLE_GLASS,
            @RequestParam("lL_TIRE_NO_1") String  lL_TIRE_NO_1,
            @RequestParam("lL_TIRE_NO_2") String  lL_TIRE_NO_2,
            @RequestParam("lL_TIRE_NO_3") String  lL_TIRE_NO_3,
            @RequestParam("lL_TIRE_NO_4") String  lL_TIRE_NO_4,
            @RequestParam("lL_TIRE_NO_5") String  lL_TIRE_NO_5,
            @RequestParam("lL_TIRE_NO_6") String  lL_TIRE_NO_6,
            @RequestParam("lL_TIRE_NO_7") String  lL_TIRE_NO_7,
            @RequestParam("lL_TIRE_DATE_1") String  lL_TIRE_DATE_1,
            @RequestParam("lL_TIRE_DATE_2") String  lL_TIRE_DATE_2,
            @RequestParam("lL_TIRE_DATE_3") String  lL_TIRE_DATE_3,
            @RequestParam("lL_TIRE_DATE_4") String  lL_TIRE_DATE_4,
            @RequestParam("lL_TIRE_DATE_5") String  lL_TIRE_DATE_5,
            @RequestParam("lL_TIRE_DATE_6") String  lL_TIRE_DATE_6,
            @RequestParam("lL_TIRE_DATE_7") String  lL_TIRE_DATE_7,
            @RequestParam("lL_TIRE_KM_1") String lL_TIRE_KM_1,
            @RequestParam("lL_TIRE_KM_2") String lL_TIRE_KM_2,
            @RequestParam("lL_TIRE_KM_3") String lL_TIRE_KM_3,
            @RequestParam("lL_TIRE_KM_4") String lL_TIRE_KM_4,
            @RequestParam("lL_TIRE_KM_5") String lL_TIRE_KM_5,
            @RequestParam("lL_TIRE_KM_6") String lL_TIRE_KM_6,
            @RequestParam("lL_TIRE_KM_7") String lL_TIRE_KM_7,
            @RequestParam("r_TIRE_NO_1") String r_TIRE_NO_1,
            @RequestParam("r_TIRE_NO_2") String r_TIRE_NO_2,
            @RequestParam("r_TIRE_NO_3") String r_TIRE_NO_3,
            @RequestParam("r_TIRE_NO_4") String r_TIRE_NO_4,
            @RequestParam("r_TIRE_NO_5") String r_TIRE_NO_5,
            @RequestParam("r_TIRE_NO_6") String r_TIRE_NO_6,
            @RequestParam("r_TIRE_NO_7") String  r_TIRE_NO_7,
            @RequestParam("r_TIRE_DATE_1") String  r_TIRE_DATE_1,
            @RequestParam("r_TIRE_DATE_2") String  r_TIRE_DATE_2,
            @RequestParam("r_TIRE_DATE_3") String  r_TIRE_DATE_3,
            @RequestParam("r_TIRE_DATE_4") String  r_TIRE_DATE_4,
            @RequestParam("r_TIRE_DATE_5") String  r_TIRE_DATE_5,
            @RequestParam("r_TIRE_DATE_6") String  r_TIRE_DATE_6,
            @RequestParam("r_TIRE_DATE_7") String  r_TIRE_DATE_7,
            @RequestParam("r_TIRE_KM_1") String  r_TIRE_KM_1,
            @RequestParam("r_TIRE_KM_2") String  r_TIRE_KM_2,
            @RequestParam("r_TIRE_KM_3") String  r_TIRE_KM_3,
            @RequestParam("r_TIRE_KM_4") String  r_TIRE_KM_4,
            @RequestParam("r_TIRE_KM_5") String  r_TIRE_KM_5,
            @RequestParam("r_TIRE_KM_6") String  r_TIRE_KM_6,
            @RequestParam("r_TIRE_KM_7") String  r_TIRE_KM_7,
            @RequestParam("h_LEK") String  h_LEK,
            @RequestParam("kim_KM") String  kim_KM,
            @RequestParam("h_KM1") String  h_KM1,
            @RequestParam("h_KM2") String  h_KM2,
            @RequestParam("h_KM3") String  h_KM3,
            @RequestParam("h_KM4") String  h_KM4,
            @RequestParam("h_KM5") String  h_KM5,
            @RequestParam("h_KM6") String  h_KM6,
            @RequestParam("h_KM7") String  h_KM7,
            @RequestParam("h_KM8") String  h_KM8,
            @RequestParam("h_KM9") String  h_KM9,
            @RequestParam("h_KM10") String  h_KM10,
            @RequestParam("h_KM11") String  h_KM11,
            @RequestParam("h_KM12") String  h_KM12,
            @RequestParam("h_KM13") String  h_KM13,
            @RequestParam("h_KML_1") String  h_KML_1,
            @RequestParam("h_KML_2") String  h_KML_2,
            @RequestParam("h_KML_3") String  h_KML_3,
            @RequestParam("h_KML_4") String  h_KML_4,
            @RequestParam("h_KML_5") String  h_KML_5,
            @RequestParam("h_KML_6") String  h_KML_6,
            @RequestParam("h_KML_7") String  h_KML_7,
            @RequestParam("h_KML_8") String  h_KML_8,
            @RequestParam("h_KML_9") String  h_KML_9,
            @RequestParam("h_KML_10") String  h_KML_10,
            @RequestParam("h_KML_11") String  h_KML_11,
            @RequestParam("h_KML_12") String  h_KML_12,
            @RequestParam("h_KML_13") String  h_KML_13,
            @RequestParam("bat_StartDate") String  bat_StartDate,
            @RequestParam("bat_EndDate") String  bat_EndDate,
//            @RequestParam("bat_StartDate2") String  bat_StartDate2,
//            @RequestParam("bat_EndDate2") String  bat_EndDate2,
            @RequestParam("exCarDate") String  exCarDate,
            @RequestParam("exCarColor") String  exCarColor,
            @RequestParam("exHangMar") String  exHangMar,
            @RequestParam("batNo") String  batNo,
//            @RequestParam("batNo2") String  batNo2,
            @RequestParam("imageTruck") String  imageTruck,

            @RequestParam("saiystay") String  saiystay,
            @RequestParam("galick") String  galick,
            @RequestParam("leanGia") String  leanGia,
            @RequestParam("leanFuengThaiy") String  leanFuengThaiy,
            @RequestParam("pha_But") String  pha_But,
            @RequestParam("lektungsit") String  lektungsit,
            @RequestParam("dateExTungsit") String  dateExTungsit,
            @RequestParam("date_change_lean") String  date_change_lean,
            @RequestParam("toKen") String  toKen


    ){
        log.info("===================================update header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            VicicleHeaderReq data = new VicicleHeaderReq();
            data.setKey_id(key_id);
            data.setH_VICIVLE_NUMBER(h_VICIVLE_NUMBER);
            data.setH_VICIVLE_GALATY(h_VICIVLE_GALATY);
            data.setH_VICIVLE_DATE_GALATY(h_VICIVLE_DATE_GALATY);
            data.setH_VICIVLE_TNGLOD(h_VICIVLE_TNGLOD);
            data.setH_VICIVLE_BRANCH(h_VICIVLE_BRANCH);
            data.setH_VICIVLE_YEARLEVEL(h_VICIVLE_YEARLEVEL);
            data.setH_VICIVLE_BRANCHTYPE(h_VICIVLE_BRANCHTYPE);
            data.setH_VICIVLE_DATEEXPRIRE(h_VICIVLE_DATEEXPRIRE);
            data.setH_VICIVLE_LEKJUK(h_VICIVLE_LEKJUK);
            data.setH_VICIVLE_LEKTHUNG (h_VICIVLE_LEKTHUNG);
            data.setH_VICIVLE_GPS(h_VICIVLE_GPS);
            data.setH_VICIVLE_POYPUDNUMFON(h_VICIVLE_POYPUDNUMFON);
            data.setH_VICIVLE_MORFAI(h_VICIVLE_MORFAI);
            data.setH_VICIVLE_BGTOM(h_VICIVLE_BGTOM);
            data.setH_VICIVLE_JANLARK(h_VICIVLE_JANLARK);
            data.setH_VICIVLE_FAINAR(h_VICIVLE_FAINAR);
            data.setH_VICIVLE_FAITHAIY(h_VICIVLE_FAITHAIY);
            data.setH_VICIVLE_FAIYKHANG(h_VICIVLE_FAIYKHANG);
            data.setH_VICIVLE_VENMONGNAR(h_VICIVLE_VENMONGNAR);
            data.setH_VICIVLE_VENMONGLHG(h_VICIVLE_VENMONGLHG);
            data.setH_VICIVLE_VENKHANG(h_VICIVLE_VENKHANG);
            data.setH_VICIVLE_GLASS(h_VICIVLE_GLASS);
            data.setLL_TIRE_NO_1(lL_TIRE_NO_1);
            data.setLL_TIRE_NO_2(lL_TIRE_NO_2);
            data.setLL_TIRE_NO_3(lL_TIRE_NO_3);
            data.setLL_TIRE_NO_4(lL_TIRE_NO_4);
            data.setLL_TIRE_NO_5(lL_TIRE_NO_5);
            data.setLL_TIRE_NO_6(lL_TIRE_NO_6);
            data.setLL_TIRE_NO_7(lL_TIRE_NO_7);
            data.setLL_TIRE_DATE_1(lL_TIRE_DATE_1);
            data.setLL_TIRE_DATE_2(lL_TIRE_DATE_2);
            data.setLL_TIRE_DATE_3(lL_TIRE_DATE_3);
            data.setLL_TIRE_DATE_4(lL_TIRE_DATE_4);
            data.setLL_TIRE_DATE_5(lL_TIRE_DATE_5);
            data.setLL_TIRE_DATE_6(lL_TIRE_DATE_6);
            data.setLL_TIRE_DATE_7(lL_TIRE_DATE_7);
            data.setLL_TIRE_KM_1(lL_TIRE_KM_1);
            data.setLL_TIRE_KM_2(lL_TIRE_KM_2);
            data.setLL_TIRE_KM_3(lL_TIRE_KM_3);
            data.setLL_TIRE_KM_4(lL_TIRE_KM_4);
            data.setLL_TIRE_KM_5(lL_TIRE_KM_5);
            data.setLL_TIRE_KM_6(lL_TIRE_KM_6);
            data.setLL_TIRE_KM_7(lL_TIRE_KM_7);
            data.setR_TIRE_NO_1(r_TIRE_NO_1);
            data.setR_TIRE_NO_2(r_TIRE_NO_2);
            data.setR_TIRE_NO_3(r_TIRE_NO_3);
            data.setR_TIRE_NO_4(r_TIRE_NO_4);
            data.setR_TIRE_NO_5(r_TIRE_NO_5);
            data.setR_TIRE_NO_6(r_TIRE_NO_6);
            data.setR_TIRE_NO_7(r_TIRE_NO_7);
            data.setR_TIRE_DATE_1(r_TIRE_DATE_1);
            data.setR_TIRE_DATE_2(r_TIRE_DATE_2);
            data.setR_TIRE_DATE_3(r_TIRE_DATE_3);
            data.setR_TIRE_DATE_4(r_TIRE_DATE_4);
            data.setR_TIRE_DATE_5(r_TIRE_DATE_5);
            data.setR_TIRE_DATE_6(r_TIRE_DATE_6);
            data.setR_TIRE_DATE_7(r_TIRE_DATE_7);
            data.setR_TIRE_KM_1(r_TIRE_KM_1);
            data.setR_TIRE_KM_2(r_TIRE_KM_2);
            data.setR_TIRE_KM_3(r_TIRE_KM_3);
            data.setR_TIRE_KM_4(r_TIRE_KM_4);
            data.setR_TIRE_KM_5(r_TIRE_KM_5);
            data.setR_TIRE_KM_6(r_TIRE_KM_6);
            data.setR_TIRE_KM_7(r_TIRE_KM_7);
            data.setH_LEK_NUMMUNKHG(h_LEK);
            data.setKim_KM(kim_KM);
            data.setH_KM1(h_KM1);
            data.setH_KM2(h_KM2);
            data.setH_KM3(h_KM3);
            data.setH_KM4(h_KM4);
            data.setH_KM5(h_KM5);
            data.setH_KM6(h_KM6);
            data.setH_KM7(h_KM7);
            data.setH_KM8(h_KM8);
            data.setH_KM9(h_KM9);
            data.setH_KM10(h_KM10);
            data.setH_KM11(h_KM11);
            data.setH_KM12(h_KM12);
            data.setH_KM13(h_KM13);
            data.setH_KML_1(h_KML_1);
            data.setH_KML_2(h_KML_2);
            data.setH_KML_3(h_KML_3);
            data.setH_KML_4(h_KML_4);
            data.setH_KML_5(h_KML_5);
            data.setH_KML_6(h_KML_6);
            data.setH_KML_7(h_KML_7);
            data.setH_KML_8(h_KML_8);
            data.setH_KML_9(h_KML_9);
            data.setH_KML_10(h_KML_10);
            data.setH_KML_11(h_KML_11);
            data.setH_KML_12(h_KML_12);
            data.setH_KML_13(h_KML_13);
            data.setBat_StartDate(bat_StartDate);
            data.setBat_EndDate(bat_EndDate);
//            data.setBat_StartDate2(bat_StartDate2);
//            data.setBat_EndDate2(bat_EndDate2);
            data.setExCarDate(exCarDate);
            data.setExCarColor(exCarColor);
            data.setExHangMar(exHangMar);
            data.setBatNo(batNo);
//            data.setBatNo2(batNo2);

            data.setSaiystay(saiystay);
            data.setGalick(galick);
            data.setLeanGia(leanGia);
            data.setLeanFuengThaiy(leanFuengThaiy);
            data.setPha_But(pha_But);
            data.setLektungsit(lektungsit);
            data.setDateExTungsit(dateExTungsit);
            data.setDate_change_lean(date_change_lean);
            data.setToKen(toKen);
            data.setImageTruck(imageTruck);
            log.error("******file lenght"+files);
            log.info("files:==="+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if (files == null ){
                log.warn("************* file name is null ****************");
                data.setImageTruck("1");
            }else {
                log.warn("************* file name no null ****************");
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediacar(file));
                });
                fileName = StringUtils.join(fileNames, ',');
                data.setImageTruck(fileName);
            }
            result = vicicleHeaderService.updateVicicleHeader(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    // car office
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/insertCarOffice.service" , consumes = {"multipart/form-data"})
    public Messages InsertCarOffice(
            @RequestParam("files") MultipartFile files,
            @RequestParam("license_plate") String  license_plate,
            @RequestParam("battery_code_name") String  battery_code_name,
            @RequestParam("license_plate_end") String  license_plate_end,
            @RequestParam("license_plate_start") String  license_plate_start,
            @RequestParam("car_year") String  car_year,
            @RequestParam("car_type") String  car_type,
            @RequestParam("car_brand") String  car_brand,
            @RequestParam("lekThung") String  lekThung,
            @RequestParam("lekJuk") String  lekJuk,
            @RequestParam("carColor") String  carColor,
            @RequestParam("cc") String  cc,
            @RequestParam("car_mileage_now") String  car_mileage_now,
            @RequestParam("millor_side") String  millor_side,
            @RequestParam("millor_back") String  millor_back,
            @RequestParam("back_light") String  back_light,
            @RequestParam("font_light") String  font_light,
            @RequestParam("insurance_viet_expireDate") String  insurance_viet_expireDate,
            @RequestParam("insurance_Lao_expireDate") String  insurance_Lao_expireDate,
            @RequestParam("insurance_thai_expireDate") String  insurance_thai_expireDate,
            @RequestParam("insurance_thai") String  insurance_thai,
            @RequestParam("insurance_viet") String  insurance_viet,
            @RequestParam("insurance_Lao") String  insurance_Lao,
            @RequestParam("leanGia") String  leanGia,
            @RequestParam("steering_wheel") String  steering_wheel,
            @RequestParam("owner_car") String  owner_car,
            @RequestParam("car_model") String  car_model,
            @RequestParam("oil") String  oil,
            @RequestParam("total_weigh_car") String  total_weigh_car,
            @RequestParam("technic_check_dateEnd") String  technic_check_dateEnd,
            @RequestParam("technic_check_dateStart") String  technic_check_dateStart,
            @RequestParam("serial_wheel_right_font") String  serial_wheel_right_font,
            @RequestParam("serial_wheel_right_back") String  serial_wheel_right_back,
            @RequestParam("serial_wheel_left_font") String  serial_wheel_left_font,
            @RequestParam("serial_wheel_left_back") String  serial_wheel_left_back,
            @RequestParam("sitPosition_amount") String  sitPosition_amount,
            @RequestParam("tall") String  tall,
            @RequestParam("longg") String  longg,
            @RequestParam("wide") String  wide,
            @RequestParam("dao") String  dao,
            @RequestParam("toKen") String  toKen,
            @RequestParam("lean") String  lean,
            @RequestParam("leanFuengThaiy") String  leanFuengThaiy,
            @RequestParam("tungsitnumber") String  tungsitnumber,
            @RequestParam("tungsitDateExpire") String  tungsitDateExpire,
            @RequestParam("serial_tire_second") String  serial_tire_second ,
            @RequestParam("lekmai_next") String  lekmai_next  ,
            @RequestParam("date_change_lean") String  date_change_lean ,
            @RequestParam("date_change_lean_next") String  date_change_lean_next,
            @RequestParam("leanGiaNextday") String  leanGiaNextday
    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            CarOfficeReq data = new CarOfficeReq();
            data.setLicense_plate(license_plate);
            data.setBattery_code_name(battery_code_name);
            data.setLicense_plate_end(license_plate_end);
            data.setLicense_plate_start(license_plate_start);
            data.setCar_year(car_year);
            data.setCarColor(carColor);
            data.setLekThung(lekThung);
            data.setLekJuk(lekJuk);
            data.setCar_brand(car_brand);
            data.setCar_type(car_type);
            data.setCc(cc);
            data.setCar_mileage_now(car_mileage_now);
            data.setMillor_side(millor_side);
            data.setMillor_back(millor_back);
            data.setBack_light(back_light);
            data.setFont_light(font_light);
            data.setInsurance_viet_expireDate(insurance_viet_expireDate);
            data.setInsurance_Lao_expireDate(insurance_Lao_expireDate);
            data.setInsurance_thai_expireDate(insurance_thai_expireDate);
            data.setInsurance_thai(insurance_thai);
            data.setInsurance_viet(insurance_viet);
            data.setInsurance_Lao(insurance_Lao);
            data.setLeanGia(leanGia);
            data.setSteering_wheel(steering_wheel);
            data.setOwner_car(owner_car);
            data.setCar_model(car_model);
            data.setOil(oil);
            data.setTotal_weigh_car(total_weigh_car);
            data.setTechnic_check_dateEnd(technic_check_dateEnd);
            data.setTechnic_check_dateStart(technic_check_dateStart);
            data.setSerial_wheel_right_font(serial_wheel_right_font);
            data.setSerial_wheel_right_back(serial_wheel_right_back);
            data.setSerial_wheel_left_font(serial_wheel_left_font);
            data.setSerial_wheel_left_back(serial_wheel_left_back);
            data.setSitPosition_amount(sitPosition_amount);
            data.setTall(tall);
            data.setLongg(longg);
            data.setWide(wide);
            data.setDao(dao);
            data.setToKen(toKen);
            data.setLean(lean);
            data.setLeanFuengThaiy(leanFuengThaiy);
            data.setTungsitnumber(tungsitnumber);
            data.setTungsitDateExpire(tungsitDateExpire);
            data.setSerial_tire_second(serial_tire_second);
            data.setLekmai_next(lekmai_next);
            data.setDate_change_lean(date_change_lean);
            data.setDate_change_lean_next(date_change_lean_next);
            data.setLeanGiaNextday(leanGiaNextday);
            log.error("******file lenght"+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                log.warn("************* file name is null ****************");
                data.setImg("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediacar(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImg(fileName);
            }
            result = vicicleHeaderService.InsertCarOfficeService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    // jaiy lod dao
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/PayLodDao.service" , consumes = {"multipart/form-data"})
    public Messages InsertCarOffice(
            @RequestParam("files") MultipartFile files,
            @RequestParam("carId") String  carId,
            @RequestParam("cur") String  cur,
            @RequestParam("pricePaid") String  pricePaid,
            @RequestParam("toKen") String  toKen
    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            PaidCarDaoReq data = new PaidCarDaoReq();
            data.setCarId(carId);
            data.setCur(cur);
            data.setPricePaid(pricePaid);
            data.setToKen(toKen);

            log.error("******file lenght"+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                log.warn("************* file name is null ****************");
                data.setPdfFile("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediacar(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setPdfFile(fileName);
            }
            result = vicicleHeaderService.PayLodDaoService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    // download pdf file
    @PostMapping("downloadPdf/{fileName:.+}")
    public ResponseEntity<?> createPDF(
            @ApiParam(
                    name = "Body Request",
                    value = "JSON body request to check information",
                    required = true)
            @Valid
            @PathVariable(name = "fileName") String fileName,
            @Context HttpServletRequest request
    ) throws Exception {
        log.info("\t\t --> Download File PDF controller >>>>>>>>>>>>>>>>>>>>>>");
        String clientIpAddress = request.getRemoteAddr();
        log.info("Client IP Address = " + clientIpAddress);
        log.info("File name = " + fileName);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        String fileName = "ReportBorder_" + sf.format(new Date()) + ".pdf";
//        DataResponse response = generateReportToPdfService.createPdfFile();
//        DataResponse response = new DataResponse();
//        response.setStatus("00");
//        if (response.getStatus().equals("00")) {
        try {
            String fullPath = "src/main/resources/images/car/" + fileName + ".pdf";
            File file = new File(fullPath);

            HttpHeaders header = new HttpHeaders();
//                header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fullPath); // User for View file
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fullPath);    // User for download file
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            log.info("\t\t --> End Download File PDF controller <<<<<<<<<<<<<<<<<<<");
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (Exception e) {
//            throw new NotFoundException(
//                    initializeMessage.errorMessage("File image not found !!", "System can not generate file")
//            );
            log.warn("An error occurs while releasing JMS resources", e);
        }
        return null;
    }

    // update car office
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/UpdateCarOffice.service" , consumes = {"multipart/form-data"})
    public Messages UpdateCarOffice(
//            @RequestParam("files") MultipartFile files,
            @RequestParam("KEY_ID") String KEY_ID,
            @RequestParam("license_plate") String  license_plate,
            @RequestParam("battery_code_name") String  battery_code_name,
            @RequestParam("license_plate_end") String  license_plate_end,
            @RequestParam("license_plate_start") String  license_plate_start,
            @RequestParam("car_year") String  car_year,
            @RequestParam("car_type") String  car_type,
            @RequestParam("car_brand") String  car_brand,
            @RequestParam("lekThung") String  lekThung,
            @RequestParam("lekJuk") String  lekJuk,
            @RequestParam("carColor") String  carColor,
            @RequestParam("cc") String  cc,
            @RequestParam("car_mileage_now") String  car_mileage_now,
            @RequestParam("millor_side") String  millor_side,
            @RequestParam("millor_back") String  millor_back,
            @RequestParam("back_light") String  back_light,
            @RequestParam("font_light") String  font_light,
            @RequestParam("insurance_viet_expireDate") String  insurance_viet_expireDate,
            @RequestParam("insurance_Lao_expireDate") String  insurance_Lao_expireDate,
            @RequestParam("insurance_thai_expireDate") String  insurance_thai_expireDate,
            @RequestParam("insurance_thai") String  insurance_thai,
            @RequestParam("insurance_viet") String  insurance_viet,
            @RequestParam("insurance_Lao") String  insurance_Lao,
            @RequestParam("leanGia") String  leanGia,
            @RequestParam("steering_wheel") String  steering_wheel,
            @RequestParam("owner_car") String  owner_car,
            @RequestParam("car_model") String  car_model,
            @RequestParam("oil") String  oil,
            @RequestParam("total_weigh_car") String  total_weigh_car,
            @RequestParam("technic_check_dateEnd") String  technic_check_dateEnd,
            @RequestParam("technic_check_dateStart") String  technic_check_dateStart,
            @RequestParam("serial_wheel_right_font") String  serial_wheel_right_font,
            @RequestParam("serial_wheel_right_back") String  serial_wheel_right_back,
            @RequestParam("serial_wheel_left_font") String  serial_wheel_left_font,
            @RequestParam("serial_wheel_left_back") String  serial_wheel_left_back,
            @RequestParam("sitPosition_amount") String  sitPosition_amount,
            @RequestParam("tall") String  tall,
            @RequestParam("longg") String  longg,
            @RequestParam("wide") String  wide,
            @RequestParam("dao") String  dao,
            @RequestParam("toKen") String  toKen,
//            @RequestParam("lean") String  lean,
            @RequestParam("leanFuengThaiy") String  leanFuengThaiy,
            @RequestParam("tungsitnumber") String  tungsitnumber,
            @RequestParam("tungsitDateExpire") String  tungsitDateExpire,
            @RequestParam("serial_tire_second") String  serial_tire_second,
            @RequestParam("lekmai_next") String  lekmai_next,
            @RequestParam("date_change_lean") String  date_change_lean ,
            @RequestParam("date_change_lean_next") String  date_change_lean_next,
            @RequestParam("leanGiaNextday") String  leanGiaNextday

    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            CarOfficeReq data = new CarOfficeReq();
            data.setKEY_ID(KEY_ID);
            data.setLicense_plate(license_plate);
            data.setBattery_code_name(battery_code_name);
            data.setLicense_plate_end(license_plate_end);
            data.setLicense_plate_start(license_plate_start);
            data.setCar_year(car_year);
            data.setCarColor(carColor);
            data.setLekThung(lekThung);
            data.setLekJuk(lekJuk);
            data.setCar_brand(car_brand);
            data.setCar_type(car_type);
            data.setCc(cc);
            data.setCar_mileage_now(car_mileage_now);
            data.setMillor_side(millor_side);
            data.setMillor_back(millor_back);
            data.setBack_light(back_light);
            data.setFont_light(font_light);
            data.setInsurance_viet_expireDate(insurance_viet_expireDate);
            data.setInsurance_Lao_expireDate(insurance_Lao_expireDate);
            data.setInsurance_thai_expireDate(insurance_thai_expireDate);
            data.setInsurance_thai(insurance_thai);
            data.setInsurance_viet(insurance_viet);
            data.setInsurance_Lao(insurance_Lao);
            data.setLeanGia(leanGia);
            data.setSteering_wheel(steering_wheel);
            data.setOwner_car(owner_car);
            data.setCar_model(car_model);
            data.setOil(oil);
            data.setTotal_weigh_car(total_weigh_car);
            data.setTechnic_check_dateEnd(technic_check_dateEnd);
            data.setTechnic_check_dateStart(technic_check_dateStart);
            data.setSerial_wheel_right_font(serial_wheel_right_font);
            data.setSerial_wheel_right_back(serial_wheel_right_back);
            data.setSerial_wheel_left_font(serial_wheel_left_font);
            data.setSerial_wheel_left_back(serial_wheel_left_back);
            data.setSitPosition_amount(sitPosition_amount);
            data.setTall(tall);
            data.setLongg(longg);
            data.setWide(wide);
            data.setDao(dao);
            data.setToKen(toKen);
//            data.setLean(lean);
            data.setLeanFuengThaiy(leanFuengThaiy);
            data.setTungsitnumber(tungsitnumber);
            data.setTungsitDateExpire(tungsitDateExpire);
            data.setSerial_tire_second(serial_tire_second);
            data.setLekmai_next(lekmai_next);
            data.setDate_change_lean(date_change_lean);
            data.setDate_change_lean_next(date_change_lean_next);
            data.setLeanGiaNextday(leanGiaNextday);
//            log.error("******file lenght"+files);
//            log.error(data);
//            String fileName = "";
//            List<String> fileNames = new ArrayList<>();
//            if(files == null){
//                log.warn("************* file name is null ****************");
//                data.setImg("http://khounkham.com/images/car/image.jpg");
//            }else {
//                Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
//                });
//                log.info("Uploaded the files successfully: " + fileNames );
//                fileName = StringUtils.join(fileNames, ',');
//                data.setImg(fileName);
//            }
            result = vicicleHeaderService.UpdateCarOfficeService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
//    update notice status
@CrossOrigin(origins = "*")
@PostMapping("/UpdateNoticeCaofficeStatus.service")
public Messages UpdateNoticeCaofficeStatus(@RequestBody CarOfficeReq carOfficeReq){
    Messages result = new Messages();
    try {
        result = vicicleHeaderService.UpdateCarOfficeNoticeStatus(carOfficeReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
}
