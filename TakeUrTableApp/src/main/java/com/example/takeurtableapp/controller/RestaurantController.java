package com.example.takeurtableapp.controller;

import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.RestaurantRepository;
import com.example.takeurtableapp.service.RestaurantService;
import com.example.takeurtableapp.service.iml.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;
//    @GetMapping("/restaurants")
//    public String showRestaurants(Model model) {
//        List<Restaurant> restaurants = restaurantRepository.findAll();
//        model.addAttribute("restaurants", restaurants);
//        return "restaurantPage";
//    }

//    @GetMapping("/restaurants/{id}")
//    public String showRestaurant(@PathVariable Long id, Model model) {
//        Restaurant restaurant = restaurantService.getRestaurantById(id);
//        model.addAttribute("restaurant", restaurant);
//        return "restaurantPage";
//    }

    @GetMapping("/restaurant/{name}")
    public String showRestaurant(@PathVariable String name, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        if (restaurant != null){
            model.addAttribute("restaurant", restaurant);
        }
        else{
            model.addAttribute("restaurant", new Restaurant(-999L,"Error","Error","Error","Error","Error","Error","Error","Error","Error"));
        }
        return "restaurantPage";
    }
}