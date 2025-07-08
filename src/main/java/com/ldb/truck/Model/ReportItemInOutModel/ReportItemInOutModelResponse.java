package com.ldb.truck.Model.ReportItemInOutModel;

import com.ldb.truck.Model.ReportAllStock.ReportAllStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportItemInOutModelResponse {
    private String status;
    private String message;
    Double sumRaisedAmt;
    Double sumInAmt;
    Double sumOutAmt;
    Double sumAmt;

    double sumPrice;
    double sumPriceIn;
    double sumPriceOut;

    private List<ReportAllStock> dataResponse;
}
