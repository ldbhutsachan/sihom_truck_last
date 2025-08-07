package com.ldb.truck.Model.ReportItemInOutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class details_item_req {
    String toKen;
    String billNo;
    String showByCondition;
    String status;


    String startDate;
    String endDate;
    String borNo;
}
