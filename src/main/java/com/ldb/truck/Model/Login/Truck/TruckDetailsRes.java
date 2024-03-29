package com.ldb.truck.Model.Login.Truck;

import com.ldb.truck.Model.Login.SumGiveFooter.SumGiveFooter;
import com.ldb.truck.Model.sumInCome;
import com.ldb.truck.Model.sumInComeCount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDetailsRes {
    private String status;
    private String message;
    private List<TruckDetails> data;
    private List<SumGiveFooter> sumFooter;
    private List<sumInCome> sumFooterPay;
    private String countTotalAll;
}
