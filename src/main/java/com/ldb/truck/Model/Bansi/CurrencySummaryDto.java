package com.ldb.truck.Model.Bansi;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencySummaryDto {
    private BigDecimal sumMustPayLAK = BigDecimal.ZERO;
    private BigDecimal sumPayLAK = BigDecimal.ZERO;
    private BigDecimal sumNextPayLAK = BigDecimal.ZERO;

    private BigDecimal sumMustPayTHB = BigDecimal.ZERO;
    private BigDecimal sumPayTHB = BigDecimal.ZERO;
    private BigDecimal sumNextPayTHB = BigDecimal.ZERO;

    private BigDecimal sumMustPayUSD = BigDecimal.ZERO;
    private BigDecimal sumPayUSD = BigDecimal.ZERO;
    private BigDecimal sumNextPayUSD = BigDecimal.ZERO;

    public void calculateNextPay() {
        sumNextPayLAK = sumMustPayLAK.subtract(sumPayLAK);
        sumNextPayTHB = sumMustPayTHB.subtract(sumPayTHB);
        sumNextPayUSD = sumMustPayUSD.subtract(sumPayUSD);
    }
}
