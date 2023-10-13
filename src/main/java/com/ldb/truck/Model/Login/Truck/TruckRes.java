package com.ldb.truck.Model.Login.Truck;

import java.util.List;

public class TruckRes {
    private String status;
    private String message;
    private List<TruckOut> data;

    public TruckRes() {
    }

    public TruckRes(String status, String message, List<TruckOut> data) {
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

    public List<TruckOut> getData() {
        return data;
    }

    public void setData(List<TruckOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TruckRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
