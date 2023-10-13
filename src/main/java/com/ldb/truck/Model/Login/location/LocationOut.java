package com.ldb.truck.Model.Login.location;

public class LocationOut {

    private String id;
    private String province;
    private String detail;
    private String status;

    public LocationOut() {
    }

    public LocationOut(String id, String province, String detail, String status) {
        this.id = id;
        this.province = province;
        this.detail = detail;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LocationOut{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
