-- customer_orders.sql
-- Insert CustomerOrders into wigellsdb.sushi_customerorders
INSERT INTO sushi_customerorders (userID, customer_id, total_price_sek, total_price_euro, takeaway) VALUES
(1, 1, 200, 20, true),
(2, 2, 300, 30, false);
