package com.ldb.truck.Dao.OweDao;

import com.ldb.truck.Model.Login.Owe.*;
import com.ldb.truck.Model.Login.Payment.GenerateInvoiceID;
import com.ldb.truck.RowMapper.Performance.GetBillNoMapper;
import com.ldb.truck.Model.Login.ResFromDateReq;
import java.util.List;

public interface OweImDao {
    List<Owe> ListOweDetails();

    List<OwePayBack> OweReportByDate(ResFromDateReq resFromDateReq);

    List<sumOweFooter> getSumfooterByDate(ResFromDateReq resFromDateReq);

    List<OwePayBack> OweReport(ResFromDateReq resFromDateReq);
    List<sumOweFooter> getSumfooter(ResFromDateReq resFromDateReq);
    public List<GroupCurr> getGroupCurrSumfooter(ResFromDateReq resFromDateReq);


}
