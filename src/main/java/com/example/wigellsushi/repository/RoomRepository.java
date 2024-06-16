package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomByIsBookedFalse();


}
