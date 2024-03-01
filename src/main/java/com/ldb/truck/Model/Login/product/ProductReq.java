package com.ldb.truck.Model.Login.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {
    private String id;
    private String proId;
    private String proName;
    private String proType;
    private String proUrl;
    private String userId;
    private String toKen;
    private String branch;
    private String saveById;

}
