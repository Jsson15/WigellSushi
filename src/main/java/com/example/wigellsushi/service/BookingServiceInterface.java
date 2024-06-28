package com.example.wigellsushi.service;

import com.example.wigellsushi.model.Bookings;

import java.util.List;

public interface BookingServiceInterface {
    Bookings bookRoom(Bookings booking);

    Bookings updateBooking(Bookings booking, int bookingID);

    List<Bookings> getAllBookings(int userID);
}
