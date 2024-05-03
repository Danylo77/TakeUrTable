package com.example.takeurtableapp.service;

import com.example.takeurtableapp.entity.Restaurant;

public interface RestaurantService {
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByName(String name);
}
