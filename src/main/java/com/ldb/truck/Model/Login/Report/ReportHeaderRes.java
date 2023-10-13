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
public class ReportHeaderRes {
    private String status;
    private String message;
    private List<ReportHeader> data;
}
