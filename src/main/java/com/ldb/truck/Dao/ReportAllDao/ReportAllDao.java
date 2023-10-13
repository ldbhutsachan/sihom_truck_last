package com.ldb.truck.Dao.ReportAllDao;

import java.util.List;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReportAllDao {
    List<ReportAll> ListAllReport_product(@RequestBody  ReportAllReq reportAllReq);
    List<ReportAll> ListAllReport (ReportAllReq reportAllReq);
    List<ReportAll> ListAllReportCustomer(@RequestBody ReportAllReq reportAllReq);
    List<ReportAll> ListAllReportProduct(@RequestBody  ReportAllReq reportAllReq);
}
