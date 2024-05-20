package com.example.takeurtableapp.controller;
import com.example.takeurtableapp.dto.BookingComment;
import com.example.takeurtableapp.dto.BookingDto;
import com.example.takeurtableapp.dto.RestaurantLogin;
import com.example.takeurtableapp.entity.Booking;
import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.BookingRepository;
import com.example.takeurtableapp.service.BookingService;
import com.example.takeurtableapp.service.RestaurantService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class AdminController {

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

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("restaurantLogin", new RestaurantLogin());
        return "login";
    }

    @PostMapping("/admin")
    public String login(@ModelAttribute("restaurantLogin") RestaurantLogin restaurantLogin,  Model model) {
        Restaurant restaurant = restaurantService.getRestaurantByEmail(restaurantLogin.getEmail());
        if (restaurant != null){
            if(restaurant.getPassword().equals(restaurantLogin.getPassword())){
                model.addAttribute("restaurant", restaurant);
                List<Booking> confirmedBookings = bookingService.getConfirmedBookingsByRestaurantId(restaurant.getId());
                List<Booking> unconfirmedBookings = bookingService.getUnconfirmedBookingsByRestaurantId(restaurant.getId());
                List<Booking> archivedBookings = bookingService.getArchivedBookingsByRestaurantId(restaurant.getId());
                model.addAttribute("confirmedBookings", confirmedBookings);
                model.addAttribute("unconfirmedBookings", unconfirmedBookings);
                model.addAttribute("archivedBookings", archivedBookings);
                return "restaurantHome";
            }
        }
        model.addAttribute("error", "Неправильний email або пароль");
        return "login";
    }

    @PostMapping("/accept-booking/{bookingId}")
    public ResponseEntity<String> acceptBooking(@PathVariable Long bookingId, @RequestBody BookingComment bookingComment) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        booking.setStatus("Підтверджено");
        booking.setCommentAdmin(bookingComment.getComment());
        bookingRepository.save(booking);
        Twilio.init(accountSid,authToken);
        PhoneNumber clientPhoneNumber = new PhoneNumber("+380977170704");
        String smsText = "Ваше бронювання успішно підтверджено!";
        try {
            //Message.creator(clientPhoneNumber,new PhoneNumber("+12512202285"), smsText).create();
        } catch (Exception e) {
        }
        return ResponseEntity.ok("Ви успішно відтвердили бронювання!");
    }

    @PostMapping("/refuse-booking/{bookingId}")
    public ResponseEntity<String> refuseBooking(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        bookingRepository.delete(booking);
        return ResponseEntity.ok("Ви успішно відхилили бронювання!");
    }

    @PostMapping("/archive-booking/{bookingId}")
    public ResponseEntity<String> archiveBooking(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        booking.setStatus("Архівовано");
        bookingRepository.save(booking);
        return ResponseEntity.ok("Ви успішно архівували бронювання!");
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
        String smsText = restaurant.getRestaurantName() + " чекає на Вас о " + formattedDate + " за адресою " + restaurant.getAddress() + " м."+restaurant.getCity() +"!";
        try {
            //Message.creator(clientPhoneNumber,new PhoneNumber("+12512202285"), smsText).create();
        } catch (Exception e) {
        }
        return ResponseEntity.ok("Ви успішно відправили сповіщення!");
    }

}