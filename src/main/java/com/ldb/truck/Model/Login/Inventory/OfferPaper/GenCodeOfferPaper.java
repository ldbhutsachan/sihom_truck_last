package com.ldb.truck.Model.Login.Inventory.OfferPaper;

import com.ldb.truck.Model.Login.ShowIdinvoiceNo.getInvoiceNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenCodeOfferPaper {
    private String status;
    private String message;
    private List<OfferCodeModel> data;
}
