CREATE TABLE IF NOT EXISTS `prices` (
    id_Price INT AUTO_INCREMENT PRIMARY KEY,
    id_Brand INT,
    start_Date DATETIME,
    end_Date DATETIME,
    id_Price_Rate INT,
    id_Product INT,
    priority INT,
    product_price DECIMAL(10, 2),
    currency VARCHAR(3)
);
