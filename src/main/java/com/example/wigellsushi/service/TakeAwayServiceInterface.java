package com.example.wigellsushi.service;

import com.example.wigellsushi.model.Bookings;
import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.model.TakeAwayOrders;

import java.util.List;

public interface TakeAwayServiceInterface {
    TakeAwayOrders placeTakeAwayOrder(TakeAwayOrders takeAwayOrders);
    List<TakeAwayOrders> getAllTakeAwayOrders();
}
