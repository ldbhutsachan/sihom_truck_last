package com.ldb.truck.Model.ReportInoutItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportInoutItemGroup {
    int raiseAmt;
    int inAmt;
    int outAmt;
    int closeAmt;
}
