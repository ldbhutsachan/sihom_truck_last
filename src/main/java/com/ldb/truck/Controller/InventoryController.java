package com.ldb.truck.Controller;
import com.ldb.truck.Dao.Inventory.InventoryDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.FuelStation.FuelStationReq;
import com.ldb.truck.Model.Login.FuelStation.FuelStationRes;
import com.ldb.truck.Model.Login.Inventory.Items.ItemReq;
import com.ldb.truck.Model.Login.Inventory.Items.ItemRes;
import com.ldb.truck.Model.Login.Inventory.Items.Items;
import com.ldb.truck.Model.Login.Inventory.Shops.ShopReq;
import com.ldb.truck.Model.Login.Inventory.Shops.ShopRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Service.Inventory.InventoryService;
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
public class InventoryController {
    private static final Logger log = LogManager.getLogger(InventoryController.class);
    @Autowired
    InventoryService inventoryService;
    @Autowired
    private MediaUploadService mediaUploadService;
// ===============================================Shop==================================================
    // show List Shop
@CrossOrigin(origins = "*")
@PostMapping("/ListShops.service")
public ShopRes ListShops(@RequestBody ShopReq shopReq ){
    ShopRes result = new ShopRes();
    try {
        result = inventoryService.ListShops(shopReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
// Insert Shops
@CrossOrigin(origins = "*")
@PostMapping("/InsertShop.service")
public ShopRes InsertShops (@RequestBody ShopReq shopReq ){
    ShopRes result = new ShopRes();
    try {
        result = inventoryService.InsertShops(shopReq);

    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    // Update Shops
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateShop.service")
    public ShopRes UpdateShop (@RequestBody ShopReq shopReq ){
        ShopRes result = new ShopRes();
        try {
            result = inventoryService.UpdateShops(shopReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
// delete shop
@CrossOrigin(origins = "*")
@PostMapping("/DelShops.service")
public ShopRes DelShop (@RequestBody ShopReq shopReq) {

    ShopRes result = new ShopRes();
    try {
        result = inventoryService.DelShops(shopReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
// ===============================================Shop==================================================
    //-- Show Items
    @CrossOrigin(origins = "*")
    @PostMapping("/ListItems.service")
    public ItemRes listItems(@RequestBody ItemReq itemReq ){
        ItemRes result = new ItemRes();
        try {
            result = inventoryService.ListItems(itemReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //--delete item
    @CrossOrigin(origins = "*")
    @PostMapping("/DelItem.service")
    public ItemRes DelItem (@RequestBody ItemReq itemReq) {

        ItemRes result = new ItemRes();
        try {
            result = inventoryService.DelItem(itemReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
// insert item
@CrossOrigin(origins = "*")
@PostMapping(value = "/insertItems.service" , consumes = {"multipart/form-data"})
public Messages saveVicicleHeader(
        @RequestParam("files") MultipartFile files,
        @RequestParam("itemName") String  itemName,
        @RequestParam("unit") String  unit,
        @RequestParam("unit_price") String  unit_price,
        @RequestParam("qty") Integer  qty,
        @RequestParam("toKen") String  toKen){
        log.info("===================================Insert Items==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        ItemReq data = new ItemReq();
        data.setItemName(itemName);
        data.setUnit(unit);
        data.setUnit_price(unit_price);
        data.setQty(qty);
        data.setToKen(toKen);
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
        result = inventoryService.saveItems(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
// update item
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateItem.service" , consumes = {"multipart/form-data"})
    public Messages updateItems(
            @RequestParam(name="files" , required=false) MultipartFile[] files,
            @RequestParam("item_id") Integer item_id,
            @RequestParam("itemName") String  itemName,
            @RequestParam("unit") String  unit,
            @RequestParam("unit_price") String  unit_price,
            @RequestParam("qty") Integer  qty,
            @RequestParam("toKen") String  toKen,
            @RequestParam("img") String  img

    ){
        log.info("===================================update Item==================================================");

        Messages result = new Messages();
        try {
            ItemReq data = new ItemReq();
            data.setItem_id(item_id);
            data.setItemName(itemName);
            data.setUnit(unit);
            data.setUnit_price(unit_price);
            data.setQty(qty);
            data.setToKen(toKen);
            data.setImg(img);
            log.error("******file lenght"+files);
            log.info("files:==="+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if (files == null ){
                log.warn("************* file name is null ****************");
                data.setImg("1");
            }else {
                log.warn("************* file name no null ****************");
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediacar(file));
                });
                fileName = StringUtils.join(fileNames, ',');
                data.setImg(fileName);
            }
            result = inventoryService.UpdateItems(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
}
