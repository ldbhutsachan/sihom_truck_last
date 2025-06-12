package com.ldb.truck.Entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAuthResponse {
   private String status;
   private String message;
   List<OrderAuthHeader> dataResponse;
}