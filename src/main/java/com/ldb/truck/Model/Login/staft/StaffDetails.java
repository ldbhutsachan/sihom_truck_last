package com.ldb.truck.Model.Login.staft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDetails {
    private String staffID;
    private String staffName;
    private String staffSurname;
    private String idCard;
    private String licenId;
    private String idCardExpried;

    private String keyId;
    private String headerNo;
    private String footerNo;
    private String cusName;

    private String proNo;
    private String proName;
    private String placeName;
    private String provinceName;
    private String startDate;
    private String endDate;

}
