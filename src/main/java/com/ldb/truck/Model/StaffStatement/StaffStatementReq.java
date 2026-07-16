package com.ldb.truck.Model.StaffStatement;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StaffStatementReq {
    private String staffId;
    private String title;
    private MultipartFile[] files;
    private Integer borId;
    private Long deptId;
    private String token; // Accept "token" from JSON
    private String startDate;
    private String endDate;
}
