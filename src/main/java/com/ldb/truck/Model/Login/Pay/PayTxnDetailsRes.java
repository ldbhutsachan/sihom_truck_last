package com.ldb.truck.Model.Login.Pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayTxnDetailsRes {
    private String status;
    private String message;
    private List<PayTxnDetails> data;
}
