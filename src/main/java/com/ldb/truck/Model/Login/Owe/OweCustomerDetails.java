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
public class OweCustomerDetails {
    private String cusId;
    private String cusName;
    private String calTotalDate;
    private List<OwePayDetails> listOwePayDetails;
    private List<GroupCurr> GroupCurrDetails;


}
