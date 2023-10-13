package com.ldb.truck.Model.Login.staft;

import java.util.List;

public class staftRes {
    private String status;
    private String message;
    private List<staftOut> data;

    public staftRes() {
    }

    public staftRes(String status, String message, List<staftOut> data) {
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

    public List<staftOut> getData() {
        return data;
    }

    public void setData(List<staftOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "staftRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
