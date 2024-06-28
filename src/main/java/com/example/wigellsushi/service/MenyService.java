package com.example.wigellsushi.service;

import com.example.wigellsushi.logging.Log4j;
import com.example.wigellsushi.execption.ResourceNotFoundException;
import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.repository.DishesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

@Service
public class MenyService implements MenyServiceInterface {

    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/fd910a7e3d1255ef612fb638/latest/SEK";

    @Autowired
    private DishesRepository dishesRepository;

    @Override
    public Dishes addDishes(Dishes dish) {
        try {
            BigDecimal priceEuro = calculateTotalPriceEuro(dish.getPriceSek());
            dish.setPriceEuro(priceEuro);
        } catch (IOException e) {
            // Hantera IOException här, till exempel genom att logga felmeddelandet
            Log4j.logger.error("Error calculating price in Euro", e);
            throw new RuntimeException("Error calculating price in Euro", e);
        }
        dishesRepository.save(dish);
        Log4j.logger.info("Admin added a dish: " + dish);
        return dish;
    }

    private BigDecimal calculateTotalPriceEuro(BigDecimal sek) throws IOException {
        URL url = new URL(EXCHANGE_RATE_API_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(url);
        BigDecimal euroCurrency = rootNode.path("conversion_rates").path("EUR").decimalValue();

        return sek.multiply(euroCurrency).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
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
