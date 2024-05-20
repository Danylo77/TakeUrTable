package com.example.takeurtableapp.controller;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class SmsController {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;
    @PostMapping(value = "/sendSMS")
    public int sendSMS(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        Twilio.init(accountSid,authToken);
        Random random = new Random();
        int veritifyCode = 1000 + random.nextInt(9000);
        PhoneNumber clientPhoneNumber = new PhoneNumber("+380977170704");
        String smsText = "Ваш код для TakeUrTable: " + veritifyCode;
        System.out.println(smsText);
        try {
            //Message.creator(clientPhoneNumber,new PhoneNumber("+12512202285"), smsText).create();
            return veritifyCode;
        } catch (Exception e) {
            return veritifyCode;
        }

    }
}
