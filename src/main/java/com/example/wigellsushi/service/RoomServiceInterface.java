package com.example.wigellsushi.service;

import com.example.wigellsushi.model.Room;

import java.util.List;

public interface RoomServiceInterface {


    Room updateRoom (Room room, int roomID);
    Room addRoom(Room room);
    List<Room> getAllRooms();
    List<Room> getAvailableRoom();

}
