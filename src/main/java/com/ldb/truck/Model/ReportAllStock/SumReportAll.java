package com.ldb.truck.Model.ReportAllStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SumReportAll {
    private int totalRaised;
    private int totalIn;
    private int totalOut;
    private int totalClosing;
    private double totalOutPriceUSD;
    private double totalOutPriceLAK;
    private double totalOutPriceTHB;
}
