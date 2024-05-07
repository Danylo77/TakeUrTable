package com.example.takeurtableapp.service;

import com.example.takeurtableapp.dto.RestaurantDto;
import com.example.takeurtableapp.entity.Restaurant;

public interface RestaurantService {
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByName(String name);

    void saveRestaurant(RestaurantDto restaurantDtoDto);
}
