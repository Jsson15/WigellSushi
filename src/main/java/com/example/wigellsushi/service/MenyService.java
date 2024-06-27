package com.example.wigellsushi.service;

import com.example.wigellsushi.logging.Log4j;
import com.example.wigellsushi.execption.ResourceNotFoundException;
import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.repository.DishesRepository;
import com.example.wigellsushi.repository.MenyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenyService implements MenyServiceInterface{
    @Autowired
    private DishesRepository dishesRepository;

    @Override
    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
    }

    @Override
    public Dishes addDishes(Dishes dishes) {
        dishesRepository.save(dishes);
        Log4j.logger.info("Admin added a dish: " + dishes);
        return dishes;
    }

    @Override
    public void deleteDish(int dishID) {
        Dishes dishToDelete = getDishByID(dishID);
        Log4j.logger.info("Admin deleted a dish: " + dishToDelete);
        dishesRepository.delete(dishToDelete);
    }


    private Dishes getDishByID(int dishID) throws ResourceNotFoundException {
        return dishesRepository.findById(dishID).orElseThrow(() -> new ResourceNotFoundException("Dishes", "ID", dishID));
    }
}