package com.ldb.truck.Model.Login.Branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrachReq {
    private String toKen;
    private String key_id;
    private String b_name;
    private String b_tel;
    private String location;
    private String email;
    private String userId;
    private String branchNo;


}
