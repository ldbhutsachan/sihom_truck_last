package com.ldb.truck.Model.Login.location;

import java.util.List;

public class LocationRes {

    private String status;
    private String message;
    private List<LocationOut> data ;

    public LocationRes() {
    }

    public LocationRes(String status, String message, List<LocationOut> data) {
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

    public List<LocationOut> getData() {
        return data;
    }

    public void setData(List<LocationOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LocationRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
