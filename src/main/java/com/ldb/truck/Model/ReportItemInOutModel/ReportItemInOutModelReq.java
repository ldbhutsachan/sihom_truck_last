package com.ldb.truck.Model.ReportItemInOutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportItemInOutModelReq {
    String startDate;
    String endDate;
    String itemId;
    String borNo;
    String toKen;
}
