package com.ldb.truck.Entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {
    private String startDate;
    private String endDate;
    private String status;
    private String toKen;
}
