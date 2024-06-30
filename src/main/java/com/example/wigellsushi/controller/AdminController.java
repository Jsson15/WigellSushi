package com.example.wigellsushi.controller;

import com.example.wigellsushi.model.Dishes;
import com.example.wigellsushi.model.Room;
import com.example.wigellsushi.model.TakeAwayOrders;
import com.example.wigellsushi.model.User;
import com.example.wigellsushi.service.MenyService;
import com.example.wigellsushi.service.RoomService;
import com.example.wigellsushi.service.TakeAwayService;
import com.example.wigellsushi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v3")
public class AdminController {

    @Autowired
    private MenyService menyService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TakeAwayService takeAwayService;

    public AdminController() {
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(value = "/add-dish")
    public ResponseEntity<Dishes> addDishes(@Valid @RequestBody Dishes dishes) {
        Dishes savedDish = menyService.addDishes(dishes);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDish);
    }

    @DeleteMapping(value = "/deletedish/{id}")
    public ResponseEntity<Dishes> deleteDishes(@Valid @PathVariable("id") int dishID) {
        menyService.deleteDish(dishID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/updateroom/{id}")
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room1, @PathVariable("id") int roomID) {
        return ResponseEntity.ok(roomService.updateRoom(room1, roomID));
    }

    @GetMapping(value = "/allrooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping(value = "/takeaway")
    public ResponseEntity<List<TakeAwayOrders>> getAllTakeAwayOrders() {
        return ResponseEntity.ok(takeAwayService.getAllTakeAwayOrders());
    }
}
