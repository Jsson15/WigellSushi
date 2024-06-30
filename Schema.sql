-- Skapa schema
CREATE SCHEMA IF NOT EXISTS wigellsdb;

-- Använd schema
USE wigellsdb;


-- Visa all data från sushi_customers
SELECT * FROM wigellsdb.sushi_customers;

-- Visa all data från sushi_rooms
SELECT * FROM wigellsdb.sushi_rooms;

-- Visa all data från sushi_customerorders
SELECT * FROM wigellsdb.sushi_customerorders;

-- Visa all data från sushi_bookings
SELECT * FROM wigellsdb.sushi_bookings;

-- Visa all data från sushi_dishes
SELECT * FROM wigellsdb.sushi_dishes;

-- Visa all data från sushi_order_dish
SELECT * FROM wigellsdb.sushi_order_dish;





-- Tabell för kunder
CREATE TABLE IF NOT EXISTS sushi_customers (
    userid INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL,
    userfullname VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);

-- Tabell för rum
CREATE TABLE IF NOT EXISTS sushi_rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    max_guests INT NOT NULL,
    equipment VARCHAR(255)
);

-- Tabell för kundorder
CREATE TABLE IF NOT EXISTS sushi_customerorders (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    total_price_sek DECIMAL(10, 2) NOT NULL,
    total_price_euro DECIMAL(10, 2) NOT NULL,
    takeaway BOOLEAN NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES sushi_customers(id)
);

-- Tabell för bokningar
CREATE TABLE IF NOT EXISTS sushi_bookings (
    bookingID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    numberOfGuests INT NOT NULL,
    roomID INT NOT NULL,
    bookingDate DATE NOT NULL,
    isActive BOOLEAN,
    totalPriceEuro DECIMAL(10, 2),
    totalPriceSek DECIMAL(10, 2) NOT NULL,
    customerOrderID INT,
    FOREIGN KEY (userID) REFERENCES sushi_customers(id),
    FOREIGN KEY (roomID) REFERENCES sushi_rooms(id),
    FOREIGN KEY (customerOrderID) REFERENCES sushi_customerorders(id)
);

-- Tabell för rätter
CREATE TABLE IF NOT EXISTS sushi_dishes (
    dishID INT AUTO_INCREMENT PRIMARY KEY,
    dish_name VARCHAR(50) NOT NULL,
    ingredients VARCHAR(100),
    price_euro DECIMAL(10, 2) NOT NULL,
    price_sek DECIMAL(10, 2) NOT NULL
);

-- Tabell för relationer mellan order och rätter
CREATE TABLE IF NOT EXISTS sushi_order_dish (
    order_id INT,
    dish_id INT,
    PRIMARY KEY (order_id, dish_id),
    FOREIGN KEY (order_id) REFERENCES sushi_customerorders(id),
    FOREIGN KEY (dish_id) REFERENCES sushi_dishes(dishID)
);