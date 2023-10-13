package com.ldb.truck.Model.Login.customer;

import java.util.List;

public class CustomerRes {
    private String status;
    private String message;
    private List<CustomerOut> data;
    public CustomerRes() {
    }
    public CustomerRes(String status, String message, List<CustomerOut> data) {
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

    public List<CustomerOut> getData() {
        return data;
    }

    public void setData(List<CustomerOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomerRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
