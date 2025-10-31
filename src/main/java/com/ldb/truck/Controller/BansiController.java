package com.ldb.truck.Controller;

import com.ldb.truck.Entity.Bansi.BansiEntity;
import com.ldb.truck.Entity.Bansi.PayTypeEntity;
import com.ldb.truck.Model.Bansi.PayTypeReq;
import com.ldb.truck.Model.Bansi.ProjectShowReq;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.Bansi.BansiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${base_url}")
public class BansiController {

    @Autowired
    private BansiService bansiService;

    //save project
    @CrossOrigin(origins = "*")
    @PostMapping("/saveProjectPaymen.service")
    public ResponseEntity<DataResponse> saveReq(@RequestBody BansiEntity bansiEntity) {
        DataResponse response = bansiService.saveProjectPaymen(bansiEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update project
    @CrossOrigin(origins = "*")
    @PostMapping("/updateProjectPaymen.service")
    public ResponseEntity<DataResponse> updateReq(@RequestBody BansiEntity bansiEntity) {
        DataResponse response = bansiService.updateProjectPaymen(bansiEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //show project
    @CrossOrigin(origins = "*")
    @PostMapping("/showProjectPaymen.service")
    public ResponseEntity<DataResponse> showProject(@RequestBody ProjectShowReq request) {
        DataResponse response = bansiService.showProjectPayment(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // save payment_type
    @CrossOrigin(origins = "*")
    @PostMapping("/savePayType.service")
    public ResponseEntity<DataResponse> saveReq(@RequestBody PayTypeEntity payTypeEntity) {
        DataResponse response = bansiService.savePayType(payTypeEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update payment_tpye
    @PostMapping("/updatePayType.service")
    public ResponseEntity<Map<String, String>> updatePayType(@RequestBody PayTypeEntity payTypeEntity) {
        Map<String, String> response = bansiService.updatePayType(payTypeEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //show payment_type
    @CrossOrigin(origins = "*")
    @PostMapping("/showOnePayType.service")
    public ResponseEntity<DataResponse> showOnePayType(@RequestBody PayTypeReq payTypeReq) {
        DataResponse response = bansiService.showOnePayType(payTypeReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
