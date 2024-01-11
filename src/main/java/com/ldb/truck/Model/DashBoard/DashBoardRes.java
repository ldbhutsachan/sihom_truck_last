package com.ldb.truck.Model.DashBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardRes {
    private String status;
    private String message;
List<DashBoard> groupA;
List<DashBoard> groupB;
List<DashBoard> groupC;
List<DashBoard> groupD;
List<CarDashBoard> groupCarFree;
List<CarDashBoard> groupCarNoFree;
}
