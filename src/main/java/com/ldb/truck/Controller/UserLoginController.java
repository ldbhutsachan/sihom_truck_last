package com.ldb.truck.Controller;

import com.ldb.truck.Dao.ProfileDao.ProfileDao;
import com.ldb.truck.Model.Login.Login.UserHisRequest;
import com.ldb.truck.Model.Login.Login.UserHisResponse;
import com.ldb.truck.Model.Login.Login.UserHisViewResponse;
import com.ldb.truck.Model.Login.Profile.Profile;
import com.ldb.truck.Model.Login.User.UserReq;
import com.ldb.truck.Model.Login.User.UserRes;
import com.ldb.truck.Model.Login.customer.CustomerRes;
import com.ldb.truck.Service.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base_url}")
public class UserLoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    ProfileDao profileDao;

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

    @CrossOrigin
    @PostMapping("/getUserHisStock.service")
    public ResponseEntity<?> getUserHisStock(@RequestBody UserHisRequest userHisRequest){
        UserHisResponse response = new UserHisResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(userHisRequest.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        try {
            response = loginService.getUserHistory(userId);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/getUserHistoryView.service")
    public ResponseEntity<?> getUserHistoryView(@RequestBody UserHisRequest userHisRequest){
        UserHisViewResponse response = new UserHisViewResponse();
        List<Profile> userProfiles = profileDao.getProfileInfoByToken(userHisRequest.getToKen());
        if (userProfiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userId = userProfiles.get(0).getUserId();
        try {
            response = loginService.getUserHistoryView(userId,userHisRequest);
        }catch (Exception e){
            response.setStatus("EE");
            response.setMessage("Data Error !!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
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
