package com.ldb.truck.Model.Login.ExchangeRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateReq {
    private String key_Id;
    private String UserId;
    private String exchangeDate;

    private String txn_Usd;
    private String txn_Thb;
    private String txn_Cny;
}
