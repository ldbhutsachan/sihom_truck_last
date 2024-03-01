package com.ldb.truck.Model.Login.ExpensesBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesBookRes {
    private String status;
    private String message;
    private List<ExpensesBook> data;

    private String totalPay;
    private String totalIncome;
    private String totalIncome_PayAll;
}
