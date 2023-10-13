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
public class PayCashRes {
    private String status;
    private String message;
    private List<PayHeader> headerCash;
    private List<PayHeaderFT> headerFT;
    private String totalAmountAll;

    private String totalAmountAllLAK;
    private String totalAmountAllUSD;
    private String totalAmountAllTHB;
    private String totalAmountAllCNY;
}
