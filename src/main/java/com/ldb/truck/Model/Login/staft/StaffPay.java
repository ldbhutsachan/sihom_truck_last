package com.ldb.truck.Model.Login.staft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffPay {
    private String staffID;
    private String staffName;
    private String staffSurname;
    private String staff02_PayAll;
    private String staff02_Beforepay;
    private String staff02_Notpay;
    private String totalRow;

    private String idCard;
    private String licenId;
    private String vill;
    private String dis;
    private String pro;

}
