package com.ldb.truck.Dao.User;

import com.ldb.truck.Model.Login.User.UserLogin;
import com.ldb.truck.Model.Login.User.UserReq;

import java.util.List;

public interface UserImplDao {
    List<UserLogin> listUser(UserReq userReq);
    public int storeUser(UserReq userReq);
    public int editUser(UserReq userReq);
    public int delUser(UserReq userReq);
}
