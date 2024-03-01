package com.ldb.truck.Model.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResFromDateReq {

    private  String branch;
    private  String startDate;
    private String endDate;
}
