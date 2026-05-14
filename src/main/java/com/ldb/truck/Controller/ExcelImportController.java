package com.ldb.truck.Controller;

import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.RegisterService.ExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${base_url}")
@RequiredArgsConstructor
public class ExcelImportController {

    private final ExcelImportService excelImportService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateStaff-Excel",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataResponse> importStaff(
            @RequestParam("token") String token,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(
                excelImportService.importStaffFromExcel(token, file));
    }
}
