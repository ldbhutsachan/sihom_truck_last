package com.ldb.truck.Model.Login.ReportStaff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopFiveRankingRes {
    private String status;
    private String message;
    private List<ReportStaffRanking> data;
}
