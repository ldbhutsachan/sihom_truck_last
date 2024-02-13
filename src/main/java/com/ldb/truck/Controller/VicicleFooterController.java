package com.ldb.truck.Controller;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Service.VicicleFooterService.VicicleFooterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class VicicleFooterController {
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    VicicleFooterService vicicleFooterService;

    @Autowired
    private MediaUploadService mediaUploadService;
    @CrossOrigin(origins = "*")
    @PostMapping("/listViciclefooter.service")
    public VicicleFooterRes listVicicleHeader(){
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            result = vicicleFooterService.listViciclefooter();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/listViciclefooterCombo1.service")
    public VicicleFooterRes listVicicleHeaderFooterBox1(){
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            result = vicicleFooterService.listViciclefootercombox1();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/listViciclefooterByID.service")
    public VicicleFooterRes listVicicleHeaderByID(@RequestBody  VicicleFooterReq vicicleFooterReq){
        log.info("ll:"+vicicleFooterReq.getKey_id());
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            result = vicicleFooterService.listViciclefooterbyID(vicicleFooterReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/saveVicicleFooterByID.service" )
    public Messages saveVicicleHeader(@RequestParam("f_BRANCH") String f_BRANCH,
                                      @RequestParam("f_YEAR") String f_YEAR,
                                      @RequestParam("f_DATEEXPRIED") String f_DATEEXPRIED,
                                      @RequestParam("f_CARD_NO") String f_CARD_NO,
                                      @RequestParam("f_LEKKUNZEE") String f_LEKKUNZEE,
                                      @RequestParam("f_PAO") String f_PAO,
                                      @RequestParam("f_KORKC") String f_KORKC ,
                                      @RequestParam("f_TOLOCKTU") String f_TOLOCKTU,
                                      @RequestParam("f_SO") String f_SO,
                                      @RequestParam("f_PABUD") String f_PABUD ,
                                      @RequestParam("f_FAIKHANG") String f_FAIKHANG,
                                      @RequestParam("f_FAITHAIY") String f_FAITHAIY,
                                      @RequestParam("f_BGTHOM") String f_BGTHOM,
                                      @RequestParam("f_GALATY_NO") String f_GALATY_NO,
                                      @RequestParam("f_GALATY_DEP") String f_GALATY_DEP,
                                      @RequestParam("f_CAR_TYPE") String f_CAR_TYPE,
                                      @RequestParam("l_TRIES_1") String l_TRIES_1,
                                      @RequestParam("l_TRIES_2") String l_TRIES_2,
                                      @RequestParam("l_TRIES_3") String l_TRIES_3,
                                      @RequestParam("l_TRIES_4") String l_TRIES_4,
                                      @RequestParam("l_TRIES_5") String l_TRIES_5,
                                      @RequestParam("l_TRIES_6") String l_TRIES_6,
                                      @RequestParam("l_TRIES_7") String l_TRIES_7,
                                      @RequestParam("l_TRIES_8") String l_TRIES_8,
                                      @RequestParam("l_TRIES_DATE_1") String l_TRIES_DATE_1,
                                      @RequestParam("l_TRIES_DATE_2") String l_TRIES_DATE_2,
                                      @RequestParam("l_TRIES_DATE_3") String l_TRIES_DATE_3,
                                      @RequestParam("l_TRIES_DATE_4") String l_TRIES_DATE_4,
                                      @RequestParam("l_TRIES_DATE_5") String l_TRIES_DATE_5,
                                      @RequestParam("l_TRIES_DATE_6") String l_TRIES_DATE_6,
                                      @RequestParam("l_TRIES_DATE_7") String l_TRIES_DATE_7,
                                      @RequestParam("l_TRIES_DATE_8") String l_TRIES_DATE_8,
                                      @RequestParam("l_TRIES_KM_1") String l_TRIES_KM_1,
                                      @RequestParam("l_TRIES_KM_2") String l_TRIES_KM_2,
                                      @RequestParam("l_TRIES_KM_3") String l_TRIES_KM_3,
                                      @RequestParam("l_TRIES_KM_4") String l_TRIES_KM_4,
                                      @RequestParam("l_TRIES_KM_5") String l_TRIES_KM_5,
                                      @RequestParam("l_TRIES_KM_6") String l_TRIES_KM_6,
                                      @RequestParam("l_TRIES_KM_7") String l_TRIES_KM_7,
                                      @RequestParam("l_TRIES_KM_8") String l_TRIES_KM_8,
                                      @RequestParam("r_TRIES_1") String r_TRIES_1,
                                      @RequestParam("r_TRIES_2") String r_TRIES_2,
                                      @RequestParam("r_TRIES_3") String r_TRIES_3,
                                      @RequestParam("r_TRIES_4") String r_TRIES_4,
                                      @RequestParam("r_TRIES_5") String r_TRIES_5,
                                      @RequestParam("r_TRIES_6") String r_TRIES_6,
                                      @RequestParam("r_TRIES_7") String r_TRIES_7,
                                      @RequestParam("r_TRIES_8") String r_TRIES_8,
                                      @RequestParam("r_TRIES_DATE_1") String r_TRIES_DATE_1,
                                      @RequestParam("r_TRIES_DATE_2") String r_TRIES_DATE_2,
                                      @RequestParam("r_TRIES_DATE_3") String r_TRIES_DATE_3,
                                      @RequestParam("r_TRIES_DATE_4") String r_TRIES_DATE_4,
                                      @RequestParam("r_TRIES_DATE_5") String r_TRIES_DATE_5,
                                      @RequestParam("r_TRIES_DATE_6") String r_TRIES_DATE_6,
                                      @RequestParam("r_TRIES_DATE_7") String r_TRIES_DATE_7,
                                      @RequestParam("r_TRIES_DATE_8") String r_TRIES_DATE_8,
                                      @RequestParam("r_TRIES_KM_1") String r_TRIES_KM_1,
                                      @RequestParam("r_TRIES_KM_2") String r_TRIES_KM_2,
                                      @RequestParam("r_TRIES_KM_3") String r_TRIES_KM_3,
                                      @RequestParam("r_TRIES_KM_4") String r_TRIES_KM_4,
                                      @RequestParam("r_TRIES_KM_5") String r_TRIES_KM_5,
                                      @RequestParam("r_TRIES_KM_6") String r_TRIES_KM_6,
                                      @RequestParam("r_TRIES_KM_7") String r_TRIES_KM_7,
                                      @RequestParam("r_TRIES_KM_8") String r_TRIES_KM_8,
//                                      @RequestParam("f_STATUS") String f_STATUS,
                                     // @RequestParam("HIS_RESON") String his_REASON,
                                      @RequestParam("f_KM1") String f_KM1,
                                      @RequestParam("f_KM2") String f_KM2,
                                      @RequestParam("f_KM3") String f_KM3,
                                      @RequestParam("f_KM4") String f_KM4,
                                      @RequestParam("f_KM5") String f_KM5,
                                      @RequestParam("f_KM6") String f_KM6,
                                      @RequestParam("f_KM7") String f_KM7,
                                      @RequestParam("f_KM8") String f_KM8,
                                      @RequestParam("f_KM9") String f_KM9,
                                      @RequestParam("f_KM10") String f_KM10,
                                      @RequestParam("f_KM11") String f_KM11,
                                      @RequestParam("f_KM12") String f_KM12,
                                      @RequestParam("f_KM13") String f_KM13,
                                      @RequestParam("f_KM14") String f_KM14,
                                      @RequestParam("f_KM15") String f_KM15,
                                      @RequestParam("f_KM16") String f_KM16,
                                      @RequestParam("f_KM_LL1") String f_KM_LL1,
                                      @RequestParam("f_KM_LL2") String f_KM_LL2,
                                      @RequestParam("f_KM_LL3") String f_KM_LL3,
                                      @RequestParam("f_KM_LL4") String f_KM_LL4,
                                      @RequestParam("f_KM_LL9") String f_KM_LL9,
                                      @RequestParam("f_KM_LL10") String f_KM_LL10,
                                      @RequestParam("f_KM_LL11") String f_KM_LL11,
                                      @RequestParam("f_KM_LL12") String f_KM_LL12,
                                      @RequestParam("f_KM_LL5") String f_KM_LL5,
                                      @RequestParam("f_KM_LL6") String f_KM_LL6,
                                      @RequestParam("f_KM_LL7") String f_KM_LL7,
                                      @RequestParam("f_KM_LL8") String f_KM_LL8,
                                      @RequestParam("f_KM_LL13") String f_KM_LL13,
                                      @RequestParam("f_KM_LL14") String f_KM_LL14,
                                      @RequestParam("f_KM_LL15") String f_KM_LL15,
                                      @RequestParam("f_KM_LL16") String f_KM_LL16,
                                      @RequestParam("files") MultipartFile files
                                      ) {
        Messages result = new Messages();
        try{
        VicicleFooterReq data = new VicicleFooterReq();
            data.setF_BRANCH(f_BRANCH);
            data.setF_YEAR(f_YEAR  );
            data.setF_CAR_TYPE(f_CAR_TYPE);
            data.setF_DATEEXPRIED(f_DATEEXPRIED);
            data.setF_CARD_NO(f_CARD_NO );
            data.setF_LEKKUNZEE(f_LEKKUNZEE  );
            data.setF_PAO(f_PAO   );
            data.setF_KORKC(f_KORKC );
            data.setF_TOLOCKTU  ( f_TOLOCKTU);
            data.setF_SO  ( f_SO    );
            data.setF_PABUD ( f_PABUD );
            data.setF_FAIKHANG ( f_FAIKHANG);
            data.setF_FAITHAIY  ( f_FAITHAIY);
            data.setF_BGTHOM  ( f_BGTHOM);
            data.setF_GALATY_NO  ( f_GALATY_NO  );
            data.setF_GALATY_DEP  ( f_GALATY_DEP );
            data.setL_TRIES_1  ( l_TRIES_1);
            data.setL_TRIES_2  ( l_TRIES_2);
            data.setL_TRIES_3  ( l_TRIES_3);
            data.setL_TRIES_4  ( l_TRIES_4);
            data.setL_TRIES_5 ( l_TRIES_5);
            data.setL_TRIES_6  ( l_TRIES_6);
            data.setL_TRIES_7  ( l_TRIES_7);
            data.setL_TRIES_8  ( l_TRIES_8);
            data.setL_TRIES_DATE_1  ( l_TRIES_DATE_1);
            data.setL_TRIES_DATE_2  ( l_TRIES_DATE_2);
            data.setL_TRIES_DATE_3  ( l_TRIES_DATE_3);
            data.setL_TRIES_DATE_4  ( l_TRIES_DATE_4);
            data.setL_TRIES_DATE_5  ( l_TRIES_DATE_5);
            data.setL_TRIES_DATE_6  ( l_TRIES_DATE_6);
            data.setL_TRIES_DATE_7  ( l_TRIES_DATE_7);
            data.setL_TRIES_DATE_8  ( l_TRIES_DATE_8);
            data.setL_TRIES_KM_1  ( l_TRIES_KM_1  );
            data.setL_TRIES_KM_2  ( l_TRIES_KM_2  );
            data.setL_TRIES_KM_3  ( l_TRIES_KM_3  );
            data.setL_TRIES_KM_4  ( l_TRIES_KM_4  );
            data.setL_TRIES_KM_5 ( l_TRIES_KM_5  );
            data.setL_TRIES_KM_6  ( l_TRIES_KM_6  );
            data.setL_TRIES_KM_7  ( l_TRIES_KM_7  );
            data.setL_TRIES_KM_8  ( l_TRIES_KM_8  );
            data.setR_TRIES_1  ( r_TRIES_1);
            data.setR_TRIES_2  ( r_TRIES_2);
            data.setR_TRIES_3  ( r_TRIES_3);
            data.setR_TRIES_4  ( r_TRIES_4);
            data.setR_TRIES_5  ( r_TRIES_5);
            data.setR_TRIES_6  ( r_TRIES_6);
            data.setR_TRIES_7  ( r_TRIES_7);
            data.setR_TRIES_8  ( r_TRIES_8);
            data.setR_TRIES_DATE_1 ( r_TRIES_DATE_1);
            data.setR_TRIES_DATE_2  ( r_TRIES_DATE_2);
            data.setR_TRIES_DATE_3  ( r_TRIES_DATE_3);
            data.setR_TRIES_DATE_4  ( r_TRIES_DATE_4);
            data.setR_TRIES_DATE_5 ( r_TRIES_DATE_5);
            data.setR_TRIES_DATE_6  ( r_TRIES_DATE_6);
            data.setR_TRIES_DATE_7  ( r_TRIES_DATE_7);
            data.setR_TRIES_DATE_8  ( r_TRIES_DATE_8);
            data.setR_TRIES_KM_1  ( r_TRIES_KM_1  );
            data.setR_TRIES_KM_2  ( r_TRIES_KM_2  );
            data.setR_TRIES_KM_3  ( r_TRIES_KM_3  );
            data.setR_TRIES_KM_4  ( r_TRIES_KM_4  );
            data.setR_TRIES_KM_5  ( r_TRIES_KM_5  );
            data.setR_TRIES_KM_6  ( r_TRIES_KM_6  );
            data.setR_TRIES_KM_7  ( r_TRIES_KM_7  );
            data.setR_TRIES_KM_8  ( r_TRIES_KM_8  );
//            data.setF_STATUS  ( f_STATUS);
           // data.setHis_REASON  ( his_REASON);
            data.setF_KM1  ( f_KM1);
            data.setF_KM2  ( f_KM2);
            data.setF_KM3  ( f_KM3);
            data.setF_KM4  ( f_KM4);
            data.setF_KM5  ( f_KM5);
            data.setF_KM6  ( f_KM6);
            data.setF_KM7  ( f_KM7);
            data.setF_KM8  ( f_KM8);
            data.setF_KM9  ( f_KM9);
            data.setF_KM10  ( f_KM10);
            data.setF_KM11  ( f_KM11);
            data.setF_KM12  ( f_KM12);
            data.setF_KM13  ( f_KM13);
            data.setF_KM14  ( f_KM14);
            data.setF_KM15  ( f_KM15);
            data.setF_KM16  ( f_KM16);
            data.setF_KM_LL1  ( f_KM_LL1);
            data.setF_KM_LL2  ( f_KM_LL2);
            data.setF_KM_LL3  ( f_KM_LL3);
            data.setF_KM_LL4  ( f_KM_LL4);
            data.setF_KM_LL9  ( f_KM_LL9);
            data.setF_KM_LL10  ( f_KM_LL10);
            data.setF_KM_LL11  ( f_KM_LL11);
            data.setF_KM_LL12  ( f_KM_LL12);
            data.setF_KM_LL5  ( f_KM_LL5);
            data.setF_KM_LL6  ( f_KM_LL6);
            data.setF_KM_LL7  ( f_KM_LL7);
            data.setF_KM_LL8  ( f_KM_LL8);
            data.setF_KM_LL13  ( f_KM_LL13);
            data.setF_KM_LL14  ( f_KM_LL14);
            data.setF_KM_LL15  ( f_KM_LL15);
            data.setF_KM_LL16  ( f_KM_LL16);

        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setImgFootTruck("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadMediacar(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setImgFootTruck(fileName);
        }
        result = vicicleFooterService.saveVicicleFooter(data);
    }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
    }
    return result;
    }
    //update data
    @CrossOrigin(origins = "*")
    @PostMapping("/updateVicicleFooter.service")
    public Messages updateVicicleHeader( @RequestParam("f_BRANCH") String f_BRANCH,
                                        @RequestParam("f_YEAR") String f_YEAR,
                                        @RequestParam("f_DATEEXPRIED") String f_DATEEXPRIED,
                                        @RequestParam("f_CARD_NO") String f_CARD_NO,
                                        @RequestParam("f_LEKKUNZEE") String f_LEKKUNZEE,
                                        @RequestParam("f_PAO") String f_PAO,
                                        @RequestParam("f_KORKC") String f_KORKC ,
                                        @RequestParam("f_TOLOCKTU") String f_TOLOCKTU,
                                        @RequestParam("f_SO") String f_SO,
                                        @RequestParam("f_PABUD") String f_PABUD ,
                                        @RequestParam("f_FAIKHANG") String f_FAIKHANG,
                                        @RequestParam("f_FAITHAIY") String f_FAITHAIY,
                                        @RequestParam("f_BGTHOM") String f_BGTHOM,
                                        @RequestParam("f_GALATY_NO") String f_GALATY_NO,
                                        @RequestParam("f_GALATY_DEP") String f_GALATY_DEP,
                                        @RequestParam("f_CAR_TYPE") String f_CAR_TYPE,
                                        @RequestParam("l_TRIES_1") String l_TRIES_1,
                                        @RequestParam("l_TRIES_2") String l_TRIES_2,
                                        @RequestParam("l_TRIES_3") String l_TRIES_3,
                                        @RequestParam("l_TRIES_4") String l_TRIES_4,
                                        @RequestParam("l_TRIES_5") String l_TRIES_5,
                                        @RequestParam("l_TRIES_6") String l_TRIES_6,
                                        @RequestParam("l_TRIES_7") String l_TRIES_7,
                                        @RequestParam("l_TRIES_8") String l_TRIES_8,
                                        @RequestParam("l_TRIES_DATE_1") String l_TRIES_DATE_1,
                                        @RequestParam("l_TRIES_DATE_2") String l_TRIES_DATE_2,
                                        @RequestParam("l_TRIES_DATE_3") String l_TRIES_DATE_3,
                                        @RequestParam("l_TRIES_DATE_4") String l_TRIES_DATE_4,
                                        @RequestParam("l_TRIES_DATE_5") String l_TRIES_DATE_5,
                                        @RequestParam("l_TRIES_DATE_6") String l_TRIES_DATE_6,
                                        @RequestParam("l_TRIES_DATE_7") String l_TRIES_DATE_7,
                                        @RequestParam("l_TRIES_DATE_8") String l_TRIES_DATE_8,
                                        @RequestParam("l_TRIES_KM_1") String l_TRIES_KM_1,
                                        @RequestParam("l_TRIES_KM_2") String l_TRIES_KM_2,
                                        @RequestParam("l_TRIES_KM_3") String l_TRIES_KM_3,
                                        @RequestParam("l_TRIES_KM_4") String l_TRIES_KM_4,
                                        @RequestParam("l_TRIES_KM_5") String l_TRIES_KM_5,
                                        @RequestParam("l_TRIES_KM_6") String l_TRIES_KM_6,
                                        @RequestParam("l_TRIES_KM_7") String l_TRIES_KM_7,
                                        @RequestParam("l_TRIES_KM_8") String l_TRIES_KM_8,
                                        @RequestParam("r_TRIES_1") String r_TRIES_1,
                                        @RequestParam("r_TRIES_2") String r_TRIES_2,
                                        @RequestParam("r_TRIES_3") String r_TRIES_3,
                                        @RequestParam("r_TRIES_4") String r_TRIES_4,
                                        @RequestParam("r_TRIES_5") String r_TRIES_5,
                                        @RequestParam("r_TRIES_6") String r_TRIES_6,
                                        @RequestParam("r_TRIES_7") String r_TRIES_7,
                                        @RequestParam("r_TRIES_8") String r_TRIES_8,
                                        @RequestParam("r_TRIES_DATE_1") String r_TRIES_DATE_1,
                                        @RequestParam("r_TRIES_DATE_2") String r_TRIES_DATE_2,
                                        @RequestParam("r_TRIES_DATE_3") String r_TRIES_DATE_3,
                                        @RequestParam("r_TRIES_DATE_4") String r_TRIES_DATE_4,
                                        @RequestParam("r_TRIES_DATE_5") String r_TRIES_DATE_5,
                                        @RequestParam("r_TRIES_DATE_6") String r_TRIES_DATE_6,
                                        @RequestParam("r_TRIES_DATE_7") String r_TRIES_DATE_7,
                                        @RequestParam("r_TRIES_DATE_8") String r_TRIES_DATE_8,
                                        @RequestParam("r_TRIES_KM_1") String r_TRIES_KM_1,
                                        @RequestParam("r_TRIES_KM_2") String r_TRIES_KM_2,
                                        @RequestParam("r_TRIES_KM_3") String r_TRIES_KM_3,
                                        @RequestParam("r_TRIES_KM_4") String r_TRIES_KM_4,
                                        @RequestParam("r_TRIES_KM_5") String r_TRIES_KM_5,
                                        @RequestParam("r_TRIES_KM_6") String r_TRIES_KM_6,
                                        @RequestParam("r_TRIES_KM_7") String r_TRIES_KM_7,
                                        @RequestParam("r_TRIES_KM_8") String r_TRIES_KM_8,
                                        @RequestParam("f_STATUS") String f_STATUS,
                                        @RequestParam("HIS_RESON") String his_REASON,
                                        @RequestParam("f_KM1") String f_KM1,
                                        @RequestParam("f_KM2") String f_KM2,
                                        @RequestParam("f_KM3") String f_KM3,
                                        @RequestParam("f_KM4") String f_KM4,
                                        @RequestParam("f_KM5") String f_KM5,
                                        @RequestParam("f_KM6") String f_KM6,
                                        @RequestParam("f_KM7") String f_KM7,
                                        @RequestParam("f_KM8") String f_KM8,
                                        @RequestParam("f_KM9") String f_KM9,
                                        @RequestParam("f_KM10") String f_KM10,
                                        @RequestParam("f_KM11") String f_KM11,
                                        @RequestParam("f_KM12") String f_KM12,
                                        @RequestParam("f_KM13") String f_KM13,
                                        @RequestParam("f_KM14") String f_KM14,
                                        @RequestParam("f_KM15") String f_KM15,
                                        @RequestParam("f_KM16") String f_KM16,
                                        @RequestParam("f_KM_LL1") String f_KM_LL1,
                                        @RequestParam("f_KM_LL2") String f_KM_LL2,
                                        @RequestParam("f_KM_LL3") String f_KM_LL3,
                                        @RequestParam("f_KM_LL4") String f_KM_LL4,
                                        @RequestParam("f_KM_LL9") String f_KM_LL9,
                                        @RequestParam("f_KM_LL10") String f_KM_LL10,
                                        @RequestParam("f_KM_LL11") String f_KM_LL11,
                                        @RequestParam("f_KM_LL12") String f_KM_LL12,
                                        @RequestParam("f_KM_LL5") String f_KM_LL5,
                                        @RequestParam("f_KM_LL6") String f_KM_LL6,
                                        @RequestParam("f_KM_LL7") String f_KM_LL7,
                                        @RequestParam("f_KM_LL8") String f_KM_LL8,
                                        @RequestParam("f_KM_LL13") String f_KM_LL13,
                                        @RequestParam("f_KM_LL14") String f_KM_LL14,
                                        @RequestParam("f_KM_LL15") String f_KM_LL15,
                                        @RequestParam("f_KM_LL16") String f_KM_LL16,
                                        @RequestParam("imgFootTruck") String imgFootTruck,
                                         @RequestParam(name="files" , required=false) MultipartFile[] files,
                                        @RequestParam("key_id") String key_id
                                                ) {
        Messages result = new Messages();
        try {
            VicicleFooterReq data = new VicicleFooterReq();
            data.setF_BRANCH  ( f_BRANCH);
            data.setF_YEAR ( f_YEAR  );
            data.setF_CAR_TYPE  ( f_CAR_TYPE);
            data.setF_DATEEXPRIED  ( f_DATEEXPRIED);
            data.setF_CARD_NO  ( f_CARD_NO );
            data.setF_LEKKUNZEE  ( f_LEKKUNZEE  );
            data.setF_PAO ( f_PAO   );
            data.setF_KORKC  ( f_KORKC );
            data.setF_TOLOCKTU  ( f_TOLOCKTU);
            data.setF_SO  ( f_SO    );
            data.setF_PABUD ( f_PABUD );
            data.setF_FAIKHANG ( f_FAIKHANG);
            data.setF_FAITHAIY  ( f_FAITHAIY);
            data.setF_BGTHOM  ( f_BGTHOM);
            data.setF_GALATY_NO  ( f_GALATY_NO  );
            data.setF_GALATY_DEP  ( f_GALATY_DEP );
            data.setL_TRIES_1  ( l_TRIES_1);
            data.setL_TRIES_2  ( l_TRIES_2);
            data.setL_TRIES_3  ( l_TRIES_3);
            data.setL_TRIES_4  ( l_TRIES_4);
            data.setL_TRIES_5 ( l_TRIES_5);
            data.setL_TRIES_6  ( l_TRIES_6);
            data.setL_TRIES_7  ( l_TRIES_7);
            data.setL_TRIES_8  ( l_TRIES_8);
            data.setL_TRIES_DATE_1  ( l_TRIES_DATE_1);
            data.setL_TRIES_DATE_2  ( l_TRIES_DATE_2);
            data.setL_TRIES_DATE_3  ( l_TRIES_DATE_3);
            data.setL_TRIES_DATE_4  ( l_TRIES_DATE_4);
            data.setL_TRIES_DATE_5  ( l_TRIES_DATE_5);
            data.setL_TRIES_DATE_6  ( l_TRIES_DATE_6);
            data.setL_TRIES_DATE_7  ( l_TRIES_DATE_7);
            data.setL_TRIES_DATE_8  ( l_TRIES_DATE_8);
            data.setL_TRIES_KM_1  ( l_TRIES_KM_1  );
            data.setL_TRIES_KM_2  ( l_TRIES_KM_2  );
            data.setL_TRIES_KM_3  ( l_TRIES_KM_3  );
            data.setL_TRIES_KM_4  ( l_TRIES_KM_4  );
            data.setL_TRIES_KM_5 ( l_TRIES_KM_5  );
            data.setL_TRIES_KM_6  ( l_TRIES_KM_6  );
            data.setL_TRIES_KM_7  ( l_TRIES_KM_7  );
            data.setL_TRIES_KM_8  ( l_TRIES_KM_8  );
            data.setR_TRIES_1  ( r_TRIES_1);
            data.setR_TRIES_2  ( r_TRIES_2);
            data.setR_TRIES_3  ( r_TRIES_3);
            data.setR_TRIES_4  ( r_TRIES_4);
            data.setR_TRIES_5  ( r_TRIES_5);
            data.setR_TRIES_6  ( r_TRIES_6);
            data.setR_TRIES_7  ( r_TRIES_7);
            data.setR_TRIES_8  ( r_TRIES_8);
            data.setR_TRIES_DATE_1 ( r_TRIES_DATE_1);
            data.setR_TRIES_DATE_2  ( r_TRIES_DATE_2);
            data.setR_TRIES_DATE_3  ( r_TRIES_DATE_3);
            data.setR_TRIES_DATE_4  ( r_TRIES_DATE_4);
            data.setR_TRIES_DATE_5 ( r_TRIES_DATE_5);
            data.setR_TRIES_DATE_6  ( r_TRIES_DATE_6);
            data.setR_TRIES_DATE_7  ( r_TRIES_DATE_7);
            data.setR_TRIES_DATE_8  ( r_TRIES_DATE_8);
            data.setR_TRIES_KM_1  ( r_TRIES_KM_1  );
            data.setR_TRIES_KM_2  ( r_TRIES_KM_2  );
            data.setR_TRIES_KM_3  ( r_TRIES_KM_3  );
            data.setR_TRIES_KM_4  ( r_TRIES_KM_4  );
            data.setR_TRIES_KM_5  ( r_TRIES_KM_5  );
            data.setR_TRIES_KM_6  ( r_TRIES_KM_6  );
            data.setR_TRIES_KM_7  ( r_TRIES_KM_7  );
            data.setR_TRIES_KM_8  ( r_TRIES_KM_8  );
            data.setF_STATUS  ( f_STATUS);
            data.setHis_REASON  ( his_REASON);
            data.setF_KM1  ( f_KM1);
            data.setF_KM2  ( f_KM2);
            data.setF_KM3  ( f_KM3);
            data.setF_KM4  ( f_KM4);
            data.setF_KM5  ( f_KM5);
            data.setF_KM6  ( f_KM6);
            data.setF_KM7  ( f_KM7);
            data.setF_KM8  ( f_KM8);
            data.setF_KM9  ( f_KM9);
            data.setF_KM10  ( f_KM10);
            data.setF_KM11  ( f_KM11);
            data.setF_KM12  ( f_KM12);
            data.setF_KM13  ( f_KM13);
            data.setF_KM14  ( f_KM14);
            data.setF_KM15  ( f_KM15);
            data.setF_KM16  ( f_KM16);
            data.setF_KM_LL1  ( f_KM_LL1);
            data.setF_KM_LL2  ( f_KM_LL2);
            data.setF_KM_LL3  ( f_KM_LL3);
            data.setF_KM_LL4  ( f_KM_LL4);
            data.setF_KM_LL9  ( f_KM_LL9);
            data.setF_KM_LL10  ( f_KM_LL10);
            data.setF_KM_LL11  ( f_KM_LL11);
            data.setF_KM_LL12  ( f_KM_LL12);
            data.setF_KM_LL5  ( f_KM_LL5);
            data.setF_KM_LL6  ( f_KM_LL6);
            data.setF_KM_LL7  ( f_KM_LL7);
            data.setF_KM_LL8  ( f_KM_LL8);
            data.setF_KM_LL13  ( f_KM_LL13);
            data.setF_KM_LL14  ( f_KM_LL14);
            data.setF_KM_LL15  ( f_KM_LL15);
            data.setF_KM_LL16  ( f_KM_LL16);
            data.setImgFootTruck(imgFootTruck);
            data.setKey_id(key_id);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                log.warn("************* file name is null ****************");
                data.setImgFootTruck("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediacar(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImgFootTruck(fileName);
            }
            result = vicicleFooterService.updateVicicleFooter(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/DelVicicleFooter.service")
    public VicicleFooterRes DelVicicleHeader(@RequestBody  VicicleFooterReq vicicleFooterReq) {
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            result = vicicleFooterService.delVicicleFooterByID(vicicleFooterReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
}
