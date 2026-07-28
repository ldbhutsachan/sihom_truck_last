package com.ldb.truck.Model.Bansi;

import lombok.Data;

@Data
public class FinanceDiscountFilterDto {
    private String toKen;
    private String startDate;
    private String endDate;
    private String billNo;
    private Long billId;
}
