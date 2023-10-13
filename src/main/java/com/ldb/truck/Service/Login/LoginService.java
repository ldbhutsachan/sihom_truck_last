package com.ldb.truck.Service.Login;

import com.ldb.truck.Dao.Login.ImploginDao;
import com.ldb.truck.Dao.User.UserImplDao;
import com.ldb.truck.Model.Login.Login.GetUserLoginOut;
import com.ldb.truck.Model.Login.Login.GetUserLoginReq;
import com.ldb.truck.Model.Login.Login.GetUserLoginRes;
import com.ldb.truck.Model.Login.Login.LoginReq;
import com.ldb.truck.Model.Login.User.UserLogin;
import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.User.UserRes;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    ImploginDao imploginDao;
    @Autowired
    UserImplDao userLogin;
    public GetUserLoginRes Userlogin(LoginReq loginReq){
        GetUserLoginRes result = new GetUserLoginRes();
        List<GetUserLoginOut> listData = new ArrayList<>();
        GetUserLoginOut data = new GetUserLoginOut();
        try {
            listData = imploginDao.Login(loginReq);
            if(listData.size() < 1){
                result.setMessage("user or password incorrect");
                result.setStatus("01");
                return result;
            }
            data.setStaftName(listData.get(0).getStaftName());
            data.setStaftId(listData.get(0).getStaftId());
            data.setRole(listData.get(0).getRole());
            data.setStatus(listData.get(0).getStatus());
            data.setToKen(listData.get(0).getToKen());
            System.out.println("login getStaftName:"+listData.get(0).getStaftName());
            System.out.println("login getStaftId:"+listData.get(0).getStaftId());
            System.out.println("login getRole:"+listData.get(0).getRole());
            System.out.println("login getStatus:"+listData.get(0).getStatus());
            System.out.println("login getToKen:"+listData.get(0).getToKen());
            result.setMessage("success");
            result.setStatus("00");
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("user or password incorrect");
            result.setStatus("01");
            return result;
        }

    }
    public UserRes ListUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        List<UserLogin> listData = new PersistentList();
        try{
            listData = userLogin.listUser(userReq);
            if(listData.size() == 0){
                result.setStatus("01");
                result.setMessage("No Data");
                result.setData(listData);
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("success");
                result.setData(listData);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public UserRes storeUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        int checkData = 0;
        try{
            checkData = userLogin.storeUser(userReq);
            if(checkData  == 0){
                result.setStatus("01");
                result.setMessage("No Save Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("Save Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public UserRes updateUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        int checkData = 0;
        try{
            checkData = userLogin.editUser(userReq);
            if(checkData  == 0){
                result.setStatus("01");
                result.setMessage("No edit User Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("edit User Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public UserRes delUserLogin(UserReq userReq){
        UserRes result = new UserRes();
        int checkData = 0;
        try{
            checkData = userLogin.delUser(userReq);
            if(checkData  == 0){
                result.setStatus("01");
                result.setMessage("No del User Data");
                return result;
            }else {
                result.setStatus("00");
                result.setMessage("del User Data Done");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
