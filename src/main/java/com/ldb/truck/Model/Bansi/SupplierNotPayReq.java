package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class SupplierNotPayReq {
    private String token;
    private String startDate;
    private String endDate;
    private String typeOf;
    private String supplierId;
}
