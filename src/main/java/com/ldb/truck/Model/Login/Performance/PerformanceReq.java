package com.ldb.truck.Model.Login.Performance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReq {
    private String key_id;
    private String toKen;
    private String userId;
    private String branch;
    private String performanceBillNo;
    private String performanceReDate;
    private String performanceDate;
    private String performanceTotal;
    private String lahudBaiPoy;
    private String productId;
    private String custormerId;

    private String performanceOvertime;
    private String performanceJumPho;
    private String performanceFeePolish;
    private String performanceFeeTaxung;
    private String performanceFeeTiew;
    private String performanceOverVN;
    private String performanceBoderLak20;
    private String performancePassport;
    private String performanceVaccine;
    private String performanceFeeSing;
    private String  performanceFeeSaPhan;
    private String performanceFeeYoktu;
    private String performanceFeeOutContainer;
    private String feeUnit;
    private String feeTotal;
    private String per_PA;
    private String last_LEKMAI;
    private String  saiNumMun;
    private String  priceNumMun;
    private String totalNumMun;
    private String  kimKiLo;
    private String  LaiYaThang;
    private String  lekMai;
    private String check_KM;
    private String status;
    private String proAmount;
    private String proQty;
    private String proTotalAmount;
    private String proSize;
    private String currency;
    private String staff_Curr;
    private String total_PRICE;
    private String dpay_Money;

    //new add
    private String add_feeOvertime1;
    private String add_feeJumPo2;
    private String add_feePolish3;
    private String add_feeTaxung4;
    private String add_feeTiew5;
    private String add_feesing;
    private String add_feesaphan;
    private String add_feeyoktu;
    private String add_feecontrainer;
    private String add_feepayang;
    private String lAHUD_POYLOD;
}
