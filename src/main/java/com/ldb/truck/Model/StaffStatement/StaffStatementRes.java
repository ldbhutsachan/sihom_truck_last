package com.ldb.truck.Model.StaffStatement;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffStatementRes {
    private Long id;
    private Integer staffId;
    private String title;
    private String files;
    private String saveBy;
    private String createDate;
    
    // Staff details
    private String username;
    private String laoName;
    private String staffCode;
    private Long deptId;
    private String deptName;
    private Long posId;
    private String posName;
    private Integer borId;
    private String bName;
}
