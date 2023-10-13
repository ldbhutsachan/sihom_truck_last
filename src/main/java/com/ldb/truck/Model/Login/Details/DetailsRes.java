package com.ldb.truck.Model.Login.Details;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsRes {
    private String status;
    private String message;
    private List<Details> data;
}
