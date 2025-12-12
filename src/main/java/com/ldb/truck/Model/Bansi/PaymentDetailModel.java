package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDetailModel {
    private Long keyId;
    private String billNo;
    private String bill_status;
    private String date_create;
//    private String bansi;
    private String title;
    private String currency;
    private Double exchangeRate;
    private String date;
    private String datermineDate;
    private String reference;
    private String referenceNumber;
    private String remark;
    private String internalRemark;
    private String tag;
    private String file;
    private Long   payId;
    private String paytype;
    private Long   req_id;
    private String smallProject;
    private Long   itemTypeid;
    private String bigProject;
    private String supplierid;
    private String supplier_name;
    private String data_type;
    private String user;
    private String basi_approve_date;
    private String bansi_approveby;
    private String account_approve_date;
    private String account_approveby;
//    private String final_approve_date;
//    private String final_approveby;
    private String returnby;
    private String return_date;
    private String account_name;
    private String account_no;
    private String bank_name;
    // add field for list
    private List<PaymentDetailListModel> listItems;
}
