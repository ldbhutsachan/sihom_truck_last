package com.ldb.truck.Model.Login.customer;

public class CustomerReq {
    private int id;
    private String customerId;
    private String customerName;
    private String address;
    private String vilage;
    private String district;
    private String province;
    private String mobile;
    private String mobile1;
    private String email;

    public CustomerReq() {
    }

    public CustomerReq(int id) {
        this.id = id;
    }

    public CustomerReq(String customerId, String customerName, String address, String vilage, String district, String province, String mobile, String mobile1, String email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.vilage = vilage;
        this.district = district;
        this.province = province;
        this.mobile = mobile;
        this.mobile1 = mobile1;
        this.email = email;
    }

    public CustomerReq(int id, String customerId, String customerName, String address, String vilage, String district, String province, String mobile, String mobile1, String email) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.vilage = vilage;
        this.district = district;
        this.province = province;
        this.mobile = mobile;
        this.mobile1 = mobile1;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVilage() {
        return vilage;
    }

    public void setVilage(String vilage) {
        this.vilage = vilage;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerReq{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", vilage='" + vilage + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
