package com.ldb.truck.Model.Login.CarOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBorReq {
    private String KEY_ID;
    private String img;
    private String borNo;
    private String branch;
    private String userId;
    private String toKen;
}
