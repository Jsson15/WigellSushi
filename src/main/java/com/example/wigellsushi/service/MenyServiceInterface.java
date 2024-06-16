package com.example.wigellsushi.service;

import com.example.wigellsushi.model.Dishes;

import java.util.List;

public interface MenyServiceInterface {
    List<Dishes> getAllDishes();
    Dishes addDishes(Dishes dishes);
    void deleteDish(int dishID);


}

