package com.ldb.truck.Controller;

import com.ldb.truck.Dao.upload.MediaUploadService;
import com.ldb.truck.Model.Login.Dept_Must_Receive.*;
import com.ldb.truck.Model.Login.DocumentStorage.*;
import com.ldb.truck.Model.Login.DocumentStorage.RockShipSample.RockShipSampleReq;
import com.ldb.truck.Model.Login.DocumentStorage.RockShipSample.RockShipSampleRes;
import com.ldb.truck.Model.Login.Messages;
import com.ldb.truck.Model.Login.Task.LinkReq;
import com.ldb.truck.Model.Login.Task.LinkRes;
import com.ldb.truck.Model.Login.Task.TaskReq;
import com.ldb.truck.Model.Login.Task.TaskRes;
import com.ldb.truck.Service.DocumentStorageService.DocumentStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class DocumentStorageController {

    private static final Logger log = LogManager.getLogger(VicicleHeaderController.class);
    @Autowired
    DocumentStorageService documentStorageService;
    @Autowired
    private MediaUploadService mediaUploadService;

//insert ducument
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/StoreDocument.service" , consumes = {"multipart/form-data"})
    public Messages InsertDocument(
            @RequestParam("files") MultipartFile files,
            @RequestParam("branchUser") String  branchUser,
            @RequestParam("documentType") String  documentType,
            @RequestParam("toKen") String  toKen,
            @RequestParam("inboundnumber") String  inboundnumber,
            @RequestParam("outboundnumber") String  outboundnumber,
            @RequestParam("dateExpDoc") String  dateExpDoc,
            @RequestParam("classofdocs") String  classofdocs,
            @RequestParam("bound") String  bound,
            @RequestParam("content_doc") String content_doc,
            @RequestParam("whocarrydoc") String  whocarrydoc,
            @RequestParam("etc") String  etc,
            @RequestParam("lektee") String  lektee,
            @RequestParam("datetakein") String  datetakein,
            @RequestParam("company") String  company,
            @RequestParam("bouang") String  bouang,
            @RequestParam("inside") String  inside
    ){

        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            DocumentStorageReq data = new DocumentStorageReq();
            data.setBranchUser(branchUser);
            data.setDocType(documentType);
            data.setToKen(toKen);
            data.setInboundnumber(inboundnumber);
            data.setOutboundnumber(outboundnumber);
            data.setDateExpDoc(dateExpDoc);
            data.setClassofdocs(classofdocs);
            data.setBound(bound);
            data.setContent_doc(content_doc);
            data.setWhocarrydoc(whocarrydoc);
            data.setEtc(etc);
            data.setLektee(lektee);
            data.setDatetakein(datetakein);
            data.setCompany(company);
            data.setBouang(bouang);
            data.setInside(inside);

            log.error("******file lenght"+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                log.warn("************* file name is null ****************");
                data.setPdf("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                    fileNames.add(mediaUploadService.uploadPDF(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setPdf(fileName);
            }
            result = documentStorageService.InsertDocument(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
//    insert pic of borhin
@CrossOrigin(origins = "*")
@PostMapping(value = "/StorePicOfBorHin.service", consumes = {"multipart/form-data"})
public Messages InsertDocument(@RequestParam("files") MultipartFile[] files, @RequestParam("toKen") String toKen
        , @RequestParam("folderName") String folderName,@RequestParam("dateCreate") String dateCreate,@RequestParam("branch_id") String branch_id) {
    log.info("===================================save header==================================================");
    Messages result = new Messages();
    try {
        DocumentStorageReq[] data = new DocumentStorageReq[1]; // Size should be 1
        data[0] = new DocumentStorageReq(); // Initialize first element
        data[0].setToKen(toKen); // Set token
        data[0].setFolderName(folderName); // Set foderName
        data[0].setDateCreate(dateCreate); // Set date
        data[0].setBranch_id(branch_id); // Set date

        if (files == null) {
            log.warn("************* file name is null ****************");
            data[0].setPdf("http://khounkham.com/images/car/image.jpg");
        } else {
            String []fileName = mediaUploadService.uploadPDF2(files); // Single file handling
            log.info("Uploaded the file successfully: " + fileName);
            data[0].setPdf(Arrays.toString(fileName));
        }

        result = documentStorageService.InsertPicOfBor(data);
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return result;
    }
    return result;
}


//หนี้ต้องส่ง Dept must received insert
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/DeptMustReceivedInsert.service" , consumes = {"multipart/form-data"})
    public Messages DeptMustReceivedInsert(
            @RequestParam("document_1") MultipartFile document_1,
            @RequestParam("customer_id") String  customer_id,
            @RequestParam("topic") String  topic,
            @RequestParam("type_id") String  type_id,
            @RequestParam("currency") String  currency,
            @RequestParam("datee") String  datee,
            @RequestParam("due_date") String  due_date,
            @RequestParam("reference_number") String  reference_number,
            @RequestParam("lek_bai_sung") String  lek_bai_sung,
            @RequestParam("amount_money") String  amount_money,
            @RequestParam("quotation") String  quotation,
            @RequestParam("description") String  description,
            @RequestParam("note") String  note,
            @RequestParam("num") String  num,
            @RequestParam("unit") String  unit,
            @RequestParam("totalMoney") String  totalMoney,
            @RequestParam("quotation_code") String  quotation_code,
            @RequestParam("toKen") String  toKen
    ){

        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            DeptMustReceivedReq data = new DeptMustReceivedReq();
            data.setCustomer_id(customer_id);
            data.setTopic(topic);
            data.setType_id(type_id);
            data.setCurrency(currency);
            data.setDate(datee);
            data.setDue_date(due_date);
            data.setReference_number(reference_number);
            data.setLek_bai_sung(lek_bai_sung);
            data.setAmount_money(amount_money);
            data.setQuotation(quotation);
            data.setDescription(description);
            data.setNote(note);
            data.setNum(num);
            data.setUnit(unit);
            data.setTotalMooney(totalMoney);
            data.setQuotation_code(quotation_code);
            data.setToKen(toKen);

            log.error("******file lenght"+document_1);
            log.error(data);
            String fileName = "";
            String fileName2 = "";
            List<String> fileNames = new ArrayList<>();
//            List<String> fileNames2 = new ArrayList<>();
            if(document_1 == null){
                log.warn("************* file name is null ****************");
                data.setDocument_1("http://khounkham.com/images/car/image.jpg");
//                data.setDocument_2("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(document_1).stream().forEach(file -> {
                    fileNames.add(mediaUploadService.uploadPDF(file));
                });
//                Arrays.asList(document_2).stream().forEach(file -> {
//                    fileNames2.add(mediaUploadService.uploadPDF(file));
//                });
                log.info("Uploaded the files 1 successfully: " + fileNames );
//                log.info("Uploaded the files 2 successfully: " + fileNames2 );
                fileName = StringUtils.join(fileNames, ',');
//                fileName2 = StringUtils.join(fileNames2, ',');
                data.setDocument_1(fileName);
//                data.setDocument_2(fileName2);
            }
            result = documentStorageService.DeptMustReceivedInsertServiece(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
//    invoice dept insert
@CrossOrigin(origins = "*")
@PostMapping(value = "/InvoiceDeptInsert.service" , consumes = {"multipart/form-data"})
public Messages InvoiceDeptInsert(
        @RequestParam("pdfandpic") MultipartFile pdfandpic,
        @RequestParam("date") String  date_invoice,
        @RequestParam("detail") String  detail,
        @RequestParam("amount_of_money") String  amount_of_money,
        @RequestParam("quotation_code") String  quotation_code,
        @RequestParam("toKen") String  toKen,
        @RequestParam("invoice_code") String  invoice_code
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        InvoiceDeptReq data = new InvoiceDeptReq();
//        data.setFile(file);
        data.setDate_invoice(date_invoice);
        data.setDetail(detail);
        data.setAmount_of_money(amount_of_money);
        data.setQuotation_code(quotation_code);
        data.setToKen(toKen);
        data.setInvoice_code(invoice_code);

        log.error("******file lenght"+pdfandpic);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(pdfandpic == null){
            log.warn("************* file name is null ****************");
            data.setPdfandpic("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(pdfandpic).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files 1 successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setPdfandpic(fileName);
        }
        result = documentStorageService.InvoiceDeptInsertServiece(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//    update dept must receieved
//หนี้ต้องส่ง Dept must received insert
@CrossOrigin(origins = "*")
@PostMapping(value = "/DeptMustReceivedUpdate.service" , consumes = {"multipart/form-data"})
public Messages DeptMustReceivedUpdate(
        @RequestParam("key_id") String key_id,
        @RequestParam("document_1") MultipartFile document_1,
        @RequestParam("document_2") MultipartFile document_2,
        @RequestParam("customer_id") String  customer_id,
        @RequestParam("topic") String  topic,
        @RequestParam("type_id") String  type_id,
        @RequestParam("currency") String  currency,
        @RequestParam("datee") String  datee,
        @RequestParam("due_date") String  due_date,
        @RequestParam("reference_number") String  reference_number,
        @RequestParam("lek_bai_sung") String  lek_bai_sung,
        @RequestParam("amount_money") String  amount_money,
        @RequestParam("quotation") String  quotation,
        @RequestParam("description") String  description,
        @RequestParam("note") String  note,
        @RequestParam("num") String  num,
        @RequestParam("unit") String  unit,
        @RequestParam("totalMoney") String  totalMoney,
        @RequestParam("toKen") String  toKen
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        DeptMustReceivedReq data = new DeptMustReceivedReq();
        data.setKey_id(key_id);
        data.setCustomer_id(customer_id);
        data.setTopic(topic);
        data.setType_id(type_id);
        data.setCurrency(currency);
        data.setDate(datee);
        data.setDue_date(due_date);
        data.setReference_number(reference_number);
        data.setLek_bai_sung(lek_bai_sung);
        data.setAmount_money(amount_money);
        data.setQuotation(quotation);
        data.setDescription(description);
        data.setNote(note);
        data.setNum(num);
        data.setUnit(unit);
        data.setTotalMooney(totalMoney);
        data.setToKen(toKen);

        log.error("******file lenght"+document_1);
        log.error(data);
        String fileName = "";
        String fileName2 = "";
        List<String> fileNames = new ArrayList<>();
        List<String> fileNames2 = new ArrayList<>();
        if(document_1 == null || document_2 == null){
            log.warn("************* file name is null ****************");
            data.setDocument_1("http://khounkham.com/images/car/image.jpg");
            data.setDocument_2("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(document_1).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            Arrays.asList(document_2).stream().forEach(file -> {
                fileNames2.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files 1 successfully: " + fileNames );
            log.info("Uploaded the files 2 successfully: " + fileNames2 );
            fileName = StringUtils.join(fileNames, ',');
            fileName2 = StringUtils.join(fileNames2, ',');
            data.setDocument_1(fileName);
            data.setDocument_2(fileName2);
        }
        result = documentStorageService.DeptMustReceivedUpdateServiece(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//update invoice dept
@CrossOrigin(origins = "*")
@PostMapping(value = "/UpdateInvoiceDept.service" , consumes = {"multipart/form-data"})
public Messages InvoiceDeptUpdate(
        @RequestParam("key_id") String key_id,
        @RequestParam("pdfandpic") MultipartFile pdfandpic,
        @RequestParam("date") String  date_invoice,
        @RequestParam("detail") String  detail,
        @RequestParam("amount_of_money") String  amount_of_money,
        @RequestParam("quotation_code") String  quotation_code,
        @RequestParam("toKen") String  toKen,
        @RequestParam("invoice_code") String  invoice_code
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        InvoiceDeptReq data = new InvoiceDeptReq();
        data.setKey_id(key_id);
        data.setDate_invoice(date_invoice);
        data.setDetail(detail);
        data.setAmount_of_money(amount_of_money);
        data.setToKen(toKen);
        data.setInvoice_code(invoice_code);
        data.setQuotation_code(quotation_code);

        log.error("******file lenght"+pdfandpic);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(pdfandpic == null){
            log.warn("************* file name is null ****************");
            data.setPdfandpic("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(pdfandpic).stream().forEach(file -> {
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files 1 successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setPdfandpic(fileName);
        }
        result = documentStorageService.UpdateInvoiceDeptServiece(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//    update Document
@CrossOrigin(origins = "*")
@PostMapping(value = "/UpdateDocument.service" , consumes = {"multipart/form-data"})
public Messages UpdateDocument(
//        @RequestParam("files") MultipartFile files,
//        @RequestParam("branchUser") String  branchUser,
        @RequestParam("documentType") String  documentType,
        @RequestParam("toKen") String  toKen,
        @RequestParam("inboundnumber") String  inboundnumber,
        @RequestParam("outboundnumber") String  outboundnumber,
        @RequestParam("dateExpDoc") String  dateExpDoc,
        @RequestParam("classofdocs") String  classofdocs,
        @RequestParam("bound") String  bound,
        @RequestParam("content_doc") String content_doc,
        @RequestParam("whocarrydoc") String  whocarrydoc,
        @RequestParam("etc") String  etc,
        @RequestParam("lektee") String  lektee,
        @RequestParam("datetakein") String  datetakein,
        @RequestParam("company") String  company,
        @RequestParam("bouang") String  bouang,
        @RequestParam("inside") String  inside,
        @RequestParam("key_id") String  key_id
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        DocumentStorageReq data = new DocumentStorageReq();
//        data.setBranchUser(branchUser);
        data.setDocType(documentType);
        data.setToKen(toKen);
        data.setInboundnumber(inboundnumber);
        data.setOutboundnumber(outboundnumber);
        data.setDateExpDoc(dateExpDoc);
        data.setClassofdocs(classofdocs);
        data.setBound(bound);
        data.setContent_doc(content_doc);
        data.setWhocarrydoc(whocarrydoc);
        data.setEtc(etc);
        data.setLektee(lektee);
        data.setDatetakein(datetakein);
        data.setCompany(company);
        data.setBouang(bouang);
        data.setInside(inside);
        data.setKey_id(key_id);

//        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
//        if(files == null){
//            log.warn("************* file name is null ****************");
//            data.setPdf("http://khounkham.com/images/car/image.jpg");
//        }else {
//            Arrays.asList(files).stream().forEach(file -> {
////                    fileNames.add(mediaUploadService.uploadMediacar(file));
//                fileNames.add(mediaUploadService.uploadPDF(file));
//            });
//            log.info("Uploaded the files successfully: " + fileNames );
//            fileName = StringUtils.join(fileNames, ',');
//            data.setPdf(fileName);
//        }
        result = documentStorageService.UpdateDocument(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//    insert data hole
@CrossOrigin(origins = "*")
@PostMapping(value = "/StoreDataHole.service" , consumes = {"multipart/form-data"})
public Messages InsertDataHole(
        @RequestParam("files") MultipartFile files,
        @RequestParam("holeNumber") String  holeNumber,
        @RequestParam("dataColler") String  dataColler,
        @RequestParam("toKen") String  toKen,
        @RequestParam("full_Name_Hole_number") String  full_Name_Hole_number,
        @RequestParam("branch_id") String  branch_id
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        DataHoleReq data = new DataHoleReq();
        data.setHoleNumber(holeNumber);
        data.setDataColler(dataColler);
        data.setToKen(toKen);
        data.setFull_Name_Hole_number(full_Name_Hole_number);
        data.setBranch_id(branch_id);

        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setPic("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setPic(fileName);
        }
        result = documentStorageService.InsertholedataService(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//file header truck
@CrossOrigin(origins = "*")
@PostMapping(value = "/headerTruckFiles.service" , consumes = {"multipart/form-data"})
public Messages headerTruckFiles(
        @RequestParam("files") MultipartFile files,
        @RequestParam("headtruck_id") String  headtruck_id,
        @RequestParam("toKen") String  toKen,
        @RequestParam("date2") String  date2

){
    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        OnlyFileHeaderTuckReq data = new OnlyFileHeaderTuckReq();
        data.setHeadtruck_id(headtruck_id);
        data.setToKen(toKen);
        data.setDate2(date2);
        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setPicOrFile("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setPicOrFile(fileName);
        }
        result = documentStorageService.HeaderTruckFileService(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
    //file header truck update
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/headerTruckFilesUpdate.service" , consumes = {"multipart/form-data"})
    public Messages headerTruckFilesUpdate(
            @RequestParam("files") MultipartFile files,
            @RequestParam("headtruck_id") String  headtruck_id,
            @RequestParam("key_id_of_file") String  key_id_of_file,
            @RequestParam("date2") String  date2
    ){
        log.info("===================================save header==================================================");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
        String namefile = formatter.format(date);
        Messages result = new Messages();
        try {
            OnlyFileHeaderTuckReq data = new OnlyFileHeaderTuckReq();
            data.setHeadtruck_id(headtruck_id);
//            data.setToKen(toKen);
            data.setKey_id_of_file(key_id_of_file);
            data.setDate2(date2);
            log.error("******file lenght"+files);
            log.error(data);
            String fileName = "";
            List<String> fileNames = new ArrayList<>();
            if(files == null){
                log.warn("************* file name is null ****************");
                data.setPicOrFile("http://khounkham.com/images/car/image.jpg");
            }else {
                Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                    fileNames.add(mediaUploadService.uploadPDF(file));
                });
                log.info("Uploaded the files successfully: " + fileNames );
                fileName = StringUtils.join(fileNames, ',');
                data.setPicOrFile(fileName);
            }
            result = documentStorageService.HeaderTruckFileUpdateService(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
            return  result;
        }
        return  result;
    }
//store task controller
@CrossOrigin(origins = "*")
@PostMapping("/storeTask.service")
public TaskRes taskStore (@RequestBody TaskReq[] taskReq){
    TaskRes result = new TaskRes();
    try{
        result = documentStorageService.InsertTaskService(taskReq);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//store link
@CrossOrigin(origins = "*")
@PostMapping("/storeLinks.service")
public LinkRes taskLink (@RequestBody LinkReq[] linkReq){
    LinkRes result = new LinkRes();
    try{
        result = documentStorageService.InsertLinksService(linkReq);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//update task
@CrossOrigin(origins = "*")
@PostMapping("/UpdateTask.service")
public TaskRes UpdateTask (@RequestBody TaskReq[] taskReq){
    TaskRes result = new TaskRes();
    try{
        result = documentStorageService.UpdateTaskService(taskReq);
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//show result of survey
@CrossOrigin(origins = "*")
@PostMapping("/ShowAllResultOfServey.service")
public ResultOfSurveyRes ShowAllResultOfServey(@RequestBody DataHoleReq dataHoleReq){
    ResultOfSurveyRes result = new ResultOfSurveyRes();
    try {
        result = documentStorageService.AllResultOfSurveyService(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    //show task
    @CrossOrigin(origins = "*")
    @PostMapping("/getShowTask.service")
    public TaskRes getShowTask(@RequestBody TaskReq taskReq ) {
        TaskRes result = new TaskRes();
        try {
            result = documentStorageService.getShowTaskService(taskReq);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exception");
        }
        return result;
    }
//    link
@CrossOrigin(origins = "*")
@PostMapping("/getShowLink.service")
public LinkRes getShowLink(@RequestBody LinkReq linkReq) {
    LinkRes result = new LinkRes();
    try {
        result = documentStorageService.getShowLinksService(linkReq);
    } catch (Exception e) {
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exception");
    }
    return result;
}
//show pic of br
@CrossOrigin(origins = "*")
@PostMapping("/ShowPicOfBor.service")
public PicOfBorhinRes ShowPicOfBor(@RequestBody DataHoleReq dataHoleReq){
    PicOfBorhinRes result = new PicOfBorhinRes();
    try {
        result = documentStorageService.ShowPicOfBorService(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}

//show result of survey by id
@CrossOrigin(origins = "*")
@PostMapping("/ShowResultOfServeyById.service")
public ResultOfSurveyRes ShowAllResultOfServeyById(@RequestBody DataHoleReq dataHoleReq){
    ResultOfSurveyRes result = new ResultOfSurveyRes();
    try {
        result = documentStorageService.AllResultOfSurveyByIdService(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//result of survey
@CrossOrigin(origins = "*")
@PostMapping(value = "/InsertResultOfSurvey.service" , consumes = {"multipart/form-data"})
public Messages InsertResultOfSurvey(
        @RequestParam("files") MultipartFile files,
        @RequestParam("type") String  type,
        @RequestParam("toKen") String  toKen,
        @RequestParam("name") String  name,
        @RequestParam("dateInsert") String  dateInsert,
        @RequestParam("nameDetail") String  nameDetail,
        @RequestParam("branch_id") String  branch_id
){

    log.info("===================================save header==================================================");
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyss");
    String namefile = formatter.format(date);
    Messages result = new Messages();
    try {
        DataHoleReq data = new DataHoleReq();
        data.setType(type);
        data.setToKen(toKen);
        data.setName(name);
        data.setDateInsert(dateInsert);
        data.setNameDetail(nameDetail);
        data.setBranch_id(branch_id);

        log.error("******file lenght"+files);
        log.error(data);
        String fileName = "";
        List<String> fileNames = new ArrayList<>();
        if(files == null){
            log.warn("************* file name is null ****************");
            data.setFile("http://khounkham.com/images/car/image.jpg");
        }else {
            Arrays.asList(files).stream().forEach(file -> {
//                    fileNames.add(mediaUploadService.uploadMediacar(file));
                fileNames.add(mediaUploadService.uploadPDF(file));
            });
            log.info("Uploaded the files successfully: " + fileNames );
            fileName = StringUtils.join(fileNames, ',');
            data.setFile(fileName);
        }
        result = documentStorageService.ResultOfSurveyService(data);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("ບໍ່ສາມາດບັນທຶກໄດ້");
        return  result;
    }
    return  result;
}
//show list of hole
//List show Document all
@CrossOrigin(origins = "*")
@PostMapping("/ShowAllListOfHole.service")
public DataHoleRes ShowAllListOfHole(@RequestBody DataHoleReq dataHoleReq){
    DataHoleRes result = new DataHoleRes();
    try {
        result = documentStorageService.AlllistOffHoleService(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//hole data by key-id
@CrossOrigin(origins = "*")
@PostMapping("/ShowAllListOfHoleByKeyId.service")
public DataHoleRes ShowAllListOfHoleByKeyId(@RequestBody DataHoleReq dataHoleReq){
    DataHoleRes result = new DataHoleRes();
    try {
        result = documentStorageService.AlllistOffHoleByKeyIdService(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//show file header truck
@CrossOrigin(origins = "*")
@PostMapping("/ShowFilesOfHeadertruckKeyId.service")
public FileOfHeaderRes ShowFilesOfHeadertruckKeyId(@RequestBody OnlyFileHeaderTuckReq onlyFileHeaderTuckReq){
    FileOfHeaderRes result = new FileOfHeaderRes();
    try {
        result = documentStorageService.ShowFilesOfHeadertruckKeyIdService(onlyFileHeaderTuckReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}


    // download pdf file
    @PostMapping("loadDocPdf/{fileName:.+}")
    public ResponseEntity<?> createPDF(
            @ApiParam(
                    name = "Body Request",
                    value = "JSON body request to check information",
                    required = true)
            @Valid
            @PathVariable(name = "fileName") String fileName,
            @Context HttpServletRequest request
    ) throws Exception {
        log.info("\t\t --> Download File PDF controller >>>>>>>>>>>>>>>>>>>>>>");
        String clientIpAddress = request.getRemoteAddr();
        log.info("Client IP Address = " + clientIpAddress);
        log.info("File name = " + fileName);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        String fileName = "ReportBorder_" + sf.format(new Date()) + ".pdf";
//        DataResponse response = generateReportToPdfService.createPdfFile();
//        DataResponse response = new DataResponse();
//        response.setStatus("00");
//        if (response.getStatus().equals("00")) {
        try {
            String fullPath = "src/main/resources/images/car/" + fileName + ".pdf";
            File file = new File(fullPath);

            HttpHeaders header = new HttpHeaders();
//                header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fullPath); // User for View file
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fullPath);    // User for download file
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            log.info("\t\t --> End Download File PDF controller <<<<<<<<<<<<<<<<<<<");
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (Exception e) {
//            throw new NotFoundException(
//                    initializeMessage.errorMessage("File image not found !!", "System can not generate file")
//            );
            log.warn("An error occurs while releasing JMS resources", e);
        }
        return null;
    }
//    Download result of survey
@PostMapping("DownloadResultOfSurvey/{fileName:.+}")
public ResponseEntity<?> createPDFresultofsurvey(
        @ApiParam(
                name = "Body Request",
                value = "JSON body request to check information",
                required = true)
        @Valid
        @PathVariable(name = "fileName") String fileName,
        @Context HttpServletRequest request
) throws Exception {
    log.info("\t\t --> Download File PDF controller >>>>>>>>>>>>>>>>>>>>>>");
    String clientIpAddress = request.getRemoteAddr();
    log.info("Client IP Address = " + clientIpAddress);
    log.info("File name = " + fileName);
    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        String fileName = "ReportBorder_" + sf.format(new Date()) + ".pdf";
//        DataResponse response = generateReportToPdfService.createPdfFile();
//        DataResponse response = new DataResponse();
//        response.setStatus("00");
//        if (response.getStatus().equals("00")) {
    try {
        String fullPath = "src/main/resources/images/car/" + fileName + ".pdf";
        File file = new File(fullPath);

        HttpHeaders header = new HttpHeaders();
//                header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fullPath); // User for View file
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fullPath);    // User for download file
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        log.info("\t\t --> End Download File PDF controller <<<<<<<<<<<<<<<<<<<");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    } catch (Exception e) {
//            throw new NotFoundException(
//                    initializeMessage.errorMessage("File image not found !!", "System can not generate file")
//            );
        log.warn("An error occurs while releasing JMS resources", e);
    }
    return null;
}
//download hole data
@PostMapping("DownloadholeDataPic/{fileName:.+}")
public ResponseEntity<?> createPDFholedata(
        @ApiParam(
                name = "Body Request",
                value = "JSON body request to check information",
                required = true)
        @Valid
        @PathVariable(name = "fileName") String fileName,
        @Context HttpServletRequest request
) throws Exception {
    log.info("\t\t --> Download File PDF controller >>>>>>>>>>>>>>>>>>>>>>");
    String clientIpAddress = request.getRemoteAddr();
    log.info("Client IP Address = " + clientIpAddress);
    log.info("File name = " + fileName);
    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        String fileName = "ReportBorder_" + sf.format(new Date()) + ".pdf";
//        DataResponse response = generateReportToPdfService.createPdfFile();
//        DataResponse response = new DataResponse();
//        response.setStatus("00");
//        if (response.getStatus().equals("00")) {
    try {
        String fullPath = "src/main/resources/images/car/" + fileName + ".pdf";
        File file = new File(fullPath);

        HttpHeaders header = new HttpHeaders();
//                header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fullPath); // User for View file
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fullPath);    // User for download file
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        log.info("\t\t --> End Download File PDF controller <<<<<<<<<<<<<<<<<<<");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    } catch (Exception e) {
//            throw new NotFoundException(
//                    initializeMessage.errorMessage("File image not found !!", "System can not generate file")
//            );
        log.warn("An error occurs while releasing JMS resources", e);
    }
    return null;
}
    //List show Document all
    @CrossOrigin(origins = "*")
    @PostMapping("/listDocumentAll.service")
    public DocumentStorageRes listAssetsOffice(@RequestBody DocumentStorageReq documentStorageReq){
        DocumentStorageRes result = new DocumentStorageRes();
        try {
            result = documentStorageService.listDocumentService(documentStorageReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
//    show list dept must received
@CrossOrigin(origins = "*")
@PostMapping("/listDeptMustReceivedAll.service")
public DeptMustReceivedRes listDeptMustReceivedAll(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
        result = documentStorageService.DeptMustReceivedServices(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}

//list of invoice dept
@CrossOrigin(origins = "*")
@PostMapping("/listOfInvoiceDept.service")
public InvoiceDeptRes listOfInvoiceDept(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    InvoiceDeptRes result = new InvoiceDeptRes();
    try {
        result = documentStorageService.InvoiceDeptServices(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//show list name of dept must recieved
@CrossOrigin(origins = "*")
@PostMapping("/listNameOfDept.service")
public ListNameRes listNameOfDept(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    ListNameRes result = new ListNameRes();
    try {
        result = documentStorageService.ListNameDeptServices(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//accept api
@CrossOrigin(origins = "*")
@PostMapping("/AcceptTheReqDeptMustReceive.service")
public DeptMustReceivedRes AcceptTheReqDeptMustReceive(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
        result = documentStorageService.AcceptTheReqDeptMustReceiveService(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//insert name list array
@CrossOrigin(origins = "*")
@PostMapping("/InsertNameListArray.service")
public Messages InsertNameListArrayDeptMustReceive(@RequestBody List<DeptMustReceivedReq> deptMustReceivedRe){
    Messages message = new Messages();
    try {
        message = documentStorageService.DeptMustReceivedInsertListName(deptMustReceivedRe);
    }catch (Exception e){
        e.printStackTrace();
        message.setStatus("01");
        message.setMessage("exeption");
        return message;
    }
    return message;
}
//show dept must recieved detail
@CrossOrigin(origins = "*")
@PostMapping("/listDeptMustReceiveddetail.service")
public DeptMustReceivedRes listDeptMustReceiveddetail(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
        result = documentStorageService.DeptMustReceivedServicesdetail(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//list history customer pay
@CrossOrigin(origins = "*")
@PostMapping("/DeptMustReceivedHistoryCustomer.service")
public CustomerHisPayRes DeptMustReceivedHistoryCustomer(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
    CustomerHisPayRes result = new CustomerHisPayRes();
    try {
        result = documentStorageService.DeptMustReceivedServicesHisCustomer(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
    //search api dept must recieve
    @CrossOrigin(origins = "*")
    @PostMapping("/SearchingDeptMustReceived.service")
    public CustomerHisPayRes SearchingDeptMustReceived(@RequestBody DeptMustReceivedReq deptMustReceivedReq){
        CustomerHisPayRes result = new CustomerHisPayRes();
        try {
            result = documentStorageService.SearchDeptMustReceivedServicesHisCustomer(deptMustReceivedReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    //List show Document search api
    @CrossOrigin(origins = "*")
    @PostMapping("/listDocumentAllBySearch.service")
    public DocumentStorageRes listAssetsOfficeBySearch(@RequestBody DocumentStorageReq documentStorageReq){
        DocumentStorageRes result = new DocumentStorageRes();
        try {
            result = documentStorageService.SearchlistDocumentService(documentStorageReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
    // ບ້ວງ
    @CrossOrigin(origins = "*")
    @PostMapping("/storeBouang.service")
    public BouangRes storeBouang(@RequestBody BouangReq bouangReq){
        BouangRes result =new BouangRes();
        try
        {
            result = documentStorageService.BouangInsert(bouangReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
        }
        return result;
    }
//    rock ship smple insert
@CrossOrigin(origins = "*")
@PostMapping("/storeRockShipSample.service")
public RockShipSampleRes storeRockShipSample(@RequestBody RockShipSampleReq[] rockShipSampleReq){
    RockShipSampleRes result =new RockShipSampleRes();
    try
    {
        result = documentStorageService.StoreRockShipSampleService(rockShipSampleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
//show rock ship
@PostMapping("/ShowRockShipSample.service")
public RockShipSampleRes ShowRockShipSample(@RequestBody RockShipSampleReq rockShipSampleReq){
    RockShipSampleRes result =new RockShipSampleRes();
    try
    {
        result = documentStorageService.ShowRockShipSampleService(rockShipSampleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
//    update bouang
@CrossOrigin(origins = "*")
@PostMapping("/BouangUpdate.service")
public BouangRes BouangUpdate(@RequestBody BouangReq bouangReq){
    BouangRes result =new BouangRes();
    try
    {
        result = documentStorageService.BouangUpdate(bouangReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
//    show bouang all
@CrossOrigin(origins = "*")
@PostMapping("/getBouangAll.service")
public BouangRes getBouangAll(@RequestBody BouangReq bouangReq){
    BouangRes result =new BouangRes();
    try
    {
        result = documentStorageService.getBouangAllServieces(bouangReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
    }
    return result;
}
//del bouang
@CrossOrigin(origins = "*")
@PostMapping("/DelBouang.service")
public BouangRes DelBouangByID (@RequestBody BouangReq bouangReq) {

    BouangRes result = new BouangRes();
    try {
        result = documentStorageService.DelBouangServiece(bouangReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}

    //show Document detail
    @CrossOrigin(origins = "*")
    @PostMapping("/listDocumentDetail.service")
    public DocumentStorageRes DocumentDetail(@RequestBody   DocumentStorageReq documentStorageReq){
        DocumentStorageRes result = new DocumentStorageRes();
        try {
            result = documentStorageService.DocDetailById(documentStorageReq);
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus("01");
            result.setMessage("exeption");
            return result;
        }
        return result;
    }
//    delete Document
@CrossOrigin(origins = "*")
@PostMapping("/DelDocumentByID.service")
public DocumentStorageRes DelDocumentByID (@RequestBody DocumentStorageReq documentStorageReq) {

    DocumentStorageRes result = new DocumentStorageRes();
    try {
        result = documentStorageService.DelDocumentByID(documentStorageReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//delete dept must recieve list only status N
@CrossOrigin(origins = "*")
@PostMapping("/DelDeptMustReceivedtByID.service")
public DeptMustReceivedRes DelDeptMustReceivedtByID (@RequestBody DeptMustReceivedReq deptMustReceivedReq) {

    DeptMustReceivedRes result = new DeptMustReceivedRes();
    try {
        result = documentStorageService.DelDeptMustReceivedtByIDServiece(deptMustReceivedReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//delete hole by id
@CrossOrigin(origins = "*")
@PostMapping("/DelHoleByID.service")
public DataHoleRes DelHoleByID (@RequestBody DataHoleReq dataHoleReq) {

    DataHoleRes result = new DataHoleRes();
    try {
        result = documentStorageService.DelHoleByIDServiece(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
//delete result of survey
@CrossOrigin(origins = "*")
@PostMapping("/DelResultOfSurveyByID.service")
public DataHoleRes DelResultOfSurveyByID (@RequestBody DataHoleReq dataHoleReq) {

    DataHoleRes result = new DataHoleRes();
    try {
        result = documentStorageService.DelResultOfSurveyByIDServiece(dataHoleReq);
    }catch (Exception e){
        e.printStackTrace();
        result.setStatus("01");
        result.setMessage("exeption");
        return result;
    }
    return result;
}
}
