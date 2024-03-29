package com.ldb.truck.Model.Login.ExpensesBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenTypeReq {

    private String toKen;
    private String userId;
    private String branch;
    private String key_id;
    private String typeName;
    private String cDate;
}
