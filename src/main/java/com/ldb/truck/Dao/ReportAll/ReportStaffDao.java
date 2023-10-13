package com.ldb.truck.Dao.ReportAll;

import com.ldb.truck.Model.Login.ReportStaff.ReportStaff;
import com.ldb.truck.Model.Login.ReportStaff.ReportStaffReq;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReportStaffDao {
    List<ReportStaff> ReportDetails(ReportStaffReq detailsReq);
    List<ReportStaff> ReportDetailsStaff(@RequestBody ReportStaffReq detailsReq);

}
