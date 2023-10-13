package com.ldb.truck.Model.Login.staft;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDetailsRes {
    private String status;
    private String message;
    private String staffID;
    private String staffName;
    private String staffSurname;
    private String idCard;
    private String licenId;
    private String idCardExpried;
    private List<StaffDetailsGroup> data;
}
