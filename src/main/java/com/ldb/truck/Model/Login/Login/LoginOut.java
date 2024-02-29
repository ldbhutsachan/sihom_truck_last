package com.ldb.truck.Model.Login.Login;

public class LoginOut {
    private String id;
    private String user;
    private String password;
    private String role;
    private String userId;
    private String dateTime;
    private String branch;

    public LoginOut() {
    }

    public LoginOut(String user, String password, String role, String userId, String dateTime,String branch) {
        this.user = user;
        this.password = password;
        this.role = role;
        this.userId = userId;
        this.dateTime = dateTime;
        this.branch = branch;
    }

    public LoginOut(String id, String user, String password, String role, String userId, String dateTime,String branch) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.role = role;
        this.userId = userId;
        this.dateTime = dateTime;
        this.branch = branch;
    }

    public String getBranch() {return branch;}

    public void setBranch(String branch) {this.branch = branch;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LoginOut{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userId='" + userId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
