package com.ldb.truck.Controller;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeReq;
import com.ldb.truck.Model.Login.AssetsOffice.AssetsOfficeRes;
import com.ldb.truck.Model.Login.CarOffice.CarOfficeReq;
import com.ldb.truck.Model.Login.CarOffice.CarOfficeRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Service.AssetsOfficeService.AssetsOfficeService;
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
public class AssetOfficeController {
    @Autowired
    AssetsOfficeService assetsOfficeService;
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    private MediaUploadService mediaUploadService;

    //insert assset
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/insertAssetsOffice.service" , consumes = {"multipart/form-data"})
    public Messages InsertAssetsOffice(
            @RequestParam("files") MultipartFile files,
            @RequestParam("code") String  code,
            @RequestParam("name") String  name,
            @RequestParam("group_type") String  group_type,
            @RequestParam("owner") String owner,
            @RequestParam("qty") String qty,
            @RequestParam("currency") String currency,
            @RequestParam("department") String department,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("location_room") String location_room,
            @RequestParam("date_getin") String date_getin,
            @RequestParam("unit") String unit,
            @RequestParam("colors") String colors,
            @RequestParam("price") String price,
            @RequestParam("toKen") String toKen,
            @RequestParam("life_service") String life_service,
            @RequestParam("dateExpire") String dateExpire,
            @RequestParam("branch_id") String branch_id

    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            AssetsOfficeReq data = new AssetsOfficeReq();
            data.setCode(code);
            data.setName(name);
            data.setGroup_type(group_type);
            data.setOwner(owner);
            data.setQty(qty);
            data.setCurrency(currency);
            data.setDepartment(department);
            data.setBrand(brand);
            data.setModel(model);
            data.setLocation_room(location_room);
            data.setDate_getin(date_getin);
            data.setUnit(unit);
            data.setColors(colors);
            data.setPrice(price);
            data.setToKen(toKen);
            data.setLife_service(life_service);
            data.setDateExpire(dateExpire);
            data.setBranch_id(branch_id);
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
            result = assetsOfficeService.InsertAssetsOfficeService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    // update asset
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/UpdateAssetOffice.service" , consumes = {"multipart/form-data"})
    public Messages UpdateCarOffice(
            @RequestParam("files") MultipartFile files,
            @RequestParam("code") String  code,
            @RequestParam("key_id") String  key_id,
            @RequestParam("name") String  name,
            @RequestParam("group_type") String  group_type,
            @RequestParam("owner") String owner,
            @RequestParam("qty") String qty,
            @RequestParam("currency") String currency,
            @RequestParam("department") String department,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("location_room") String location_room,
            @RequestParam("date_getin") String date_getin,
            @RequestParam("unit") String unit,
            @RequestParam("colors") String colors,
            @RequestParam("price") String price,
            @RequestParam("toKen") String toKen,
            @RequestParam("life_service") String life_service,
            @RequestParam("dateExpire") String dateExpire,
            @RequestParam("branch_id") String branch_id

    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            AssetsOfficeReq data = new AssetsOfficeReq();
            data.setKey_id(key_id);
            data.setCode(code);
            data.setName(name);
            data.setGroup_type(group_type);
            data.setOwner(owner);
            data.setQty(qty);
            data.setCurrency(currency);
            data.setDepartment(department);
            data.setBrand(brand);
            data.setModel(model);
            data.setLocation_room(location_room);
            data.setDate_getin(date_getin);
            data.setUnit(unit);
            data.setColors(colors);
            data.setPrice(price);
            data.setToKen(toKen);
            data.setLife_service(life_service);
            data.setDateExpire(dateExpire);
            data.setBranch_id(branch_id);

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
            result = assetsOfficeService.UpdateAssetOfficeService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດອັບເດດໄດ້");
            return  result;
        }
        return  result;
    }

    //List show asset
    @CrossOrigin(origins = "*")
    @PostMapping("/listAssetsOffice.service")
    public AssetsOfficeRes listAssetsOffice(@RequestBody AssetsOfficeReq assetsOfficeReq){
        AssetsOfficeRes result = new AssetsOfficeRes();
        try {
            result = assetsOfficeService.listAssetOfficeService(assetsOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    //show asset detail
    @CrossOrigin(origins = "*")
    @PostMapping("/listAssetsOfficeDetail.service")
    public AssetsOfficeRes listAssetsOfficeDetailById(@RequestBody  AssetsOfficeReq assetsOfficeReq){
        AssetsOfficeRes result = new AssetsOfficeRes();
        try {
            result = assetsOfficeService.listAssetOfficeServiceDetailById(assetsOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

//    del asset
    @CrossOrigin(origins = "*")
    @PostMapping("/DelAssetsByID.service")
    public AssetsOfficeRes DelAssetsByID (@RequestBody AssetsOfficeReq assetsOfficeReq) {

        AssetsOfficeRes result = new AssetsOfficeRes();
        try {
            result = assetsOfficeService.DelAssetsByID(assetsOfficeReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

}
