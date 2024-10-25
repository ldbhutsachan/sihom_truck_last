package com.ldb.truck.Controller.ApiLaotel;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SmsController {
    @Autowired
    private LaotelSmsService smsService;

//    @PostMapping
//    public ResponseEntity<String> sendSms(@RequestParam String phoneNumber, @RequestParam String message) {
//        try {
//            smsService.sendSms(phoneNumber, message);
//            return ResponseEntity.ok("SMS sent successfully!");
//        } catch (UnirestException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SMS: " + e.getMessage());
//        }
//    }

}
