package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.DishBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishBookingRepository extends JpaRepository<DishBooking, Integer> {
}
