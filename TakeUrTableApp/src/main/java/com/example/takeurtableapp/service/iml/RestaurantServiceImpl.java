package com.example.takeurtableapp.service.iml;

import com.example.takeurtableapp.dto.RestaurantDto;
import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.RestaurantRepository;
import com.example.takeurtableapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    //private PasswordEncoder passwordEncoder;

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByRestaurantName(name);
    }

    @Override
    public void saveRestaurant(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setAdministratorName(restaurantDto.getAdministratorName());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setEmail(restaurantDto.getEmail());
        restaurant.setPassword(restaurantDto.getAdministratorName());
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setCity(restaurantDto.getCity());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setInformation(restaurantDto.getInformation());
        restaurant.setPhoto(restaurantDto.getPhoto());
        restaurantRepository.save(restaurant);
    }
}
