package com.ldb.truck.Model.Candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateVerifyReq {
    private String email;
    private String addrDistrict;
    private String religion;
    private String phone;
    private String idCardNo;
}
