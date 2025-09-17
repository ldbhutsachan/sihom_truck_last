package com.ldb.truck.Model.testModel;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   // ✅ constructor รับทุก field
@NoArgsConstructor    // ✅ constructor ว่าง
public class ItemTestModel {
    private String branchName;
    private String houseId;
    private String khname;
    private String borName;
    private Double totalLAK;
    private Double totalTHB;
    private Double totalUSD;
}
