package com.ldb.truck.Model.Login.ReportStaff;

import com.ldb.truck.Model.Login.Report.Bialieng.sumFooterGroup2;
import com.ldb.truck.Model.Login.Report.sumFooterGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportStaffRes {
    private String status;
    private String message;
    private List<ReportStaff> data;
    private sumFooterGroup2 sumFooter;
}
