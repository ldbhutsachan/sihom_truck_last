package com.ldb.truck.Model.Login.ReportStaff;

import com.ldb.truck.Model.Login.Report.Bialieng.sumFooterGroup2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmountthatPaidStaffRes {
    private String status;
    private String message;
    private List<AmountThatPaidStaffModel> data;
    private sumfooterGroupBL sumFooter;
}
