package com.ldb.truck.Dao.VicicleFooterDao;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooter;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterReq;
import com.ldb.truck.Model.Login.VicicleFooter.VicicleFooterRes;
import java.util.List;

public interface VicicleFooterInfDao {
    public List<VicicleFooter> ListVicicleFooter (VicicleFooterReq vicicleFooterReq, String uMission);
    public List<VicicleFooter> ListVicicleFooterByID(VicicleFooterReq vicicleFooterReq, String uMission);
    public int saveVicicleFooter (VicicleFooterReq vicicleFooterReq);
    public int delVicicleFooter (VicicleFooterReq vicicleFooterReq);
    public int updateVicicleFooter(VicicleFooterReq vicicleFooterReq);
    List<VicicleFooter> ReportFooter(ReportAllReq reportAllReq);
    List<VicicleFooter> ListVicicleFooterCombo1(VicicleFooterReq vicicleFooterReq);

    public int saveVicicleFooterHistory (VicicleFooterReq vicicleFooterReq);



}
