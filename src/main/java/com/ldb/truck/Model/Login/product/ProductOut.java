package com.ldb.truck.Model.Login.product;

public class ProductOut {

    private String id;
    private String proId;
    private String proName;
    private String proType;
    private String proUrl;
    private String proDateTime;
    private String userId;

    public ProductOut() {
    }

    public ProductOut(String id, String proId, String proName, String proType, String proUrl, String proDateTime, String userId) {
        this.id = id;
        this.proId = proId;
        this.proName = proName;
        this.proType = proType;
        this.proUrl = proUrl;
        this.proDateTime = proDateTime;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public String getProDateTime() {
        return proDateTime;
    }

    public void setProDateTime(String proDateTime) {
        this.proDateTime = proDateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ProductOut{" +
                "id='" + id + '\'' +
                ", proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", proType='" + proType + '\'' +
                ", proUrl='" + proUrl + '\'' +
                ", proDateTime='" + proDateTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
