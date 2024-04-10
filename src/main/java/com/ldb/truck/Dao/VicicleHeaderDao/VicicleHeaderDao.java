package com.ldb.truck.Dao.VicicleHeaderDao;

import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.Report.ReportHeaderReq;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeader;
import com.ldb.truck.Model.Login.VicicleHeader.VicicleHeaderReq;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.ldb.truck.Model.Login.Report.ReportHeader;

import java.text.ParseException;
import java.util.List;
public interface VicicleHeaderDao {
    public List<VicicleHeader> listVicicleHeader (VicicleHeaderReq vicicleHeaderReq);
    public List<VicicleHeader> listVicicleHeaderByID (VicicleHeaderReq vicicleHeaderReq);
    public int saveVicicleHeader (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int updateVicicleHeader (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int updateVicicleHeaderUppicHaveData (VicicleHeaderReq vicicleHeaderReq) throws ParseException;
    public int delVicicleHeader (VicicleHeaderReq vicicleHeaderReq);

    public int saveHeaderHistroty(VicicleHeaderReq vicicleHeaderReq);
    List<VicicleHeader> ReportHistoryHeader(ReportAllReq vicicleHeaderReq);
    List <VicicleHeader> listVicicleHeaderCombox1(VicicleHeaderReq vicicleHeaderReq);
    List<ReportHeader> listReportHeader(ReportHeaderReq reportHeaderReq);

}
