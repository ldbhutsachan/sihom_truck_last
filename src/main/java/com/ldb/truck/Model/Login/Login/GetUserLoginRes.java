package com.ldb.truck.Model.Login.Login;

public class GetUserLoginRes {
    private String status;
    private String message;
    private GetUserLoginOut  data;

    public GetUserLoginRes() {
    }

    public GetUserLoginRes(String status, String message, GetUserLoginOut data) {
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

    public GetUserLoginOut getData() {
        return data;
    }

    public void setData(GetUserLoginOut data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetUserLoginRes{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
