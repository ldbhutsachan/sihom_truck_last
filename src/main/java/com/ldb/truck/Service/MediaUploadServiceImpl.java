
package com.ldb.truck.Service;
import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Dao.upload.MediaUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class MediaUploadServiceImpl implements MediaUploadService {
//    @Value("${media.upload.url}")
//    private String uploadURL;
////    @Value("${media.upload.path}")
////    private String uploadPath;
//    @Value("${media.upload.url_pdf}")
//    private String uploadURLPDF;
//    //====================staff
//    @Value("${media.upload.url.staff}")
//    private String uploadURLStaff;
//    @Value("${media.upload.path.staff}")
//    private String uploadPathStaff;
//    @Value("${media.upload.url_pdf.staff}")
//    private String uploadURLPDFStaff;
//    //====================car
//    @Value("${media.upload.url.car}")
//    private String uploadURLCar;
//    @Value("${media.upload.path.car}")
//    private String uploadPathCar;
//    @Value("${media.upload.url_pdf.car}")
//    private String uploadURLPDFCar;

    @Value("${upload.directory.batery}")
    private String uploadDirectory;

    @Value("${upload.directory.car}")
    private String uploadDirectoryCar;

    @Value("${upload.directory.staff}")
    private String uploadDirectoryStaff;
    @Override
    public String uploadMedia(MultipartFile file) {
        try {

            log.info("Begin Convert MultiPart File To Base64String");
            String base64String = new String(Base64.encodeBase64(file.getBytes()));
            log.info("Convert To Base64 String Completed");

            log.info("Get File Extension");
            String[] filePattern = (file.getOriginalFilename()).split("\\.");
            String extension = filePattern[filePattern.length-1];
            log.info("Media File Extension Is {}",extension);

            log.info("Generate Random Media Name");
            UUID uuid =  UUID.randomUUID();
            String fileName = uuid.toString() + "-"+uuid.toString() + "." + extension;
            log.info("New Generate File Name {}" + fileName);
            //==============upload images========================================================
            File targetDirectory = new File(uploadDirectory);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            // Create the file path
            Path filePath = Path.of(uploadDirectory, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            //==============upload images========================================================

//            HttpClient client = HttpClients.createDefault();
//            HttpPost httpPost;
//            if(extension.toLowerCase().equals("pdf")){
//                httpPost = new HttpPost(uploadURLPDF);
//            }else{
//                httpPost = new HttpPost(uploadURL);
//            }
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("BASE64", base64String));
            params.add(new BasicNameValuePair("filename", fileName));
           // httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            log.info("Start To Post Upload Image ...");
           // HttpResponse rest = client.execute(httpPost);
            log.info("Finish Image Upload");
            return  fileName;
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }
    //---staff
    @Override
    public String uploadMediaStaff(MultipartFile file) {
        try {
            log.info("Begin Convert MultiPart File To Base64String");
            String base64String = new String(Base64.encodeBase64(file.getBytes()));
            log.info("Convert To Base64 String Completed");

            log.info("Get File Extension");
            String[] filePattern = (file.getOriginalFilename()).split("\\.");
            String extension = filePattern[filePattern.length-1];
            log.info("Media File Extension Is {}",extension);

            log.info("Generate Random Media Name");
            UUID uuid =  UUID.randomUUID();
            String fileName = uuid.toString() + "-"+uuid.toString() + "." + extension;
            log.info("New Generate File Name {}" + fileName);

            //==============upload images========================================================
            File targetDirectory = new File(uploadDirectoryStaff);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            // Create the file path
            Path filePath = Path.of(uploadDirectoryStaff, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            //==============upload images========================================================
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("BASE64", base64String));
            params.add(new BasicNameValuePair("filename", fileName));
            log.info("Start To Post Upload Image ...");
            log.info("Finish Image Upload");
            return  fileName;
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }
    @Override
    public String uploadMediacar(MultipartFile file) {
        try {
            log.info("Begin Convert MultiPart File To Base64String");
            String base64String = new String(Base64.encodeBase64(file.getBytes()));
            log.info("Convert To Base64 String Completed");
            log.info("Get File Extension");
            String[] filePattern = (file.getOriginalFilename()).split("\\.");
            String extension = filePattern[filePattern.length-1];
            log.info("Media File Extension Is {}",extension);
            log.info("Generate Random Media Name");
            UUID uuid =  UUID.randomUUID();
            String fileName = uuid.toString() + "-"+uuid.toString() + "." + extension;
            log.info("New Generate File Name {}" + fileName);
            //==============upload images========================================================
            File targetDirectory = new File(uploadDirectoryCar);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            Path filePath = Path.of(uploadDirectoryCar, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            //==============upload images========================================================
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("BASE64", base64String));
            params.add(new BasicNameValuePair("filename", fileName));
            log.info("Start To Post Upload Image ...");
            log.info("Finish Image Upload");
            return  fileName;
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }
}
