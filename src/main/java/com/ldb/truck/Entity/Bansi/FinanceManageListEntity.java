package com.ldb.truck.Entity.Bansi;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name ="tb_finance_managelist")
public class FinanceManageListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    @JsonProperty("key_id")
    private Long keyId;

    @Column(name = "finance_bill")
    @JsonProperty("finance_bill")
    private String financeBill;

    @Column(name = "bill_No")
    @JsonProperty("bill_No")
    private String billNo;

}
