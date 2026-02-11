package com.ldb.truck.Dao.ItemList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class modelItemList {
    private String itemId;
    private String item_name;
    private String image;
    private String borNo;
    private String borName;
    private String size;
    private String qty;
    private String khid;
    private String khno;
    private String khname;
}
