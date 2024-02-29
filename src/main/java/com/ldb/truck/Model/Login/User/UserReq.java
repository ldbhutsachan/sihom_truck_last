package com.ldb.truck.Model.Login.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private String toKen;
    private String key_Id;
    private String user_Login;
    private String userSurName;
    private String passWord;
    private String role;
    private String createDate;
    private String status;
    private String userId;
    private String staff_Id;
    private String branch;
}
