package com.ldb.truck.Model.Login.Login;

import com.ldb.truck.Entity.User.UserHisEntity;
import lombok.Data;

import java.util.List;
@Data
public class UserHisResponse {
    private String status;
    private String message;
    private List<UserHisEntity> data;
}
