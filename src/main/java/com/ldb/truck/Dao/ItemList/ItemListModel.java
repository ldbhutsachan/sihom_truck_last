package com.ldb.truck.Dao.ItemList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemListModel {
    private String itemId;
    private String item_name;
    private Float price;
    private Integer inqty;
    private String image;
    private String borNo;
    private String borName;
    private String itemtype_Name;
    private Integer qty;
    private String unit;
    private String size;
    private String khid;
    private String khno;
    private String khname;

}
