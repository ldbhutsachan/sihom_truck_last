package com.ldb.truck.Model.Bansi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AccountingReportModel {

    private Integer keyId;

    private Integer bansiId;

    private Date date_create;

    private Date basi_approve_date;

    private Integer bigProjectId;

    private String bigProject;

    private Integer smallProjectId;

    private String smallProject;

    private Integer payTypeId;

    private String payType;

    private String typeOf;

    private String supplierName;

    private String bunsiName;

    private String title;

    private String exchangeRate;

    private Date datermineDate;

    private String referenceNumber;

    private String reference;

    private String remark;

    private String internalRemark;

    private String tag;

    @JsonIgnore // ⬅️ จะไม่ส่ง field นี้ไป client
    private String file;
    private List<String> fileList; // ใหม่: เก็บหลายไฟล์

    private String billNo;

    private String bill_status;

    private String currency;

    private Double price;

    private Double usd_price;

    private String role;

    private String bank_account_name;
    private String bank_account_no;
    private String bank_name;
}
