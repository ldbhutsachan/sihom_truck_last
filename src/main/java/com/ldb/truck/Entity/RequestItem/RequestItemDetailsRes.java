package com.ldb.truck.Entity.RequestItem;

import com.ldb.truck.Entity.Stock.OrderItemHeader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItemDetailsRes {
   private String status;
   private String message;
   List<RequestItemHeader> dataResponse;
}