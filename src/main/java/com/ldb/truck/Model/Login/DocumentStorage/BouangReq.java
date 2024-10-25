package com.ldb.truck.Model.Login.DocumentStorage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BouangReq {
    private String key_id;
    private String branch;
    private String userId;
    private String toKen;
    private String nameOfBouang;

}
