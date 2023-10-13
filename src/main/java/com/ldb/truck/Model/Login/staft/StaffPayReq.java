package com.ldb.truck.Model.Login.staft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffPayReq {
    private String staffID;
    private String lahudPoyLod;
    private String startDate;
    private String endDate;
}
