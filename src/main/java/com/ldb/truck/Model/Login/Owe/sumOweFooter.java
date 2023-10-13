package com.ldb.truck.Model.Login.Owe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class sumOweFooter {
    private String CusId;
    private String sumPayAmount01;
    private String sumPayAmount02;
    private String sumPayAmount03;
    private String sumPayAmount04;
    private String currency;
}
