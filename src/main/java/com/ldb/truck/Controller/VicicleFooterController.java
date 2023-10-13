package com.ldb.truck.Controller;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderRes;
import com.ldb.truck.Model.Login.location.LocationOut;
import com.ldb.truck.Service.VicicleFooterService.VicicleFooterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class VicicleFooterController {
    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    VicicleFooterService vicicleFooterService;
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
    ///--
    @CrossOrigin(origins = "*")
    @PostMapping("/saveVicicleFooterByID.service")
    public VicicleFooterRes saveVicicleHeader(@RequestBody VicicleFooterReq vicicleFooterReq) {
        log.info("req:"+vicicleFooterReq);
        VicicleFooterRes result = new VicicleFooterRes();
        try {

            result = vicicleFooterService.saveVicicleHeader(vicicleFooterReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //update data
    @CrossOrigin(origins = "*")
    @PostMapping("/updateVicicleFooter.service")
    public VicicleFooterRes updateVicicleHeader(@RequestBody VicicleFooterReq vicicleFooterReq) {
        VicicleFooterRes result = new VicicleFooterRes();
        try {
            result = vicicleFooterService.updateVicicleHeader(vicicleFooterReq);
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
