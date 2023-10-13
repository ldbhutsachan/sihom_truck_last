package com.ldb.truck.Model.Login.Pay;

import com.ldb.truck.Model.Login.Details.Details;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayRes {
    private String status;
    private String message;
    private List<Details> data;
}
