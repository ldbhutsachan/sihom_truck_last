package com.ldb.truck.Entity.ItemPayment;

import lombok.Data;

@Data
public class itemInfo {
    private Integer itemNo;
    private Integer amt;

    @Override
    public String toString() {
        return "itemNo=" + itemNo + ", amt=" + amt;
    }

}
