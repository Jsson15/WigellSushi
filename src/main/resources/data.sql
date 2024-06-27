-- bookings.sql
-- Insert Bookings into wigellsdb.sushi_bookings
INSERT INTO wigellsdb.sushi_bookings (id, customer_id, number_of_guests, date, total_price_sek, total_price_euro, room_id, customer_order_id) VALUES
                                                                                                                                                  (1, 1, 8, '2024-06-10', 1000, 100, 1, 1),
                                                                                                                                                  (2, 2, 5, '2024-05-15', 500, 50, 2, 2);
-- customer_orders.sql
-- Insert CustomerOrders into wigellsdb.sushi_customerorders
INSERT INTO wigellsdb.sushi_customerorders (id, customer_id, total_price_sek, total_price_euro, takeaway) VALUES
                                                                                                              (1, 1, 200, 20, true),
                                                                                                              (2, 2, 300, 30, false);
-- customers.sql
-- Insert Customers into wigellsdb.sushi_customers
INSERT INTO wigellsdb.sushi_customers (id, username, name, address) VALUES
                                                                        (1, 'custuser1', 'Customer One', 'Address One'),
                                                                        (2, 'custuser2', 'Customer Two', 'Address Two');

-- dishes.sql
-- Insert Dishes into wigellsdb.sushi_dishes
INSERT INTO wigellsdb.sushi_dishes (dishID, dish_Name, ingredients, price_Sek, price_Euro) VALUES
                                                                                            (1, 'Sushi Roll', 'Rice, Seaweed, Fish', 100, 8.9),
                                                                                            (2, 'Sashimi', 'Raw Fish', 150, 13.35),
                                                                                            (3, 'Tempura', 'Fried Vegetables', 120, 10.68),
                                                                                            (4, 'Miso Soup', 'Soybean Paste, Tofu', 80, 7.12);


-- order_dish.sql
-- Insert Order_Dish relationships into wigellsdb.sushi_order_dish
INSERT INTO wigellsdb.sushi_order_dish (order_id, dish_id) VALUES
                                                               (1, 1),
                                                               (2, 2),
                                                               (1, 3),
                                                               (2, 4);

-- rooms.sql
-- Insert Rooms into wigellsdb.sushi_rooms
INSERT INTO wigellsdb.sushi_rooms (id, name, max_guests, equipment) VALUES
                                                                        (1, 'Room A', 8, 'Projector'),
                                                                        (2, 'Room B', 5, null);
