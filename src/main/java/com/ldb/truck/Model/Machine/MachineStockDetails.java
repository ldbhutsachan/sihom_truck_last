package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.Date;

@Data
public class MachineStockDetails {
    private Integer keyId; // key_id
    private String mchNo; // mch_no
    private Date createDate; // create_date
    private String createBy; // create_by
    private Integer timeTotal; // time_total
    private Date txnDate; // txn_date
    private Integer status; // status
    private String mchName; // mch_name
    private String ccy; // mch_name
    private String mchBranchName; // mch_branch_name
    private String mchModel; // mch_model
    private Integer mchProductYear; // mch_product_year

    private String saveDate;
    private String saveBy;
    private String approveDate;
    private String approveBy;

    private String borNo; // bor_no
    private String borName; // bor_name
    private String billNo; // bill_no
    private String itemId; // item_name
    private String itemName; // item_name
    private String unit; // unit
    private String currency; // currency
    private Integer qty; // qty
    private Double price; // price
    private Double total; // total
    private String usingStatus; // using_status
}
