package com.ldb.truck.Model.Machine;

import com.ldb.truck.Model.Login.product.ProductOut;
import lombok.Data;

import java.util.List;

@Data
public class MachineDetailsResponse {
    private String status;
    private String message;
    private List<MachineDetails> data;
}
