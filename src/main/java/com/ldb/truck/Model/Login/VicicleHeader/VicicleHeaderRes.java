package com.ldb.truck.Model.Login.VicicleHeader;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class VicicleHeaderRes {
    private String status;
    private String message;
    private List<VicicleHeader> data;
    public VicicleHeaderRes() {
    }
    public VicicleHeaderRes(String status, String message, List<VicicleHeader> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
