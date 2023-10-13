package com.ldb.truck.Model.Login.Batery;

import com.ldb.truck.Model.Login.customer.CustomerOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BateryRes {
    private String status;
    private String message;
    private List<Batery> data;
}
