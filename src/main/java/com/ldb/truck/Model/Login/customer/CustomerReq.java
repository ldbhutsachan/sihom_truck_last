package com.ldb.truck.Model.Login.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReq {
    private int id;
    private String customerId;
    private String toKen;
    private String userId;
    private String branch;
    private String customerName;
    private String address;
    private String vilage;
    private String district;
    private String province;
    private String mobile;
    private String mobile1;
    private String email;
}
