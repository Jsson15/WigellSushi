package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.Bookings;
import com.example.wigellsushi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
    Bookings findBookingsByBookingID(int bookingID);
    List<Bookings> findBookingsByUser(User user);


}

