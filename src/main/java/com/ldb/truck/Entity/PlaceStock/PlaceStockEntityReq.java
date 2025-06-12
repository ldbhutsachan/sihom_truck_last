package com.ldb.truck.Entity.PlaceStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceStockEntityReq {

    private Long khId; // Changed from int(100) to Integer for auto-increment handling
    private String khNo;
    private String khName;
    private String sole;
    private String soleStep;
    private String blockNo;
    private String status;
    private String borNo;
    private String userId;
    private String toKen;
    private String role;
    private String brandNo;
}