package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwePayBackRes {
    private String status;
    private String message;
   // private List<OwePayBack> data;
    private   List<OweCustomerDetails> CustomerData;
    private List<sumFooterGroupBelow> sumAmountTotalGroup;

}
