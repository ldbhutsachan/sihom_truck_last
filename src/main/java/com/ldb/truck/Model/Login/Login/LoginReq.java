package com.ldb.truck.Model.Login.Login;

public class LoginReq {
    private String user;
    private String password;
    private String role;
    private String userId;
    private String branch;

    public LoginReq() {
    }

    public LoginReq(String user, String password, String role, String userId,String branch) {
        this.user = user;
        this.password = password;
        this.role = role;
        this.userId = userId;
        this.branch = branch;
    }

    public LoginReq(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getBranch() {return branch;}

    public void setBranch(String branch) {this.branch = branch;}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userId='" + userId + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
