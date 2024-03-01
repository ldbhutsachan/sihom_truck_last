package com.ldb.truck.Model.Login.Batery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BateryReq {
    private  String keyId;
    private  String toKen;
    private  String branch;
    private  String userId;
    private String batNo;
    private String imageBatery;
    private String modalMorfai;
    private String sizeMorfai;
    private String serviceLife;
}
