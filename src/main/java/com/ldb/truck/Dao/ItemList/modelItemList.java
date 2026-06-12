package com.ldb.truck.Dao.ItemList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class modelItemList {
    private String itemId;
    private String item_name;
    private String unit;
    private String image;
    private String borNo;
    private String borName;
    private String size;
    private BigDecimal qty;
    private String khid;
    private String khno;
    private String khname;
    private String shopid;
    private String shopName;
    private String orderType;
    private String currency;
    private String exchangeRate;
    private BigDecimal price;
}
