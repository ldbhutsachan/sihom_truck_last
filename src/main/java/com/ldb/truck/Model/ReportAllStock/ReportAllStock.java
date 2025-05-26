package com.ldb.truck.Model.ReportAllStock;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportAllStock {
    private String itemName;

    private String headerNo;
    private String footNo;
    private String txnDateIn;
    private String txnDateOut;

    private Integer amt;
    private String price;
    private String total;
    private Integer amtIn;
    private String priceIn;
    private String totalIn;
    private Integer amtOut;
    private String priceOut;
    private String totalOut;

}
