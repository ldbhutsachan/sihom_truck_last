package com.ldb.truck.Entity.ItemPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemMoveReq {

    private String billNo;
    List<itemInfo> itemMoveList;

}