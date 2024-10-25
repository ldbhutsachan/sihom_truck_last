package com.ldb.truck.Model.Login.Report;

import com.ldb.truck.Model.Login.ForShowTotalOil.ForShowTotalOilPaid;
import com.ldb.truck.Model.Login.Report.Bialieng.sumfooterOilPaid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowOilPaidRes {
    private String status;
    private String message;
    private List <ForShowTotalOilPaid> data;
    private sumfooterOilPaid sumFooter;
}
