package com.example.takeurtableapp.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SmsController {

    @PostMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        Twilio.init("name","pass");
        Random random = new Random();
        int veritifyCode = 1000 + random.nextInt(9000);
        try {
            Message.creator(new PhoneNumber(phone),
                    new PhoneNumber("+from"), String.valueOf(veritifyCode)).create();
            return ResponseEntity.ok("SMS sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SMS");
        }
    }
}
