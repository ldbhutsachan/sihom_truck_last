package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDetailModel {
    private Long keyId;
    private String billNo;
    private String bansi;
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
    private String supplier_name;
    private String user;
    // add field for list
    private List<PaymentDetailListModel> listItems;
}
