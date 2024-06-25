package com.example.wigellsushi.controller;

import com.example.wigellsushi.model.Bookings;
import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.model.TakeAwayOrders;
import com.example.wigellsushi.model.User;
import com.example.wigellsushi.service.BookingService;
import com.example.wigellsushi.service.MenyService;
import com.example.wigellsushi.service.TakeAwayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v3")
public class CustomerController {
    @Autowired
    private MenyService menyService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TakeAwayService takeAwayService;


    @GetMapping(value = "/dishes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Dishes>> getAllDishes() {
        return ResponseEntity.ok(menyService.getAllDishes());
    }


    @PostMapping(value = "/bookroom")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Bookings> bookRoom(@Valid @RequestBody Bookings booking) {

        return ResponseEntity.ok(bookingService.bookRoom(booking));
    }

    @PutMapping(value = "/updatebooking/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Bookings> updateBooking(@Valid @RequestBody Bookings booking, @PathVariable("id") int bookingID) {
        return ResponseEntity.ok(bookingService.updateBooking(booking, bookingID));
    }


    @GetMapping(value = "/mybookings/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Bookings>> getAllBookings(@Valid @PathVariable("id") int userID) {

        return ResponseEntity.ok(bookingService.getAllBookings(userID));
    }

    @PostMapping(value = "/orderTakeAway")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TakeAwayOrders> takeAwayOrders(@Valid @RequestBody TakeAwayOrders takeAwayOrders) {

        return ResponseEntity.ok(takeAwayService.placeTakeAwayOrder(takeAwayOrders));
    }
}
