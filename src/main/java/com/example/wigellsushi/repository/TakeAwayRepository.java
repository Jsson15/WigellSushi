package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.TakeAwayOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeAwayRepository extends JpaRepository<TakeAwayOrders, Integer> {

}
