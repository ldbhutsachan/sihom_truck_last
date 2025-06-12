package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Entity.Brand.BrandEntity;
import com.ldb.truck.Entity.Brand.BrandReq;
import com.ldb.truck.Entity.Item.ItemEntity;
import com.ldb.truck.Entity.Item.viewItemEntity;
import com.ldb.truck.Entity.ItemType.ItemTypeEntity;
import com.ldb.truck.Entity.PlaceStock.PlaceStockEntity;
import com.ldb.truck.Entity.PlaceStock.PlaceStockEntityReq;
import com.ldb.truck.Entity.Supplier.SupplierEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Service.MediaUploadServiceImpl;
import com.ldb.truck.Service.StockService.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${base_url}")
public class ItemController {
    @Autowired
    MediaUploadServiceImpl mediaUploadService;
    @Autowired
    BrandServiceImpl brandService;
    @Autowired
    ItemTypeServiceImpl itemTypeService;
    @Autowired
    SupplierServiceImpl supplierService;
    @Autowired
    PlaceStockService placeStockService;
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    ProfileDao profileDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/getBand.service")
    public ResponseEntity<?> getBrand (@RequestBody BrandReq brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = brandService.getBrand(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
  @CrossOrigin(origins = "*")
    @PostMapping("/saveBrand.service")
    public ResponseEntity<?> saveBand (@RequestBody BrandEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = brandService.saveBand(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateBand.service")
    public ResponseEntity<?> updateBand (@RequestBody BrandEntity brandReq){
        log.info("=====start =============update");
        DataResponse response  = new DataResponse();
        try {
            response = brandService.updateBand(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delBand.service")
    public ResponseEntity<?> delBand (@RequestBody BrandEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = brandService.updateStatus(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //=====itemType
    @CrossOrigin(origins = "*")
    @PostMapping("/getItemType.service")
    public ResponseEntity<?> getItemType (@RequestBody ItemTypeEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = itemTypeService.getItemType(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveItemType.service")
    public ResponseEntity<?> saveItemType (@RequestBody ItemTypeEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = itemTypeService.saveItemType(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateItemType.service")
    public ResponseEntity<?> updateItemType (@RequestBody ItemTypeEntity brandReq){
        log.info("=====start =============update");
        DataResponse response  = new DataResponse();
        try {
            response = itemTypeService.updateItemType(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delItemType.service")
    public ResponseEntity<?> delItemType (@RequestBody ItemTypeEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = itemTypeService.delItemType(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //=====supplier
    @CrossOrigin(origins = "*")
    @PostMapping("/getSupplier.service")
    public ResponseEntity<?> getSupplier (@RequestBody SupplierEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = supplierService.getSupplier(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveSupplier.service")
    public ResponseEntity<?> saveSupplier (@RequestBody SupplierEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = supplierService.saveSupplier(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Store Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateSupplier.service")
    public ResponseEntity<?> SupplierUpdate (@RequestBody SupplierEntity brandReq){
        log.info("=====start =============update");
        DataResponse response  = new DataResponse();
        try {
            response = supplierService.updateSupplier(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delSupplier.service")
    public ResponseEntity<?> delSupplier (@RequestBody SupplierEntity brandReq){
        log.info("=====start =============update");
        DataResponse response  = new DataResponse();
        try {
            response = supplierService.delSupplier(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("update Data is Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //-----stockHouse
    @CrossOrigin(origins = "*")
    @PostMapping("/getStockHouse.service")
    public ResponseEntity<?> getStockHouse (@RequestBody PlaceStockEntityReq brandReq){
        log.info("=============start getStockHouse ============");
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            String role = userProfiles.get(0).getRole();
            String branchNo = userProfiles.get(0).getBranchNo();
            PlaceStockEntityReq reqBody = new PlaceStockEntityReq();
            reqBody.setRole(role);
            reqBody.setUserId(userId);
            reqBody.setKhId(reqBody.getKhId());
            reqBody.setBrandNo(branchNo);
            response = placeStockService.getPlaceStockHouse(reqBody);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getPlaceStockHouseByKey.service")
    public ResponseEntity<?> getPlaceStockHouseByKey (@RequestBody PlaceStockEntityReq brandReq){
        log.info("=============start getPlaceStockHouseByKey ============");
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            String role = userProfiles.get(0).getRole();
            String branchNo = userProfiles.get(0).getBranchNo();
            PlaceStockEntityReq reqBody = new PlaceStockEntityReq();
            reqBody.setRole(role);
            reqBody.setUserId(userId);
            reqBody.setKhId(reqBody.getKhId());
            reqBody.setBrandNo(branchNo);
            response = placeStockService.getPlaceStockHouseByKey(reqBody);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getBranch.service")
    public ResponseEntity<?> getBranch (){
        log.info("=============start getBranch ============");
        DataResponse response  = new DataResponse();
        try {
            response = placeStockService.getBranch();
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/saveStockHouse.service")
    public ResponseEntity<?> saveStockHouse (@RequestBody PlaceStockEntityReq brandReq){
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            response = placeStockService.storePlaceStockHouse(brandReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }@CrossOrigin(origins = "*")
    @PostMapping("/updatePlaceStock.service")
    public ResponseEntity<?> updatePlaceStock (@RequestBody PlaceStockEntityReq brandReq){
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            response = placeStockService.updatePlaceStock(brandReq,userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }@CrossOrigin(origins = "*")
    @PostMapping("/delPlaceStock.service")
    public ResponseEntity<?> delPlaceStock (@RequestBody PlaceStockEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            response = placeStockService.delPlaceStock(brandReq);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getViewItemInventory.service")
    public ResponseEntity<?> getViewItemInventory(@RequestBody viewItemEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            String role = userProfiles.get(0).getRole();
            String branchno = userProfiles.get(0).getBranchNo();
            response = itemService.getViewItemInventory(brandReq,userId,role,branchno);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getItemList.service")
    public ResponseEntity<?> getItemList(@RequestBody viewItemEntity brandReq){
        DataResponse response  = new DataResponse();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(brandReq.getToKen());
            if (userProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String userId = userProfiles.get(0).getUserId();
            String role = userProfiles.get(0).getRole();
            String branchno = userProfiles.get(0).getBranchNo();
            response = itemService.getItemList(brandReq,userId,role,branchno);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/insertItem.service")
    public ResponseEntity<DataResponse>insertItem(@ApiParam(
            name = "Body Request",
            value = "JSON body request to check information",
            required = true) @Valid  @RequestParam(name="files" , required=false) MultipartFile files
            ,@RequestParam("brandId") Integer  brandId
            ,@RequestParam("supplierId") Integer  supplierId
            ,@RequestParam("barcode") String  barcode
            ,@RequestParam("item_name") String  item_name
            ,@RequestParam("unit") Integer  unit
            ,@RequestParam("size") Integer  size
            ,@RequestParam("currency") String  currency
            ,@RequestParam("exchangeRate") Integer  exchangeRate
            ,@RequestParam("galatyStartDate") String  galatyStartDate
            ,@RequestParam("galatyEndDate") String  galatyEndDate
            ,@RequestParam("galatyAmt") Integer  galatyAmt
            ,@RequestParam("qty") Integer  qty
            ,@RequestParam("price") Float  price
            ,@RequestParam("toKen") String  toKen
            ,@RequestParam("itemtypeid") Integer  itemtypeid
            ,@RequestParam("houseid") Integer  houseid
            ,HttpServletRequest request) throws Exception {
        log.info("\t\t --> save item Request controller >>>>>>>>>>>>>>>>>>>>>>");
        String clientIpAddress = request.getRemoteAddr();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        log.info("Client IP Address = " + clientIpAddress);
        // Set the date
        String inputDate02 = galatyStartDate;
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = inputFormat.parse(inputDate02);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy-MM-dd");
        String outputDate = outputFormat.format(date);

        String inputDate03 = galatyEndDate;
        SimpleDateFormat inputFormat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date date3 = inputFormat.parse(inputDate03);
        SimpleDateFormat outputFormat3 = new SimpleDateFormat("yy-MM-dd");
        String outputDate3 = outputFormat3.format(date3);

        List<Profile> userIn = profileDao.getProfileInfoByToken(toKen);
        log.info("show=================UserNo:"+userIn.get(0).getUserId());
        log.info("show=================UserBname:"+userIn.get(0).getBranchName());
        log.info("show=================Role:"+userIn.get(0).getRole());
        log.info("show================BranchNo:"+userIn.get(0).getBranchNo());

        //================================================================
        String userId = userIn.get(0).getUserId();
        String branchNo = userIn.get(0).getBranchNo();
        DataResponse dataResponse = new DataResponse();
        ItemEntity data = new ItemEntity();
        data.setBrandId(brandId);
        data.setSupplierId(supplierId);
        data.setBarcode(barcode);
        data.setItem_name(item_name);
        data.setUnit(unit);
        data.setSize(size);
        data.setCurrency(currency);
        data.setExchangeRate(exchangeRate);
        data.setGalatyStartDate(outputDate);
        data.setGalatyEndDate(outputDate3);
        data.setGalatyAmt(galatyAmt);
        data.setQty(qty);
        data.setPrice(price);
        data.setMakeById(userId);
        data.setBranchNo(branchNo);
        data.setMakeDate(new Date());
        data.setBrandId(brandId);
        data.setItemtypeid(itemtypeid);
        data.setHouseid(houseid);
        //====================check doc file before upload this==============
        String fileName = "";
        String path="images/batery/";
        List<String> fileNames = new ArrayList<>();
        //============================================================
        String pathAdd="http://khounkham.com/images/batery/";
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setImage("http://khounkham.com/images/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadMedia(file));
            });
            log.info("Uploaded the files successfully: "+ fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setImage(pathAdd+fileName);
        }
        dataResponse = itemService.saveItem(data);
        //====================save your info sharing==============================
        return new ResponseEntity<>(dataResponse , HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateItemInventory.service")
    public ResponseEntity<DataResponse>updateItem(@ApiParam(
            name = "Body Request",
            value = "JSON body request to check information",
            required = true) @Valid  @RequestParam(name="files" , required=false) MultipartFile files
            ,@RequestParam("item_id") Long  item_id
            ,@RequestParam("brandId") Integer  brandId
            ,@RequestParam("supplierId") Integer  supplierId
            ,@RequestParam("barcode") String  barcode
            ,@RequestParam("item_name") String  item_name
            ,@RequestParam("unit") Integer  unit
            ,@RequestParam("size") Integer  size
            ,@RequestParam("currency") String  currency
            ,@RequestParam("exchangeRate") Integer  exchangeRate
            ,@RequestParam("galatyStartDate") String  galatyStartDate
            ,@RequestParam("galatyEndDate") String  galatyEndDate
            ,@RequestParam("galatyAmt") Integer  galatyAmt
            ,@RequestParam("qty") Integer  qty
            ,@RequestParam("price") Float  price
            ,@RequestParam("toKen") String  toKen
            ,@RequestParam("itemtypeid") Integer  itemtypeid
            ,@RequestParam("houseid") Integer  houseid
            ,HttpServletRequest request) throws Exception {
        log.info("\t\t --> save item Request controller >>>>>>>>>>>>>>>>>>>>>>");
        String clientIpAddress = request.getRemoteAddr();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        log.info("Client IP Address = " + clientIpAddress);
        // Set the date
        String inputDate02 = galatyStartDate;
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = inputFormat.parse(inputDate02);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy-MM-dd");
        String outputDate = outputFormat.format(date);

        String inputDate03 = galatyEndDate;
        SimpleDateFormat inputFormat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date date3 = inputFormat.parse(inputDate03);
        SimpleDateFormat outputFormat3 = new SimpleDateFormat("yy-MM-dd");
        String outputDate3 = outputFormat3.format(date3);

        List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        String branchNo = userProfiles.get(0).getBranchNo();
        DataResponse dataResponse = new DataResponse();
        ItemEntity data = new ItemEntity();
        data.setItemId(item_id);
        data.setBrandId(brandId);
        data.setSupplierId(supplierId);
        data.setBarcode(barcode);
        data.setItem_name(item_name);
        data.setUnit(unit);
        data.setSize(size);
        data.setCurrency(currency);
        data.setExchangeRate(exchangeRate);
        data.setGalatyStartDate(outputDate);
        data.setGalatyEndDate(outputDate3);
        data.setGalatyAmt(galatyAmt);
        data.setQty(qty);
        data.setPrice(price);
        data.setApproveBy(userId);
        data.setBranchNo(branchNo);
        data.setApproveDate(new Date());
        data.setBrandId(brandId);
        data.setItemtypeid(itemtypeid);
        data.setHouseid(houseid);
        //====================check doc file before upload this==============
        // Handle file upload
        String imageUrl = "http://khounkham.com/images/image.jpg"; // Default image
        if (files != null && !files.isEmpty()) {
            String uploadedFileName = mediaUploadService.uploadMedia(files);
            imageUrl = "http://khounkham.com/images/batery/" + uploadedFileName;
        }
        data.setImage(imageUrl);

        dataResponse = itemService.updateItem(data);
        //====================save your info sharing==============================
        return new ResponseEntity<>(dataResponse , HttpStatus.OK);
    }
}
