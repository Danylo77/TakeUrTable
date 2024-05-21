package com.example.takeurtableapp.controller;

import com.example.takeurtableapp.entity.Booking;
import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.BookingRepository;
import com.example.takeurtableapp.service.BookingService;
import com.example.takeurtableapp.service.RestaurantService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class SmsController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingRepository bookingRepository;

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

    @PostMapping("/sms-booking/{bookingId}")
    public ResponseEntity<String> smsBooking(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        Restaurant restaurant = restaurantService.getRestaurantById(booking.getRestaurant().getId());
        Twilio.init(accountSid,authToken);
        PhoneNumber clientPhoneNumber = new PhoneNumber("+380977170704");
        Date date = new Date(booking.getDate().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        String smsText = restaurant.getRestaurantName() + " чекає на Вас о " + formattedDate + " за адресою "
                + restaurant.getAddress() + " м."+restaurant.getCity() +"!";
        try {
            //Message.creator(clientPhoneNumber,new PhoneNumber("+12512202285"), smsText).create();
        } catch (Exception e) {
        }
        return ResponseEntity.ok("Ви успішно відправили сповіщення!");
    }
}
