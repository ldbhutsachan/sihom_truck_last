package com.ldb.truck.Model.Login.VicicleFooter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class VicicleFooterRes {
    private String status;
    private String message;
    private List<VicicleFooter> data;
    public VicicleFooterRes() {
    }
    public VicicleFooterRes(String status, String message, List<VicicleFooter> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
