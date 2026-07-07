package com.ldb.truck.Service;

import com.ldb.truck.Dao.ImageOfCarDao;
import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.ImageOfCar.ImageOfCar;
import com.ldb.truck.Model.ImageOfCar.ImageOfCarReq;
import com.ldb.truck.Model.ImageOfCar.ImageOfCarRes;
import com.ldb.truck.Model.Login.Profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageOfCarService {

    private static final Logger log = LogManager.getLogger(ImageOfCarService.class);

    @Autowired
    private ImageOfCarDao imageOfCarDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private MediaUploadService mediaUploadService;

    public ImageOfCarRes insertCarImages(String toKen, Integer carId, String imageType, MultipartFile[] imageFile) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }
            String userName = userProfiles.get(0).getUserName();

            ImageOfCarReq checkReq = new ImageOfCarReq();
            checkReq.setCarId(carId);
            checkReq.setImageType(imageType);
            List<ImageOfCar> existingImages = imageOfCarDao.listCarImages(checkReq);
            if (existingImages != null && !existingImages.isEmpty()) {
                response.setStatus("01");
                response.setMessage("ຮູບພາບປະເພດ: \" + imageType + \" .ມີຢູ່ແລ້ວ ກະລຸນາອັບເດັບແທນໄດ້ເລີຍ");

                response.setData(null);
                return response;
            }

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/car/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> filePaths = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    String fileName = mediaUploadService.uploadMediacar(file);
                    filePaths.add(pathAdd + fileName);
                });
                log.info("Uploaded files successfully: " + filePaths);
                filePath = StringUtils.join(filePaths, ",");
            } else {
                log.warn("No files uploaded");
            }

            ImageOfCarReq req = new ImageOfCarReq();
            req.setCarId(carId);
            req.setImageType(imageType);

            int result = imageOfCarDao.insertCarImage(req, userName, filePath);
            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data inserted successfully");
            } else {
                response.setStatus("01");
                response.setMessage("Failed to insert data");
            }
            response.setData(null);
        } catch (Exception e) {
            log.error("Error inserting car image: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while inserting data");
            response.setData(null);
        }
        return response;
    }

    public ImageOfCarRes updateCarImages(String toKen, Long id, String imageType, MultipartFile[] imageFile) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(toKen);
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }
            String userName = userProfiles.get(0).getUserName();

            String filePath = null;
            String pathAdd = "http://khounkham.com/images/car/";
            if (imageFile != null && imageFile.length > 0) {
                List<String> filePaths = new ArrayList<>();
                Arrays.asList(imageFile).forEach(file -> {
                    String fileName = mediaUploadService.uploadMediacar(file);
                    filePaths.add(pathAdd + fileName);
                });
                log.info("Uploaded files successfully: " + filePaths);
                filePath = StringUtils.join(filePaths, ",");
            } else {
                log.warn("No files uploaded");
            }

            ImageOfCarReq req = new ImageOfCarReq();
            req.setId(id);
            req.setImageType(imageType);

            int result = imageOfCarDao.updateCarImage(req, userName, filePath);
            if (result > 0) {
                response.setStatus("00");
                response.setMessage("Data updated successfully");
            } else {
                response.setStatus("01");
                response.setMessage("Failed to update data (id may not exist)");
            }
            response.setData(null);
        } catch (Exception e) {
            log.error("Error updating car image: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while updating data");
            response.setData(null);
        }
        return response;
    }

    public ImageOfCarRes showCarImages(ImageOfCarReq req) {
        ImageOfCarRes response = new ImageOfCarRes();
        try {
            List<Profile> userProfiles = profileDao.getProfileInfoByToken(req.getToKen());
            if (userProfiles == null || userProfiles.isEmpty()) {
                response.setStatus("01");
                response.setMessage("Invalid or missing toKen");
                response.setData(null);
                return response;
            }

            List<ImageOfCar> data = imageOfCarDao.listCarImages(req);
            if (data != null && !data.isEmpty()) {
                response.setStatus("00");
                response.setMessage("Success");
                response.setData(data);
            } else {
                response.setStatus("01");
                response.setMessage("Data not found !!!");
                response.setData(null);
            }
        } catch (Exception e) {
            log.error("Error showing car images: ", e);
            response.setStatus("05");
            response.setMessage("An error occurred while retrieving data");
            response.setData(null);
        }
        return response;
    }
}
