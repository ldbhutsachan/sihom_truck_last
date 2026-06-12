package com.ldb.truck.Entity.RequestItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItem {
    private int item;
    private BigDecimal qty;
    private String type;
    private String borNo;
    private String mchNo;
}
