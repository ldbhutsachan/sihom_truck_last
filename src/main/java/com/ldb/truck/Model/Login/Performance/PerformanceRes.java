package com.ldb.truck.Model.Login.Performance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRes {
    private String status;
    private String message;
  //  private List<Performance> data;
private List<performance_SmallHeaderGruop> performanceSmallHeaderGruops;
    private List<performanceheaderGruop> performanceheaderGruops;
    private List<performaceGroupDetails> performaceGroupDetails;
    private List<performanceGroupFee> performaceGroupFee;
    private List<performance_FeePower> performance_GroupFeePower;

}
