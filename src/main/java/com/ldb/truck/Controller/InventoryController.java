package com.ldb.truck.Controller;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.CarOffice.CarOfficeReq;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilReq;
import com.ldb.truck.Model.Login.CarOffice.FillOil.FillOilRes;
import com.ldb.truck.Model.Login.DocumentStorage.DataHoleReq;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReportRes;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReq;
import com.ldb.truck.Model.Login.Inventory.Fix.FixReqListProve.ShowFixRequest;
import com.ldb.truck.Model.Login.Inventory.Fix.FixRes;
import com.ldb.truck.Model.Login.Inventory.Fix.ShowFix;
import com.ldb.truck.Model.Login.Inventory.Items.ItemHisReq;
import com.ldb.truck.Model.Login.Inventory.Items.ItemHisRes;
import com.ldb.truck.Model.Login.Inventory.Items.ItemReq;
import com.ldb.truck.Model.Login.Inventory.Items.ItemRes;
import com.ldb.truck.Model.Login.Inventory.OfferPaper.*;
import com.ldb.truck.Model.Login.Inventory.Old_inventory.OldInventoryReq;
import com.ldb.truck.Model.Login.Inventory.Old_inventory.OldInventoryRes;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.ReportstockReq;
import com.ldb.truck.Model.Login.Inventory.Report_Stock.ReportstockRes;
import com.ldb.truck.Model.Login.Inventory.Shops.*;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Task.TaskReq;
import com.ldb.truck.Model.Login.Task.TaskRes;
import com.ldb.truck.Service.Inventory.InventoryService;
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
    // show List Shops Must Pay
    @CrossOrigin(origins = "*")
    @PostMapping("/ListShopsMustPay.service")
    public ShopsMustPayRes ListShopsMustPay(@RequestBody ShopReq shopReq ){
        ShopsMustPayRes result = new ShopsMustPayRes();
        try {
            result = inventoryService.ListShopsMustPayService(shopReq);
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
//fill oill insert
@CrossOrigin(origins = "*")
@PostMapping("/InsertOil.service")
public FillOilRes InsertOil (@RequestBody FillOilReq fillOilReq ){
    FillOilRes result = new FillOilRes();
    try {
        result = inventoryService.InsertOil(fillOilReq);

    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//show oill fill his
@CrossOrigin(origins = "*")
@PostMapping("/ListHisFillOill.service")
public FillOilRes ListHisFillOill(@RequestBody FillOilReq fillOilReq ){
    FillOilRes result = new FillOilRes();
    try {
        result = inventoryService.ListOilFillService(fillOilReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
// offer paper
@CrossOrigin(origins = "*")
@PostMapping("/saveofferpaper.service")
public OfferpaperRes offerpaper (@RequestBody OfferPaperReq offerPaperReq ){
    OfferpaperRes result = new OfferpaperRes();
    try {
        result = inventoryService.SaveOfferPaper(offerPaperReq);

    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    @CrossOrigin(origins = "*")
    @PostMapping("/deletefferpaper.service")
    public OfferpaperRes deletefferpaper (@RequestBody OfferPaperReq offerPaperReq ){
        OfferpaperRes result = new OfferpaperRes();
        try {
            result = inventoryService.DeleteOfferPaper(offerPaperReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
// fix
    @CrossOrigin(origins = "*")
    @PostMapping("/fix.service")
    public FixRes fixRes (@RequestBody FixReq fixReq){
    FixRes result = new FixRes();
    try {
        result = inventoryService.FixService(fixReq);

    }
    catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
        return result;
    }
    //    insert old inventory
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/InsertOldInventory.service" , consumes = {"multipart/form-data"})
    public Messages InsertDataHole(
            @RequestParam("image_Oldwarehouse") MultipartFile image_Oldwarehouse,
            @RequestParam("itemName_Oldwarehouse") String itemName_Oldwarehouse,
            @RequestParam("description_Oldwarehouse") String  description_Oldwarehouse,
            @RequestParam("importExpirationDate_Oldwarehouse") String  importExpirationDate_Oldwarehouse,
            @RequestParam("price_Oldwarehouse") String  price_Oldwarehouse,
            @RequestParam("vehiclefooter_Oldwarehouse") String  vehiclefooter_Oldwarehouse,
            @RequestParam("vehicle_Oldwarehouse") String  vehicle_Oldwarehouse,
            @RequestParam("qty_Oldwarehouse") String  qty_Oldwarehouse,
            @RequestParam("selectedType_Oldwarehouse") String  selectedType_Oldwarehouse,
            @RequestParam("toKen") String  toKen,
            @RequestParam("branch_id") String  branch_id,
            @RequestParam("cur") String  cur
    ){

        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            OldInventoryReq data = new OldInventoryReq();
            data.setItemName_Oldwarehouse(itemName_Oldwarehouse);
            data.setDescription_Oldwarehouse(description_Oldwarehouse);
            data.setImportExpirationDate_Oldwarehouse(importExpirationDate_Oldwarehouse);
            data.setPrice_Oldwarehouse(price_Oldwarehouse);
            data.setVehiclefooter_Oldwarehouse(vehiclefooter_Oldwarehouse);
            data.setVehicle_Oldwarehouse(vehicle_Oldwarehouse);
            data.setQty_Oldwarehouse(qty_Oldwarehouse);
            data.setSelectedType_Oldwarehouse(selectedType_Oldwarehouse);
            data.setToKen(toKen);
            data.setBranch_id(branch_id);
            data.setCur(cur);

            log.error("******file lenght"+image_Oldwarehouse);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(image_Oldwarehouse == null){
                log.warn("************* file name is null ****************");
                data.setImage_Oldwarehouse("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(image_Oldwarehouse).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                    fileNames.add(mediaUploadService.uploadPDF(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImage_Oldwarehouse(fileName);
            }
            result = inventoryService.InsertOldInventoryService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
//    delete old inventory
@CrossOrigin(origins = "*")
@PostMapping("/DelOldInventory.service")
public OldInventoryRes DelOldInventory (@RequestBody OldInventoryReq oldInventoryReq){
    OldInventoryRes result = new OldInventoryRes();
    try{
        result = inventoryService.DeleteOldInventoryService(oldInventoryReq);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}

//    edit old inventory
@CrossOrigin(origins = "*")
@PostMapping(value = "/UpdateOldInventory.service" , consumes = {"multipart/form-data"})
public Messages UpdateOldInventory(
        @RequestParam("key_id") String  key_id,
        @RequestParam("image_Oldwarehouse") MultipartFile image_Oldwarehouse,
        @RequestParam("itemName_Oldwarehouse") String itemName_Oldwarehouse,
        @RequestParam("description_Oldwarehouse") String  description_Oldwarehouse,
        @RequestParam("importExpirationDate_Oldwarehouse") String  importExpirationDate_Oldwarehouse,
        @RequestParam("price_Oldwarehouse") String  price_Oldwarehouse,
        @RequestParam("vehiclefooter_Oldwarehouse") String  vehiclefooter_Oldwarehouse,
        @RequestParam("vehicle_Oldwarehouse") String  vehicle_Oldwarehouse,
        @RequestParam("qty_Oldwarehouse") String  qty_Oldwarehouse,
        @RequestParam("selectedType_Oldwarehouse") String  selectedType_Oldwarehouse,
        @RequestParam("branch_id") String  branch_id,
        @RequestParam("toKen") String  toKen,
        @RequestParam("cur") String  cur


){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        OldInventoryReq data = new OldInventoryReq();
        data.setKey_id(key_id);
        data.setItemName_Oldwarehouse(itemName_Oldwarehouse);
        data.setDescription_Oldwarehouse(description_Oldwarehouse);
        data.setImportExpirationDate_Oldwarehouse(importExpirationDate_Oldwarehouse);
        data.setPrice_Oldwarehouse(price_Oldwarehouse);
        data.setVehiclefooter_Oldwarehouse(vehiclefooter_Oldwarehouse);
        data.setVehicle_Oldwarehouse(vehicle_Oldwarehouse);
        data.setQty_Oldwarehouse(qty_Oldwarehouse);
        data.setSelectedType_Oldwarehouse(selectedType_Oldwarehouse);
        data.setBranch_id(branch_id);
        data.setToKen(toKen);
        data.setCur(cur);


        log.error("******file lenght"+image_Oldwarehouse);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(image_Oldwarehouse == null){
            log.warn("************* file name is null ****************");
            data.setImage_Oldwarehouse("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(image_Oldwarehouse).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setImage_Oldwarehouse(fileName);
        }
        result = inventoryService.UpdateoldInventoryService(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//    approve fix
@CrossOrigin(origins = "*")
@PostMapping("/approvefix.service")
public FixRes approvefixRes (@RequestBody FixReq fixReq){
    FixRes result = new FixRes();
    try {
        result = inventoryService.approveFixService(fixReq);
    }
    catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//proof fix
@CrossOrigin(origins = "*")
@PostMapping("/proofFixReq.service")
public FixRes proofFixReq (@RequestBody FixReq fixReq){
    FixRes result = new FixRes();
    try {
        result = inventoryService.proofFixReqService(fixReq);
    }
    catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    //show old inventory
    @CrossOrigin(origins = "*")
    @PostMapping("/showOldInventory.service")
    public OldInventoryRes oldInventoryRes(@RequestBody OldInventoryReq oldInventoryReq){
        OldInventoryRes result = new OldInventoryRes();
        try {
            result = inventoryService.showoldInventoryService(oldInventoryReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
//show req fix
@CrossOrigin(origins = "*")
@PostMapping("/showListofFixReq.service")
public ShowFixRequest showListofFixReq(@RequestBody FixReq fixReq){
    ShowFixRequest result = new ShowFixRequest();
    try {
        result = inventoryService.showListofFixReqService(fixReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    // Purchase Order
    @CrossOrigin(origins = "*")
    @PostMapping("/SavePurchaseOrder.service")
    public PurchaseOrderRes offerpaper (@RequestBody PurchaseOrderReq purchaseOrderReq  ){
        PurchaseOrderRes result = new PurchaseOrderRes();
        try {
            result = inventoryService.SavePurchaseOrder(purchaseOrderReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // Move item to stock
    @CrossOrigin(origins = "*")
    @PostMapping("/MoveItemToStock.service")
    public MoveToStockRes MoveItemToStock (@RequestBody MoveToStockReq moveToStockReq ){
        MoveToStockRes result = new MoveToStockRes();
        try {
            result = inventoryService.MoveItemToStockService(moveToStockReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //pay to shops
    // Move item to stock
    @CrossOrigin(origins = "*")
    @PostMapping("/PayToShop.service")
    public PayToShopRes PayToShop (@RequestBody List<PayToShopReq> payToShopReq ){
        PayToShopRes result = new PayToShopRes();
        try {
            result = inventoryService.PayToShopService(payToShopReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // report stock
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportStock.service")
    public ReportStockRes ReportStock (@RequestBody MoveToStockReq moveToStockReq ){
        ReportStockRes result = new ReportStockRes();
        try {
            result = inventoryService.ReportStockService(moveToStockReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // item his
    // report stock
    @CrossOrigin(origins = "*")
    @PostMapping("/ItemHis.service")
    public ItemHisRes ItemHis (@RequestBody ItemHisReq itemHisReq ){
        ItemHisRes result = new ItemHisRes();
        try {
            result = inventoryService.ItemHisResService(itemHisReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //report stock Detail
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportStockDetail.service")
    public ReportStockRes ReportStockDetail (@RequestBody ReportStockDetailReq reportStockDetailReq ){
        ReportStockRes result = new ReportStockRes();
        try {
            result = inventoryService.ReportStockServiceDetail(reportStockDetailReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
// show offer paper when saved
@CrossOrigin(origins = "*")
@PostMapping("/showofferpaper.service")
public ShowOfferPaper showofferpaper(@RequestBody OfferPaperReq offerPaperReq){
    ShowOfferPaper result = new ShowOfferPaper();
    try {
        result = inventoryService.ShowOfferPaperSaved(offerPaperReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//report offer paper
@CrossOrigin(origins = "*")
@PostMapping("/reportshowofferpaper.service")
public ShowOfferPaper reportshowofferpaper(@RequestBody OfferPaperReq offerPaperReq){
    ShowOfferPaper result = new ShowOfferPaper();
    try {
        result = inventoryService.ReportShowOfferPaperSaved(offerPaperReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//report currency inventory
@CrossOrigin(origins = "*")
@PostMapping("/reportShowofferpaperCurrencyUSD.service")
public ReportShowOfferPaper reportShowofferpaperCurrencyUSD(@RequestBody OfferPaperReq offerPaperReq){
    ReportShowOfferPaper result = new ReportShowOfferPaper();
    try {
        result = inventoryService.reportShowofferpaperCurrencyServiceUSD(offerPaperReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    @CrossOrigin(origins = "*")
    @PostMapping("/CurrencyUSDinKip.service")
    public ReportShowOfferPaper CurrencyUSDinKip(@RequestBody OfferPaperReq offerPaperReq){
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        try {
            result = inventoryService.CurrencyUSDinKip(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/CurrencyTHBinKip.service")
    public ReportShowOfferPaper CurrencyTHBinKip(@RequestBody OfferPaperReq offerPaperReq){
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        try {
            result = inventoryService.CurrencyTHBinKip(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/CurrencyLAKinKip.service")
    public ReportShowOfferPaper CurrencyLAKinKip(@RequestBody OfferPaperReq offerPaperReq){
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        try {
            result = inventoryService.CurrencyLAKinKip(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/reportShowofferpaperCurrencyTHB.service")
    public ReportShowOfferPaper reportShowofferpaperCurrencyTHB(@RequestBody OfferPaperReq offerPaperReq){
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        try {
            result = inventoryService.reportShowofferpaperCurrencyServiceTHB(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/reportShowofferpaperCurrencyLAK.service")
    public ReportShowOfferPaper reportShowofferpaperCurrencyLAK(@RequestBody OfferPaperReq offerPaperReq){
        ReportShowOfferPaper result = new ReportShowOfferPaper();
        try {
            result = inventoryService.reportShowofferpaperCurrencyServiceLAK(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
//report stock
@CrossOrigin(origins = "*")
@PostMapping("/reportStockDayWeek.service")
public ReportstockRes ReportstockRes (@RequestBody ReportstockReq reportstockReq){
    ReportstockRes result = new ReportstockRes();
    try {
        result = inventoryService.reportStockDayWeekService(reportstockReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
// fix report
@CrossOrigin(origins = "*")
@PostMapping("/FixReport.service")
public FixReportRes FixReport(@RequestBody FixReq fixReq ){
    FixReportRes result = new FixReportRes();
    try {
        result = inventoryService.FixReportService(fixReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    // show offer paper Detail
    @CrossOrigin(origins = "*")
    @PostMapping("/showofferpaperDetail.service")
    public ShowOfferPaperDetail showofferpaperDetail(@RequestBody OfferPaperReq offerPaperReq){
        ShowOfferPaperDetail result = new ShowOfferPaperDetail();
        try {
            result = inventoryService.ShowOfferPaperDetail(offerPaperReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //detail paid to shop
    @CrossOrigin(origins = "*")
    @PostMapping("/detailBillPaidToShop.service")
    public PaidToShopDetail detailBillPaidToShop(@RequestBody PaidToShopDetailReq paidToShopDetailReq){
        PaidToShopDetail result = new PaidToShopDetail();
        try {
            result = inventoryService.PaidToShopDetailService(paidToShopDetailReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // show fix
    @CrossOrigin(origins = "*")
    @PostMapping("/showFix.service")
    public ShowFix showFix(@RequestBody FixReq fixReq){
        ShowFix result = new ShowFix();
        try {
            result = inventoryService.ShowFixList(fixReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // show fix Detail
    @CrossOrigin(origins = "*")
    @PostMapping("/showFixDetail.service")
    public ShowFix showFixDetail(@RequestBody FixReq fixReq){
        ShowFix result = new ShowFix();
        try {
            result = inventoryService.ShowFixDetail(fixReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // update Fix Cost
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateFixCost.service")
    public ShowFix UpdateFixCost(@RequestBody FixReq fixReq){
        ShowFix result = new ShowFix();
        try {
            result = inventoryService.UpdateFixCostService(fixReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
// gen code OFF-XXX
@CrossOrigin(origins = "*")
@PostMapping("/gencodeofferpaper.service")
public GenCodeOfferPaper gencodeofferpaper(){
    GenCodeOfferPaper result = new GenCodeOfferPaper();
    try {
        result = inventoryService.GenOfferPaperService();
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
// gen code PO-XXX
@CrossOrigin(origins = "*")
@PostMapping("/GenCodePO.service")
public GenCodePO GenCodePO(){
    GenCodePO result = new GenCodePO();
    try {
        result = inventoryService.GenCodePoService();
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
    // Update Shops
    @CrossOrigin(origins = "*")
    @PostMapping("/limitstock.service")
    public Messages limitstock (@RequestBody ItemReq itemReq ){
        Messages result = new Messages();
        try {
            result = inventoryService.LimitStock(itemReq);

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
//delete his fill oill
@CrossOrigin(origins = "*")
@PostMapping("/DelHisOil.service")
public FillOilRes DelHisOil (@RequestBody FillOilReq fillOilReq) {

    FillOilRes result = new FillOilRes();
    try {
        result = inventoryService.DelFillOilHis(fillOilReq);
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
    //detail item by id
    //-- Show Items
    @CrossOrigin(origins = "*")
    @PostMapping("/ListItemsDetail.service")
    public ItemRes listItemsDetail(@RequestBody ItemReq itemReq ){
        ItemRes result = new ItemRes();
        try {
            result = inventoryService.ItemsDetailbyId(itemReq);
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
        @RequestParam("toKen") String  toKen,
        @RequestParam("branch_id") String  branch_id,
        @RequestParam("limitQty") String  limitQty
)

{
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
        data.setBranch_id(branch_id);
        data.setLimitQty(limitQty);
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
            @RequestParam("branch_id") String  branch_id
//            @RequestParam("img") String  img

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
            data.setBranch_id(branch_id);
//            data.setImg(img);
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
