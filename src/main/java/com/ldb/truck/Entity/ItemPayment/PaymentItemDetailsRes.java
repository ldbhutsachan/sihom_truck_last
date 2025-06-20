package com.ldb.truck.Entity.ItemPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentItemDetailsRes {
   private String status;
   private String message;
   private String logo;

   List<GroupPaymentItemHeader> dataResponse;
}