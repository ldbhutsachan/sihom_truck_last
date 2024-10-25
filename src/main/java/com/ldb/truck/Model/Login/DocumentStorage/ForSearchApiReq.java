package com.ldb.truck.Model.Login.DocumentStorage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ForSearchApiReq {
    private String company;
    private String license;
    private String typeDoc;
    private String inside;
    private String branch;
    private String userId;
    private String toKen;
}
