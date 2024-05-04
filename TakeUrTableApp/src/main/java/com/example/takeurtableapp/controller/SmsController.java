package com.example.takeurtableapp.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SmsController {

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

        Twilio.init("name","pass");
        Random random = new Random();
        int veritifyCode = 1000 + random.nextInt(9000);
        Message.creator(new PhoneNumber("+to number"),
                new PhoneNumber("+from"), String.valueOf(veritifyCode)).create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
