package com.ldb.truck.Model.Login.ExchangeRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
    private String key_Id;
    private String UserId;
    private String userNam;
    private String exchangeDate;
    private String txn_Lak;
    private String txn_Usd;
    private String txn_Thb;
    private String txn_Cny;
}
