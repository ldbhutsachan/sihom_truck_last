package com.ldb.truck.Model.StaffStatement;

import lombok.Data;
import java.util.List;

@Data
public class StaffStatementBatchReq {
    private List<StaffStatementReq> requests;
}
