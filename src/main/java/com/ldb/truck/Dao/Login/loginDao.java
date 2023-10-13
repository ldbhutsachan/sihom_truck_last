package com.ldb.truck.Dao.Login;

import com.ldb.truck.Model.Login.Login.GetUserLoginOut;
import com.ldb.truck.Model.Login.Login.LoginOut;
import com.ldb.truck.Model.Login.Login.LoginReq;

import java.util.List;

public interface loginDao {

    List<LoginOut> getAllCustomer();
    List<GetUserLoginOut> Login(LoginReq loginReq);
    int StoreUser(LoginReq loginReq);
    int UpdateUser(LoginReq loginReq);
    int DeleteUser(LoginReq loginReq);
    List<GetUserLoginOut> getInfoUserLogin(LoginReq loginReq);
}
