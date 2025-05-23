package com.ldb.truck.Model.ReportAllStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllStockRes {
    private String status;
    private String message;
    public List<ReportAllStock> data;

}
