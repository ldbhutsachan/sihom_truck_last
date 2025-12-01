package com.ldb.truck.Model.Bansi;

import lombok.Data;
import java.util.Date;

@Data
public class AccountingReportModel {

    private Integer keyId;

    private Integer bansiId;

    private Date date_create;

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

    private Date date;

    private Date datermineDate;

    private String referenceNumber;

    private String reference;

    private String remark;

    private String internalRemark;

    private String tag;

    private String file;

    private String billNo;

    private String currency;

    private Double price;

    private Double usd_price;

    private String role;
}
