package com.ldb.truck.Model.Login.ShowIdinvoiceNo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class getInvoiceNoRes {
    private String status;
    private String message;
    private List<getInvoiceNo> data;
}
