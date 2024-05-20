package com.example.takeurtableapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantLogin{
    private String email;
    private String password;

    public RestaurantLogin(String email, String password){
        this.email = email;
        this.password = password;
    }
    public RestaurantLogin(){};

}