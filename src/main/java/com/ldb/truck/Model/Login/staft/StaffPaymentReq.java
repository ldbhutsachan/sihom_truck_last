package com.ldb.truck.Model.Login.staft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffPaymentReq {
    private String key_Id;
    private String payDate;
    private String staffID01;
    private String staffID02;
    private String payAmount_NotPay01;
    private String payAmount_NotPay02;
    private String payAmount_Pay01;
    private String payAmount_Pay02;
    private String payAmount_TotalPay01;
    private String payAmount_TotalPay02;
    private String payAmount_status01;
    private String payAmount_status02;
    private String toKen;
    private String branch;
    private String userId;
    private String productID;
    private String endDate;
    private String startDate;

    private List<String> dels;
    private List<String> keyIds;
    private String allofthem;
}
