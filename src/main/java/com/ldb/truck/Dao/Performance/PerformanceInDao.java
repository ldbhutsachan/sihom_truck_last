package com.ldb.truck.Dao.Performance;
import com.ldb.truck.Model.Login.Details.DetailsReq;
import com.ldb.truck.Model.Login.Performance.*;
import com.ldb.truck.Model.Login.Report.ReportAll;
import com.ldb.truck.Model.Login.Report.ReportAllReq;
import org.springframework.web.bind.annotation.RequestBody;


//import com.ldb.truck.Model.Login.Performance.
import javax.swing.plaf.PanelUI;
import java.util.List;

public interface PerformanceInDao {
    public int updateDetailsFooterKM(DetailsReq detailsReq);
    public int updateDetailsHeaderKM(DetailsReq detailsReq);
    public int updateDetailsAmount(PerformanceReq performanceReq);
    public int updateStaffNum01(PerformanceReq performanceReq);
    public int updateStaffNum02(PerformanceReq performanceReq);
    List<v_performance> v_popupPerformance();
    List<ReportAll> viewPopup();
    public int storePerformanceHis(PerformanceReq performanceReq);
    public List<generateKeyID> genKeyID();
    public int updateDetailsFooter(PerformanceReq performanceReq);
    public int updateDetailsHeader(PerformanceReq performanceReq);
    List<getBillNo> getBillNo(getBillNoReg getBillNoReg);
    public  int savePerformance (PerformanceReq performanceReq);
    public  int updateDetails(PerformanceReq performanceReq);
    List<performanceheaderGruop> gruopperformance(PerformanceReq performanceReq);
    List<performaceGroupDetails> gruoperDetails(PerformanceReq performanceReq);
    List<performanceGroupFee> gruoperFee(PerformanceReq performanceReq);

    List<performance_FeePower> groupFeePower(PerformanceReq performanceReq);

    List<performance_SmallHeaderGruop> groupSmallGroup(PerformanceReq performanceReq);

    List<v_performance> ListV_performance();

    List<v_performance> ListV_performancebyBillNo(PerformanceReq performanceReq);
}
