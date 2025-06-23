package com.ldb.truck.Entity.ItemPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel
{
    private Integer itemId;
    private String itemName;
    private Date saveDate;
    private String approveBy;
    private Date approveDate;
    private String brandName;
    private String itemTypeName;
    private String image;
    private String status;
    private Float amount;
    private String ccy;
    private Date exp;
    private Float totalcal;
    private Integer qtycal;
    private Float amountscal;
}
