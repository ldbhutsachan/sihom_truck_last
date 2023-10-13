package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwePayBackReq {
    private String cusId;
    private String cusName;
    private String billNo;
    private String payDate;
    private String amount;
    private String payAmount;
    private String payStatus;
}
