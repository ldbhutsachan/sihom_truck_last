package com.ldb.truck.Controller;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.Login.GetUserLoginRes;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.ResFromDateReq;
import com.ldb.truck.Model.Login.Truck.*;
import com.ldb.truck.Model.Login.customer.CustomerReq;
import com.ldb.truck.Model.Login.customer.CustomerRes;
import com.ldb.truck.Model.Login.location.LocationReq;
import com.ldb.truck.Model.Login.location.LocationRes;
import com.ldb.truck.Model.Login.product.ProductReq;
import com.ldb.truck.Model.Login.product.ProductRes;
import com.ldb.truck.Model.Login.staft.stafReq;
import com.ldb.truck.Model.Login.staft.staftRes;
import com.ldb.truck.Service.Login.LoginService;
import com.ldb.truck.Service.Product.ProductService;
import com.ldb.truck.Service.Truck.TruckService;
import com.ldb.truck.Service.customer.CustomerService;
import com.ldb.truck.Service.location.LocationService;
import com.ldb.truck.Service.staft.StaftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    @Autowired
    CustomerService customerService;
    @Autowired
    StaftService staftService;
    @Autowired
    ProductService productService;
    @Autowired
    LoginService loginService;
    @Autowired
    LocationService locationService;
    @Autowired
    TruckService truckService;
    @Autowired
    private MediaUploadService mediaUploadService;
    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public String Test() throws Exception {

        return "hello";
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getAllCustomer")
    public CustomerRes  getAllCustomer (){
        CustomerRes result = new CustomerRes();
        try {

            result = customerService.getAllCustomer();

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getCustomerById")
    public CustomerRes  getCustomerById (@RequestBody  CustomerReq customerReq){
        CustomerRes result = new CustomerRes();
        try {
            result = customerService.getCustomerById(customerReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/StoreCustomer")
    public CustomerRes  StoreCustomer (@RequestBody  CustomerReq customerReq){
        CustomerRes result = new CustomerRes();
        try {
            result = customerService.StoreCustomer(customerReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateCustomer")
    public CustomerRes  UpdateCustomer (@RequestBody  CustomerReq customerReq){

        CustomerRes result = new CustomerRes();

        try {

            result = customerService.UpdateCustomer(customerReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/DeleteCustomer")
    public CustomerRes  DeleteCustomer (@RequestBody  CustomerReq customerReq){
        CustomerRes result = new CustomerRes();
        String id = String.valueOf(customerReq.getId());
        try {
            result = customerService.deleteCustomer(id);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //--List<staftOut> getChooseStaft01(
    @CrossOrigin(origins = "*")
    @PostMapping("/getChooseStaft01.service")
    public staftRes getChooseStaft01(){
        staftRes result = new staftRes();
        try {
            result = staftService.getChooseStaft01();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }    @CrossOrigin(origins = "*")
    @PostMapping("/getChooseStaft02.service")
    public staftRes getChooseStaft02(){
        staftRes result = new staftRes();
        try {
            result = staftService.getChooseStaft02();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getStaftById")
    public staftRes getStaftById (@RequestBody stafReq stafReq){
        staftRes result = new staftRes();
        try {
            result = staftService.getStaftById(stafReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/StoreStaft" , consumes = {"multipart/form-data"})
    public Messages StoreStaft(
            @RequestParam(name="files" , required=false) MultipartFile[] files
            ,@RequestParam("staftId") String  staftId
            ,@RequestParam("name") String  name
            ,@RequestParam("surname") String  surname
            ,@RequestParam("idCard") String  idCard
            ,@RequestParam("licenceId") String  licenceId
            ,@RequestParam("verBy") String  verBy
            ,@RequestParam("licenceExp") String  licenceExp
            ,@RequestParam("vaillage") String  vaillage
            ,@RequestParam("district") String  district
            ,@RequestParam("province") String  province
            ,@RequestParam("mobile") String  mobile
            ,@RequestParam("mobile1") String  mobile1
            ,@RequestParam("gender") String  gender
            ,@RequestParam("userId") String  userId
    ){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            stafReq data = new stafReq();
            data.setStaftId(staftId);
            data.setName(name);
            data.setSurname(surname);
            data.setIdCard(idCard);
            data.setLicenceId(licenceId);
            data.setVerBy(verBy);
            data.setLicenceExp(licenceExp);
            data.setVaillage(vaillage);
            data.setDistrict(district);
            data.setProvince(province);
            data.setMobile(mobile);
            data.setMobile1(mobile1);
            data.setGender(gender);
            data.setUserId(userId);
//            data.setDataTime(dataTime);
//            data.setImageStaff(imageStaff);
            logger.error("******file lenght"+files);
            logger.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                logger.warn("************* file name is null ****************");
                data.setImageStaff("http://khounkham.com/images/staff/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediaStaff(file));
                });
                logger.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImageStaff(fileName);
            }
            result = staftService.StoreStaft(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateStaft" , consumes = {"multipart/form-data"})
    public Messages updateStaft(
            @RequestParam(name="files" , required=false) MultipartFile[] files
            ,@RequestParam("id") String  id
            ,@RequestParam("staftId") String  staftId
            ,@RequestParam("name") String  name
            ,@RequestParam("surname") String  surname
            ,@RequestParam("idCard") String  idCard
            ,@RequestParam("licenceId") String  licenceId
            ,@RequestParam("verBy") String  verBy
            ,@RequestParam("licenceExp") String  licenceExp
            ,@RequestParam("vaillage") String  vaillage
            ,@RequestParam("district") String  district
            ,@RequestParam("province") String  province
            ,@RequestParam("mobile") String  mobile
            ,@RequestParam("mobile1") String  mobile1
            ,@RequestParam("gender") String  gender
            ,@RequestParam("userId") String  userId
            ,@RequestParam("imageStaff") String  imageStaff
    ){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            stafReq data = new stafReq();
            data.setId(id);
            data.setStaftId(staftId);
            data.setName(name);
            data.setSurname(surname);
            data.setIdCard(idCard);
            data.setLicenceId(licenceId);
            data.setVerBy(verBy);
            data.setLicenceExp(licenceExp);
            data.setVaillage(vaillage);
            data.setDistrict(district);
            data.setProvince(province);
            data.setMobile(mobile);
            data.setMobile1(mobile1);
            data.setGender(gender);
            data.setUserId(userId);
            data.setImageStaff(imageStaff);
            logger.error("******file lenght"+files);
            logger.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                logger.warn("************* file name is null ****************");
                data.setImageStaff("1");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadMediaStaff(file));
                });
                logger.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setImageStaff(fileName);
            }
            result = staftService.UpdateStaft(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getAllStaft")
    public staftRes getAllStaft (){
        staftRes result = new staftRes();
        try {
            result = staftService.getAllStaft();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/DeleteStaft")
    public staftRes  DeleteStaft ( @RequestBody stafReq stafReq){
        staftRes result = new staftRes();
        try {
            result = staftService.DeleteStaft(stafReq.getId());

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getAllProduct")
    public ProductRes getAllProduct (){
        ProductRes result = new ProductRes();
        try {
            result = productService.getAllProcut();

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getProductById")
    public ProductRes getProductById (@RequestBody  ProductReq productReq){

        ProductRes result = new ProductRes();

        try {

            result = productService.getProcutById(productReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/StoreProduct")
    public ProductRes StoreProduct (@RequestBody  ProductReq productReq){

        ProductRes result = new ProductRes();

        try {

            result = productService.StoreProcut(productReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateProduct")
    public ProductRes UpdateProduct (@RequestBody  ProductReq productReq){

        ProductRes result = new ProductRes();

        try {

            result = productService.UpdaeProcut(productReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/DeleteProduct")
    public ProductRes DeleteProduct (@RequestBody  ProductReq productReq){

        ProductRes result = new ProductRes();

        try {

            result = productService.DeleteProcut(productReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Login")
    public GetUserLoginRes Login (@RequestBody  LoginReq loginReq){
        GetUserLoginRes result = new GetUserLoginRes();
        try {
            System.out.println("============================>login service<============================================");
            result = loginService.Userlogin(loginReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllLocation")
    public LocationRes getAllLocation (){
        LocationRes result = new LocationRes();
        try {
            result = locationService.getAllLocation();
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/getLocationById")
    public LocationRes getLocationById (@RequestBody LocationReq locationReq){
        LocationRes result = new LocationRes();
        try {
            result = locationService.getLocationById(locationReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/StoreLocation")
    public LocationRes StoreProduct (@RequestBody LocationReq locationReq){
        LocationRes result = new LocationRes();
        try {

            result = locationService.StoreLocation(locationReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateLocation")
    public LocationRes UpdateLocation (@RequestBody LocationReq locationReq){
        LocationRes result = new LocationRes();
        try {
            result = locationService.UpdateLocation(locationReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/DeleteLocation")
    public LocationRes DeleteLocation (@RequestBody LocationReq locationReq){

        LocationRes result = new LocationRes();

        try {

            result = locationService.DeleteLocation(locationReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getAllTruck")
    public TruckRes getAllTruck (){

        TruckRes result = new TruckRes();

        try {

            result = truckService.getAllTruck();

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getTruckById")
    public TruckRes getTruckById (@RequestBody TruckReq truckReq){

        TruckRes result = new TruckRes();

        try {

            result = truckService.getTruckById(truckReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/StoreTruck")
    public TruckRes StoreTruck (@RequestBody TruckReq truckReq){
        TruckRes result = new TruckRes();
        try {
            result = truckService.StoreTruck(truckReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/UpdateTruck")
    public TruckRes UpdateTruck (@RequestBody TruckReq truckReq){
        TruckRes result = new TruckRes();
        try {
            result = truckService.UpdateTruck(truckReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/DeleteTruck")
    public TruckRes DeleteTruck (@RequestBody TruckReq truckReq){
        TruckRes result = new TruckRes();

        try {

            result = truckService.DeleteTruck(truckReq);

        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //----------------=================REPORT CAR DETAILS
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportGiveCarAll")
    public TruckDetailsRes ReportGiveCarAll(@RequestBody ResFromDateReq resFromDateReq){
        logger.info("================================>ReportGiveCarAll<========================================================");
        TruckDetailsRes result = new TruckDetailsRes();
        try {
            result = truckService.ReportGive(resFromDateReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //=============================================>ReportGiveCarAllNo<===========================================================
    @CrossOrigin(origins = "*")
    @PostMapping("/ReportGiveCarAllNo")
    public TruckDetailsGroupRes ReportGiveCarAllNo(@RequestBody TruckDetailsReq truckDetailsReq){
        logger.info("================================>ReportGiveCarAll<========================================================");
        TruckDetailsGroupRes result = new TruckDetailsGroupRes();
        try {
            result = truckService.ReportGiveCarAllNo(truckDetailsReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
}
