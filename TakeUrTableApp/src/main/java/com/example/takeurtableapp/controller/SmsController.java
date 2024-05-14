package com.example.takeurtableapp.controller;

import com.example.takeurtableapp.forms.ReservationForm;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class SmsController {


//    @PostMapping(value = "/sendSMS")
//    public void sendSMS(@RequestParam("name") String name, @RequestParam("phone") String phone) {
//        System.out.println("Ім'я: " + name);
//        System.out.println("Номер телефону: " + phone);
//    }



    @PostMapping(value = "/sendSMS")
    public int sendSMS(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        Twilio.init("name","pass");
        Random random = new Random();
        int veritifyCode = 1000 + random.nextInt(9000);
        System.out.println(veritifyCode);
        return veritifyCode;
//        try {
//            Message.creator(new PhoneNumber(phone),
//                    new PhoneNumber("+from"), String.valueOf(veritifyCode)).create();
//            return ResponseEntity.ok("SMS sent successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SMS");
//        }
//        return ResponseEntity.ok("SMS sent successfully");
    }
}
