package com.ldb.truck.Model.ReportAllStock;

import com.ldb.truck.Model.ReportInoutItem.ReportInoutItemGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//public class ReportAllStockInOutRes {
//    private String status;
//    private String message;
//    public List<ReportAllStockInOut> data;
//   // ReportInoutItemGroup groupFooter;
//}

//vee
public class ReportAllStockInOutRes {
    private String status;
    private String message;
    private List<ReportAllStockGroup> data;
    private SumReportAll sumReport;
}
