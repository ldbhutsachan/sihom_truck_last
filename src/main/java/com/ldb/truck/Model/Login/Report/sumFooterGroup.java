package com.ldb.truck.Model.Login.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class sumFooterGroup {
    private String totalBiaLieng ;
    private String totalSopher ;
    private String TotalBiaLiengAndlaiJaiyOutFrist ;
    private String totalNummun ;
    private String totalLidFuel ;
    private String todtalLaiyJaiyFrist;
    private String todtalLaiyJaiySecond;
    private String totalPriceFuel;
    private String totalPriceNammun;
    private String laiJaiyOutTotal;
    private String laiJaiyOutFrist;
    private String biaOutWasted;

   // ==================== sum ค่าสิ้นเปือง===========================
   private String RunningTotal;
   // ==================== sum ค่าสิ้นเปือง===========================




    private String totalstaff02_payAll ;
    private String totalstaff02_beforepay ;

    private String allLaiyJaiy ;
    private String alltotalBialiengKangjaiy ;
}
