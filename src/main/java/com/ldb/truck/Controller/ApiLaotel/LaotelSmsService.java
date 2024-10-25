package com.ldb.truck.Controller.ApiLaotel;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.http.HttpResponse;
import java.util.UUID;

import static org.springframework.http.RequestEntity.put;

public class LaotelSmsService {
    @Autowired
    private LaotelSmsConfig config;

//    public void sendSms(String phoneNumber, String message) throws UnirestException {
//        HttpResponse<String> response = Unirest.post("https://apicenter.laotel.com:9443/api/sms_center/submit_sms")
//                .header("apikey", config.getApiKey())
//                .header("Content-Type", "application/json")
//                .body(new JsonNode() {
//                    {
//                        put("transaction_id", UUID.randomUUID().toString()); // Generate a unique ID
//                        put("header", "Khounkham"); // Or set your desired header
//                        put("phoneNumber", phoneNumber);
//                        put("message", message);
//                    }
//                })
//                .asString();
//
//        if (response.getStatus != 200) {
//            throw new RuntimeException("Failed to send SMS: " + response.getStatusText());
//        }
//    }
}
