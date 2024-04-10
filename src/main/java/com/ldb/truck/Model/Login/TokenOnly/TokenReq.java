package com.ldb.truck.Model.Login.TokenOnly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenReq {
    private String toKen;
    private String branch;
    private String userId;
}
