package com.ldb.truck.Model.Login.product;

import java.util.List;

public class ProductRes {
    private String status;
    private String message;
    private List<ProductOut> data;

    public ProductRes() {
    }

    public ProductRes(String status, String message, List<ProductOut> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductOut> getData() {
        return data;
    }

    public void setData(List<ProductOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
