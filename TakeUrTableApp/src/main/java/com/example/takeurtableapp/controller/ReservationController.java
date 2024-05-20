package com.example.takeurtableapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveTable(@RequestBody ReservationRequest request) {
        // Отримайте дані бронювання з тіла запиту
        String name = request.getName();
        String phone = request.getPhone();


        return ResponseEntity.ok("Бронювання успішно здійснено");
    }
    public class ReservationRequest {
        private String name;
        private String phone;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}


