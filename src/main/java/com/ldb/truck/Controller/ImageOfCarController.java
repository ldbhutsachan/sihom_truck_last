package com.ldb.truck.Controller;

import com.ldb.truck.Model.ImageOfCar.ImageOfCarReq;
import com.ldb.truck.Service.ImageOfCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("${base_url}")
public class ImageOfCarController {

    private static final Logger log = LogManager.getLogger(ImageOfCarController.class);

    @Autowired
    private ImageOfCarService imageOfCarService;

    @CrossOrigin(origins = "*")
    @PostMapping("/insertcarimages")
    public ResponseEntity<?> insertCarImages(
            @RequestParam("toKen") String toKen,
            @RequestParam("carId") Integer carId,
            @RequestParam("imageType") String imageType,
            @RequestPart(value = "files", required = false) MultipartFile[] imageFile) {
        log.info("insertcarimages called with carId: " + carId + " and imageType: " + imageType);
        return new ResponseEntity<>(imageOfCarService.insertCarImages(toKen, carId, imageType, imageFile), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updatecarimages")
    public ResponseEntity<?> updateCarImages(
            @RequestParam("toKen") String toKen,
            @RequestParam("id") Long id,
            @RequestParam("imageType") String imageType,
            @RequestPart(value = "files", required = false) MultipartFile[] imageFile) {
        log.info("updatecarimages called for id: " + id + " and imageType: " + imageType);
        return new ResponseEntity<>(imageOfCarService.updateCarImages(toKen, id, imageType, imageFile), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/showcarimages")
    public ResponseEntity<?> showCarImages(@RequestBody ImageOfCarReq req) {
        log.info("showcarimages called with token: " + req.getToKen() + " and imageType: " + req.getImageType());
        return new ResponseEntity<>(imageOfCarService.showCarImages(req), HttpStatus.OK);
    }
}
