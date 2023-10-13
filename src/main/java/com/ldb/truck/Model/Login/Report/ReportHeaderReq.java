package com.ldb.truck.Model.Login.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportHeaderReq {
    private  String startDate;
    private String endDate;
}
