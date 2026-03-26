package com.ldb.truck.Entity.Item;

import java.math.BigDecimal;

public interface ItemListView {

    String getItemId();
    String getItemName();
    String getUnit();
    String getImage();
    String getBorNo();
    String getBorName();
    String getSize();
    BigDecimal getQty();
    String getKhid();
    String getKhno();
    String getKhname();
    String getShopId();
    String getShopName();
    String getOrderType();
    String getCurrency();
    String getExchangeRate();
    BigDecimal  getPrice();


}
