package com.ldb.truck.Model.Login.ExchangeRate;

import com.ldb.truck.Model.Login.Details.Details;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRes {
    private String status;
    private String message;
    private List<ExchangeRate> data;
}
