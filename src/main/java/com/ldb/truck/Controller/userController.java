package com.ldb.truck.Controller;

import com.ldb.truck.Entity.EntityUser.UserBorEntity;
import com.ldb.truck.Model.DataResponse;
import com.ldb.truck.Service.UserBor.UserBorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("${base_url}")
@CrossOrigin(origins = "*")
public class userController {
    @Autowired
    private UserBorService userBorService;

    // Insert user
    @PostMapping("/insertUserBor.service")
    public ResponseEntity<DataResponse> insertUser(@RequestBody UserBorEntity user) {
        DataResponse response = userBorService.insertUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update user
    @PostMapping("/updateUserBor.service")
    public ResponseEntity<DataResponse> updateUser(@RequestBody UserBorEntity user) {
        DataResponse response = userBorService.updateUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //show user
    @RestController
    @RequestMapping("${base_url}")
    @CrossOrigin(origins = "*")
    public class UserController {

        @Autowired
        private UserBorService userBorService;

        // Show all users
        @GetMapping("/showAllUsers.service")
        public ResponseEntity<DataResponse> showAllUsers() {
            DataResponse response = userBorService.getAllUsers();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
