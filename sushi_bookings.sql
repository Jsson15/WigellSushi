-- bookings.sql
-- Insert Bookings into wigellsdb.sushi_bookings
INSERT INTO sushi_bookings (userID, numberOfGuests, roomID, bookingDate, totalPriceSek, totalPriceEuro, customerOrderID, isActive) VALUES 
(1, 8, 1, '2024-06-10', 1000.00, 100.00, 1, TRUE),
(2, 5, 2, '2024-05-15', 500.00, 50.00, 2, TRUE);