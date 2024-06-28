package com.example.wigellsushi.service;

import com.example.wigellsushi.model.Dishes;

import java.util.List;

public interface MenyServiceInterface {
    Dishes addDishes(Dishes dishes);
    List<Dishes> getAllDishes();
    void deleteDish(int dishID);
}

