package com.ldb.truck.Model.Login.Dept_Must_Receive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListNameRes {
    private String status;
    private String message;
    private List<ListNameModel> data;
    private summingTotalMoney sumfooter;
}
