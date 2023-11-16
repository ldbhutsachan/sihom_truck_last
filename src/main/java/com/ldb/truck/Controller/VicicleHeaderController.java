package com.ldb.truck.Controller;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.staft.stafReq;
import com.ldb.truck.Service.VicicleHeaderService.VicicleHeaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class VicicleHeaderController {
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    VicicleHeaderService vicicleHeaderService;
    @Autowired
    private MediaUploadService mediaUploadService;
    @CrossOrigin(origins = "*")
    @PostMapping("/listVicicleHeader.service")
    public VicicleHeaderRes listVicicleHeader(){
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.listVicicleHeader();
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
    public VicicleHeaderRes listVicicleHeaderCombo1(){
        VicicleHeaderRes result = new VicicleHeaderRes();
        try {
            result = vicicleHeaderService.listVicicleHeaderCombo1();
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
        @RequestParam("exCarDate") String  exCarDate,
        @RequestParam("exCarColor") String  exCarColor,
        @RequestParam("exHangMar") String  exHangMar,
        @RequestParam("batNo") String  batNo
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
        data.setExCarDate(exCarDate);
        data.setExCarColor(exCarColor);
        data.setExHangMar(exHangMar);
        data.setBatNo(batNo);
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
            @RequestParam("exCarDate") String  exCarDate,
            @RequestParam("exCarColor") String  exCarColor,
            @RequestParam("exHangMar") String  exHangMar,
            @RequestParam("batNo") String  batNo,
            @RequestParam("imageTruck") String  imageTruck


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
            data.setExCarDate(exCarDate);
            data.setExCarColor(exCarColor);
            data.setExHangMar(exHangMar);
            data.setBatNo(batNo);
           // data.setImageTruck(imageTruck);
            log.error("******file lenght"+files);
            log.info("files:==="+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if (files == null ){
                log.warn("************* file name is null ****************");
                data.setImageTruck("101");
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
}
