package com.ldb.truck.Entity.RequestItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItems {
    private String toKen;
    private String billNo;
    private List<RequestItem> itemId;
}