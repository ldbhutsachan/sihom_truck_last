package com.ldb.truck.Service.UserBor;

import com.ldb.truck.Entity.EntityUser.UserBorEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Repository.UserBorRepository;
import com.ldb.truck.Util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBorService {

    @Autowired
    private UserBorRepository userBorRepository;

    //insertUser service
    public DataResponse insertUser(UserBorEntity user) {
        DataResponse response = new DataResponse();
        try {
            // set dateInsert
            user.setDateInsert(new Date());

            // generate token จาก userLogin
            if(user.getUserLogin() != null) {
                user.setToken(TokenGenerator.generateToken(user.getUserLogin()));
            }

            // call native query
            userBorRepository.insertUserNative(
                    user.getUserLogin(),
                    user.getPassword(),
                    user.getRole(),
//                    user.getUserId(),
                    user.getRole(),
                    user.getStatus(),
                    user.getToken(),
                    user.getBranch(),
                    user.getSaveById(),
                    user.getDepartment(),
                    user.getSpritRole(),
                    user.getBorNo()
            );
            response.setStatus("00");
            response.setMessage("Insert User Successfully ");
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    //update user service
    public DataResponse updateUser(UserBorEntity user) {
        DataResponse response = new DataResponse();
        try {
            if(user.getUserLogin() != null) {
                user.setToken(TokenGenerator.generateToken(user.getUserLogin()));
            }

            userBorRepository.updateUserNative(
                    user.getKeyId(),
                    user.getUserLogin(),
                    user.getPassword(),
                    user.getRole(),
                    user.getStatus(),
                    user.getToken(),
                    user.getBranch(),
                    user.getSaveById(),
                    user.getDepartment(),
                    user.getSpritRole(),
                    user.getBorNo()
            );

            response.setStatus("00");
            response.setMessage("Update user Success");
        } catch (Exception e) {
            response.setStatus("EE");
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // show User
        // ดึง user ทั้งหมด
        public DataResponse getAllUsers() {
            DataResponse response = new DataResponse();
            try {
                List<UserBorEntity> users = userBorRepository.findAllUsersNative();
                response.setStatus("00");
                response.setMessage("Success");
                response.setDataResponse(users);
            } catch (Exception e) {
                response.setStatus("EE");
                response.setMessage("Error: " + e.getMessage());
            }
            return response;
        }
    }
