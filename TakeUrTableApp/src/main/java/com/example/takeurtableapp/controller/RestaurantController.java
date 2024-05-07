package com.example.takeurtableapp.controller;

import com.example.takeurtableapp.dto.RestaurantDto;
import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.RestaurantRepository;
import com.example.takeurtableapp.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/")
    public String showMain(Model model) {
        return "main";
    }

//    @GetMapping("/restaurants/{id}")
//    public String showRestaurant(@PathVariable Long id, Model model) {
//        Restaurant restaurant = restaurantService.getRestaurantById(id);
//        model.addAttribute("restaurant", restaurant);
//        System.out.println(restaurant.getRestaurantName());
//        return "restaurantPage";
//    }

    @GetMapping("/restaurant/{name}")
    public String showRestaurant(@PathVariable String name, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantByName(name);
        if (restaurant != null){
            model.addAttribute("restaurant", restaurant);
        }
        else{
            restaurant = new Restaurant(-999L,"","","","","","","","","");
            model.addAttribute("restaurant", restaurant);
        }
        System.out.println(restaurant.getRestaurantName());
        System.out.println("1");
        return "restaurantPage";
    }

    @GetMapping("/register")
    public String registerRestaurant (Model model) {
        RestaurantDto restaurantDto = new RestaurantDto();
        model.addAttribute("restaurantDto", restaurantDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registation(@Valid @ModelAttribute("restaurantDto") RestaurantDto restaurantDto, BindingResult result, Model model){
        Restaurant existingRestaurant = restaurantService.getRestaurantByName(restaurantDto.getRestaurantName());
        if(existingRestaurant != null && existingRestaurant.getRestaurantName() != null && !existingRestaurant.getRestaurantName().isEmpty()){
            result.rejectValue("email",null,"There is already account");
        }
        if (result.hasErrors()){
            model.addAttribute("restaurantDto", restaurantDto);
            return "/register";
        }
        restaurantService.saveRestaurant(restaurantDto);
        return "redirect:/register?sucess";
    }

}