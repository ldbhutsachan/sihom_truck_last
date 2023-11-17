package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.Batery.Batery;
import com.ldb.truck.Model.Login.Batery.BateryReq;
import com.ldb.truck.Model.Login.Batery.BateryRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.staft.stafReq;
import com.ldb.truck.Service.Batery.BeterSerivice;
import com.ldb.truck.Service.MediaUploadServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class BatteryController {
@Autowired
BeterSerivice beterSerivice;
    private static final Logger logger = LogManager.getLogger(BatteryController.class);
    @Autowired
    MediaUploadServiceImpl mediaUploadService;
@CrossOrigin(value = "*")
@PostMapping(value = "/getBateryAll")
    public BateryRes getBateryAll(@RequestBody BateryReq bateryReq){
    BateryRes result = new BateryRes();
    try{
        result = beterSerivice.getBateryAll(bateryReq);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/saveBatery" , consumes = {"multipart/form-data"})
    public Messages saveBatery(
            @RequestParam("files") MultipartFile files
//            @RequestParam(name="files" , required=false) MultipartFile[] files
            ,@RequestParam("batNo") String  batNo
            ,@RequestParam("modalMorfai") String  modalMorfai
            ,@RequestParam("sizeMorfai") String  sizeMorfai
            ,@RequestParam("serviceLife") String  serviceLife
    ){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            BateryReq data = new BateryReq();
            data.setBatNo(batNo);
            data.setModalMorfai(modalMorfai);
            data.setSizeMorfai(sizeMorfai);
            data.setServiceLife(serviceLife);
            logger.error("******file lenght"+files);
            logger.error(data);
            String fileName = "";
            String path="images/batery/";
            List<String> fileNames = new ArrayList<>();
            //============================================================
            if(files == null){
                logger.warn("************* file name is null ****************");
                data.setImageBatery("http://khounkham.com/images/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMedia(file));
                });
                logger.info("Uploaded the files successfully: "+ fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImageBatery(fileName);
            }
            //============================================================
            result = beterSerivice.saveBatery(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
 //========================update data
 @CrossOrigin(origins = "*")
 @PostMapping(value = "/updateBatery" , consumes = {"multipart/form-data"})
 public Messages updateBatery(
        // @RequestParam(name="files" , required=false) MultipartFile[] files
        @RequestParam(name="files" , required=false) MultipartFile[] files
         ,@RequestParam("batNo") String  batNo
         ,@RequestParam("modalMorfai") String  modalMorfai
         ,@RequestParam("sizeMorfai") String  sizeMorfai
         ,@RequestParam("serviceLife") String  serviceLife
         ,@RequestParam("imageBatery") String imageBatery
          ,@RequestParam("keyId") String keyId
 ){
     Date date = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
     String namefile = formatter.format(date);
     Messages result = new Messages();
     try {
         BateryReq data = new BateryReq();
         data.setBatNo(batNo);
         data.setModalMorfai(modalMorfai);
         data.setSizeMorfai(sizeMorfai);
         data.setServiceLife(serviceLife);
         data.setKeyId(keyId);
         data.setImageBatery(imageBatery);
         logger.error("******file lenght"+files);
         logger.error(data);
         String fileName = "";
         List<String> fileNames = new ArrayList<>();

         if(files == null){
             logger.warn("************* file name is null ****************");
             data.setImageBatery("1");
         }else {
             Arrays.asList(files).stream().forEach(file -> {
                 fileNames.add(mediaUploadService.uploadMedia(file));
             });
             logger.info("Uploaded the files successfully: " + fileNames );
             fileName = StringUtils.join(fileNames, ',');
             data.setImageBatery(fileName);
         }
         result = beterSerivice.updateBatery(data);
     }catch (Exception e){
         e.printStackTrace();
         result.setStatus("01");
         result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
         return  result;
     }
     return  result;
 }
//===============================del data
//DelBatery
@CrossOrigin(origins = "*")
@PostMapping(value = "/DelBatery")
public Messages updateBatery(@RequestBody BateryReq bateryReq){
    logger.info("==========================>Del <=============================");
    Messages result = new Messages();
    try {
        result = beterSerivice.DelBatery(bateryReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
}
