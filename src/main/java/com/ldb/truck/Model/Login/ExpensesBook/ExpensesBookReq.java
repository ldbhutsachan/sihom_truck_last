package com.ldb.truck.Model.Login.ExpensesBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesBookReq {
    private String key_id;
    private String exPType;
    private String exPName;
    private String toTal;
    private String amount;
    private String expDate;
    private String cDate;
    private String perAmount;
    private String ref_NO;
    private String status;
    private String userId;
    private String toKen;
    private String branch;


}
