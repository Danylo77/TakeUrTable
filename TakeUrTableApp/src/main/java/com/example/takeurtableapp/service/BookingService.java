package com.example.takeurtableapp.service;

import com.example.takeurtableapp.dto.BookingDto;
import com.example.takeurtableapp.dto.RestaurantDto;
import com.example.takeurtableapp.entity.Booking;
import com.example.takeurtableapp.entity.Restaurant;

import java.util.List;

public interface BookingService {
    Booking getBookingById(Long id);
    List<Booking> getBookingsByRestaurantId(Long restaurantId);
    void saveBooking(BookingDto bookingDto);
    List<Booking> getUnconfirmedBookingsByRestaurantId(Long restaurantId);
    List<Booking> getConfirmedBookingsByRestaurantId(Long restaurantId);
    List<Booking> getArchivedBookingsByRestaurantId(Long restaurantId);
}
