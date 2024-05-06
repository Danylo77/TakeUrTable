package com.example.takeurtableapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody String code) {
        // Отримати код підтвердження з тіла запиту і перевірити його
        // Тут ви можете реалізувати логіку для перевірки коду

        // Наприклад, якщо введений код співпадає з збереженим кодом підтвердження
        String savedCode = "1234"; // Замініть це на збережений код підтвердження
        if (code.equals(savedCode)) {
            return ResponseEntity.ok("Код підтвердження вірний");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Невірний код підтвердження");
        }
    }
}
