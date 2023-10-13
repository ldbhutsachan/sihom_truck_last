package com.ldb.truck.Model.Login.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllRes {
    private String status;
    private String message;
    private List<ReportAll> data;
}
