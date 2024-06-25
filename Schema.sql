-- Skapa schema
CREATE SCHEMA IF NOT EXISTS wigellsdb;

-- Använd schema
USE wigellsdb;

-- Skapa tabeller

-- Tabell för kunder
CREATE TABLE IF NOT EXISTS sushi_customers (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);

-- Tabell för rum
CREATE TABLE IF NOT EXISTS sushi_rooms (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    max_guests INT NOT NULL,
    equipment VARCHAR(255)
);

-- Tabell för kundorder
CREATE TABLE IF NOT EXISTS sushi_customerorders (
    id INT PRIMARY KEY,
    customer_id INT,
    total_price_sek DECIMAL(10, 2) NOT NULL,
    total_price_euro DECIMAL(10, 2) NOT NULL,
    takeaway BOOLEAN NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES sushi_customers(id)
);

-- Tabell för bokningar
CREATE TABLE IF NOT EXISTS sushi_bookings (
    id INT PRIMARY KEY,
    customer_id INT,
    number_of_guests INT NOT NULL,
    date DATE NOT NULL,
    total_price_sek DECIMAL(10, 2) NOT NULL,
    total_price_euro DECIMAL(10, 2) NOT NULL,
    room_id INT,
    customer_order_id INT,
    FOREIGN KEY (customer_id) REFERENCES sushi_customers(id),
    FOREIGN KEY (room_id) REFERENCES sushi_rooms(id),
    FOREIGN KEY (customer_order_id) REFERENCES sushi_customerorders(id)
);

-- Tabell för rätter
CREATE TABLE IF NOT EXISTS sushi_dishes (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price_sek DECIMAL(10, 2) NOT NULL,
    price_euro DECIMAL(10, 2) NOT NULL
);

-- Tabell för relationer mellan order och rätter
CREATE TABLE IF NOT EXISTS sushi_order_dish (
    order_id INT,
    dish_id INT,
    PRIMARY KEY (order_id, dish_id),
    FOREIGN KEY (order_id) REFERENCES sushi_customerorders(id),
    FOREIGN KEY (dish_id) REFERENCES sushi_dishes(id)
);

