package com.ldb.truck.Controller;

import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.RegisterService.ExcelImportService;
import com.ldb.truck.Model.StaffStatement.StaffStatementReq;
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
        @PostMapping(value = "/updateStaff-Excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<DataResponse> importStaff(
                        @RequestParam("token") String token,
                        @RequestParam("file") MultipartFile file) {
                return ResponseEntity.ok(
                                excelImportService.importStaffFromExcel(token, file));
        }

        @CrossOrigin(origins = "*")
        @PostMapping(value = "/upload_statement", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<DataResponse> uploadStatement(
                        @RequestParam("token") String token,
                        @RequestParam("title") String title,
                        @RequestParam("statement_date") String statementDate,
                        @RequestParam("file") MultipartFile file) {
                return ResponseEntity.ok(
                                excelImportService.uploadStatement(token, title, statementDate, file));
        }

        @CrossOrigin(origins = "*")
        @PostMapping(value = "/list_staff_statement")
        public ResponseEntity<DataResponse> listStaffStatementData(
                        @RequestHeader(value = "token", required = false) String token,
                        @RequestBody StaffStatementReq req) {
                return ResponseEntity.ok(excelImportService.listStaffStatementData(token, req));
        }

        @CrossOrigin(origins = "*")
        @PostMapping(value = "/update_statement")
        public ResponseEntity<DataResponse> updateStaffStatement(
                        @RequestHeader(value = "token", required = false) String tokenHeader,
                        @RequestBody com.ldb.truck.Model.StaffStatement.StaffStatementUpdateReq req) {
                // Support token in both header and body (assuming old APIs sometimes passed it
                // differently, but for JSON req we'll prioritize Header, else you could add
                // token to UpdateReq if needed, but here we expect it in Header or query if you
                // want. Wait, I'll just use tokenHeader)
                String actualToken = (tokenHeader != null && !tokenHeader.trim().isEmpty()) ? tokenHeader
                                : req.getToken();
                return ResponseEntity.ok(excelImportService.updateStaffStatement(actualToken, req));
        }
}
