package com.ldb.truck.Model.Login.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportFuel {
    private String del;
    private String key_id;
    private String keyIds;
    private String plateTruckHead;
    private String dateFillFuel;
    private Double lidFuel;
    private Double totalLidFuel;
    private String pumpName;
    private String village;
    private String district;
    private String province;
    private Double prizPerLid;
    private Double totalPrizeFuel;
    private Double totalPrizeFuelAll;
    private String status_fuel;
}
