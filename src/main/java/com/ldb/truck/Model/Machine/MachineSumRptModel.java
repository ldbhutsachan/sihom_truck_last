package com.ldb.truck.Model.Machine;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class MachineSumRptModel {
    private Integer mchId;
    private String mchNo;
    private String mchName;
    Collection<GroupItemList> groupItemList = null;

    @Data
    public static class  GroupItemList{
        private String borNo;
        private String borName;
        private String itemId;
        private String itemName;
        private Integer qty;
        private Double price;
        private Double total;
    }
}
