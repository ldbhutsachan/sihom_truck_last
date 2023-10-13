package com.ldb.truck.Model.Login.staft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffPayment {
    private String payDate;
    private String staffID01;
    private String staffID02;
    private String staffSurname01;
    private String staffSurname02;
    private String payAmount_NotPay01;
    private String payAmount_NotPay02;
    private String payAmount_Pay01;
    private String payAmount_Pay02;
    private String payAmount_TotalPay01;
    private String payAmount_TotalPay02;
    private String payAmount_status01;
    private String payAmount_status02;


}
