package com.example.takeurtableapp.repository;

import com.example.takeurtableapp.entity.Booking;
import com.example.takeurtableapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}