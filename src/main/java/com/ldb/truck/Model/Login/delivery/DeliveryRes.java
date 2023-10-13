package com.ldb.truck.Model.Login.delivery;

import java.util.List;

public class DeliveryRes {

    private String status;
    private String message;
    private List<DeliveryOut> data;

    public DeliveryRes() {
    }

    public DeliveryRes(String status, String message, List<DeliveryOut> data) {
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

    public List<DeliveryOut> getData() {
        return data;
    }

    public void setData(List<DeliveryOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeliveryRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
