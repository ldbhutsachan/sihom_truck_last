package com.ldb.truck.Entity.ItemPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPaymentReq {
    private String toKen;
    private String status;
    private Float amountPayment;
    private Integer percentPayment;

    private String startDate;
    private String endDate;
    private String borNumber;





}
