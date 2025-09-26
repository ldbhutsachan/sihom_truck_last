package com.ldb.truck.Entity.RequestItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItem {
    private int item;
    private int qty;
    private String type;
    private String borNo;
    private String mchNo;
}
