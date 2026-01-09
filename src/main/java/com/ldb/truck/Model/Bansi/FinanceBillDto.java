package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class FinanceBillDto {
    private String financeBill;         // ชื่อหรือเลขของ Finance Bill
    private List<String> billNos = new ArrayList<>(); // รายการ billNo
}
