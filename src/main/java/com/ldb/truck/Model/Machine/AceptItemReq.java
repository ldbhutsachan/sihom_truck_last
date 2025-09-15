package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.util.List;

@Data
public class AceptItemReq {
    private String toKen;
    private String billNo;
    private String usingBy;
    private List<Integer> itemList;
}
