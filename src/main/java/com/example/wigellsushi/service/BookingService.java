package com.example.wigellsushi.service;

import com.example.wigellsushi.logging.Log4j;
import com.example.wigellsushi.model.*;
import com.example.wigellsushi.repository.BookingsRepository;
import com.example.wigellsushi.repository.DishBookingRepository;
import com.example.wigellsushi.repository.RoomRepository;
import com.example.wigellsushi.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

@Service
public class BookingService implements BookingServiceInterface {
    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/fd910a7e3d1255ef612fb638/latest/SEK";

    @Autowired
    private MenyService menyService;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private DishBookingRepository dishBookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public Bookings bookRoom(Bookings booking) {
        BigDecimal totalPriceSEK = calculateTotalPriceSEK(booking);
        booking.setTotalPriceSek(totalPriceSEK);

        try {
            booking.setTotalPriceEuro(calculateTotalPriceEuro(totalPriceSEK));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Room room = roomRepository.findById(booking.getRoomID()).orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getIsBooked()) {
            throw new RuntimeException("This room is already booked");
        } else {
            room.setIsBooked(true);
            bookingsRepository.save(booking);
            for (DishBooking db : booking.getDishBooking()) {
                db.setBooking(booking);
            }
            dishBookingRepository.saveAll(booking.getDishBooking());
            roomRepository.save(room);
            Log4j.logger.info("Customer added a booking: " + booking);

            return booking;
        }
    }

    public Bookings updateBooking(Bookings booking, int bookingID) {
        Bookings bookingToUpdate = bookingsRepository.findBookingsByBookingID(bookingID);

        bookingToUpdate.setNumberOfGuests(booking.getNumberOfGuests());
        bookingToUpdate.setRoomID(booking.getRoomID());

        bookingToUpdate.setActive(booking.isActive());
        BigDecimal totalPriceSEK = calculateTotalPriceSEK(booking);
        bookingToUpdate.setTotalPriceSek(totalPriceSEK);
        try {
            bookingToUpdate.setTotalPriceEuro(calculateTotalPriceEuro(totalPriceSEK));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dishBookingRepository.deleteAll(bookingToUpdate.getDishBooking());

        bookingToUpdate.setDishBooking(null);
        bookingsRepository.save(bookingToUpdate);

        for (DishBooking db : booking.getDishBooking()) {
            db.setBooking(booking);
        }
        dishBookingRepository.saveAll(booking.getDishBooking());

        bookingToUpdate.setDishBooking(booking.getDishBooking());

        Log4j.logger.info("Customer changed a booking: " + booking);
        return bookingToUpdate;
    }

    public List<Bookings> getAllBookings(int userID) {
        return bookingsRepository.findBookingsByUser(userRepository.findById(userID).get());
    }

    private BigDecimal calculateTotalPriceSEK(Bookings booking) {
        BigDecimal totalPriceSEK = BigDecimal.ZERO;
        List<Dishes> dishesList = menyService.getAllDishes();

        for (Dishes dish : dishesList) {
            for (DishBooking dishBooking : booking.getDishBooking()) {
                if (dishBooking.getDish().getDishID() == dish.getDishID()) {
                    BigDecimal price = dish.getPriceSek().multiply(BigDecimal.valueOf(dishBooking.getQuantity()));
                    totalPriceSEK = totalPriceSEK.add(price);
                }
            }
        }

        return totalPriceSEK;
    }

    private BigDecimal calculateTotalPriceEuro(BigDecimal sek) throws IOException {
        URL url = new URL(EXCHANGE_RATE_API_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(url);
        BigDecimal euroCurrency = rootNode.path("conversion_rates").path("EUR").decimalValue();

        return sek.multiply(euroCurrency).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
