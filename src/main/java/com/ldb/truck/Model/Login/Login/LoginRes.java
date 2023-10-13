package com.ldb.truck.Model.Login.Login;

import java.util.List;

public class LoginRes {
    private String status;
    private String message;
    private List<LoginOut> data;

    public LoginRes() {
    }

    public LoginRes(String status, String message, List<LoginOut> data) {
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

    public List<LoginOut> getData() {
        return data;
    }

    public void setData(List<LoginOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
