package com.ldb.truck.Model.Login.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String userId;
    private String staff_id;
    private String userName;
    private String role;
    private String branchNo;
    private String branchName;
    private String borNo;
    private String borName;
    private String role2;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStaff_id() { return staff_id; }
    public void setStaff_id(String staff_id) { this.staff_id = staff_id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getBranchNo() { return branchNo; }
    public void setBranchNo(String branchNo) { this.branchNo = branchNo; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getBorNo() { return borNo; }
    public void setBorNo(String borNo) { this.borNo = borNo; }

    public String getBorName() { return borName; }
    public void setBorName(String borName) { this.borName = borName; }

    public String getRole2() { return role2; }
    public void setRole2(String role2) { this.role2 = role2; }
}
