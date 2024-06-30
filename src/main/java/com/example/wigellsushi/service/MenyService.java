package com.example.wigellsushi.service;

import com.example.wigellsushi.logging.Log4j;
import com.example.wigellsushi.execption.ResourceNotFoundException;
import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.repository.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MenyService implements MenyServiceInterface {

    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/fd910a7e3d1255ef612fb638/latest/SEK";

    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Dishes addDishes(Dishes dish) {
        try {
            BigDecimal priceEuro = calculateTotalPriceEuro(dish.getPriceSek());
            dish.setPriceEuro(priceEuro);
            dishesRepository.save(dish);
            Log4j.logger.info("Admin added a dish: " + dish);
            return dish;
        } catch (WebClientResponseException ex) {
            Log4j.logger.error("Error fetching exchange rate", ex);
            throw new ResourceNotFoundException("Error fetching exchange rate", ex);
        }
    }

    private BigDecimal calculateTotalPriceEuro(BigDecimal sek) {
        WebClient webClient = webClientBuilder.build();
        try {
            JsonNode response = webClient.get()
                    .uri(EXCHANGE_RATE_API_URL)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
            BigDecimal euroCurrency = response.path("conversion_rates").path("EUR").decimalValue();
            return sek.multiply(euroCurrency).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (WebClientResponseException ex) {
            throw new ResourceNotFoundException("Error fetching exchange rate", ex);
        }
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
