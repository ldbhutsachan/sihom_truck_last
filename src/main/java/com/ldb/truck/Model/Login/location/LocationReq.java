package com.ldb.truck.Model.Login.location;

public class LocationReq {

    private String province;
    private String detail;
    private String id;

    public LocationReq() {
    }

    public LocationReq(String province, String detail, String id) {
        this.province = province;
        this.detail = detail;
        this.id = id;
    }

    public LocationReq(String province, String detail) {
        this.province = province;
        this.detail = detail;
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

    @Override
    public String toString() {
        return "LocationReq{" +
                "province='" + province + '\'' +
                ", detail='" + detail + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
