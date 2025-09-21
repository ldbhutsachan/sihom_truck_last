package com.ldb.truck.Model.Machine;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class MachineReport {
    private Integer mchId;
    private String mchNo;
    private String mchName;

    private String mchBranchName;
    private String mchModel;
    private String mchProductYear;
    private String requestDate; // formatted as 'YYYY-MM-DD'
    private String borNo;
    private String borName;
    private Integer itemId;
    private String itemName;
    private String unit;
    private String currency;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal total;


}
