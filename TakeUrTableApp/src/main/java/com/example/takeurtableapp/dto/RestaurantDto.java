package com.example.takeurtableapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private Long id;
    @NotEmpty
    private String administratorName;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String restaurantName;
    @NotEmpty
    private String city;
    @NotEmpty
    private String address;
    @NotEmpty
    private String information;
    @NotEmpty
    private String photo;

}
