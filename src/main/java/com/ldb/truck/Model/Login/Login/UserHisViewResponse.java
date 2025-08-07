package com.ldb.truck.Model.Login.Login;

import com.ldb.truck.Entity.User.UserHisEntity;
import com.ldb.truck.Entity.User.VUserHisEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserHisViewResponse {
    private String status;
    private String message;
    private List<VUserHisEntity> data;
}
