package com.ldb.truck.Controller;

import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.User.UserRes;
import com.ldb.truck.Model.Login.customer.CustomerRes;
import com.ldb.truck.Service.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${base_url}")
public class UserLoginController {
    @Autowired
    LoginService loginService;
    @CrossOrigin(origins = "*")
    @PostMapping("/getUserLogin.service")
    public UserRes getUserLogin (@RequestBody UserReq userReq){
        UserRes result = new UserRes();
        try{
        result = loginService.ListUserLogin(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/storeUserLogin.service")
    public UserRes storeUserLogin (@RequestBody UserReq userReq){
        UserRes result = new UserRes();
        try{
            result = loginService.storeUserLogin(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/updateUserLogin.service")
    public UserRes updateUserLogin (@RequestBody UserReq userReq){
        UserRes result = new UserRes();
        try{
            result = loginService.updateUserLogin(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/delUserLogin.service")
    public UserRes delUserLogin(@RequestBody UserReq userReq){

        UserRes result = new UserRes();
        try{
            result = loginService.delUserLogin(userReq);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
