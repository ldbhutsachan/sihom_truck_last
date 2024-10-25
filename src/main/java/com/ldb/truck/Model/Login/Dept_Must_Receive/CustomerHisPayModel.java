package com.ldb.truck.Model.Login.Dept_Must_Receive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerHisPayModel {

    private String  customerName;
    private String  currency;
    private String  date;
    private Double  amount_money;
    private Double  money_y_luea;
    private Double  money_T_jaiy;
}
