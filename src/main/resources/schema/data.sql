CREATE TABLE IF NOT EXISTS brand
(
    id INT PRIMARY KEY,
    name  VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS catalogue_prices
(
    price_list INT PRIMARY KEY,
    brand_id   INT,
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    product_id INT,
    priority   INT,
    price      DOUBLE,
    currency   VARCHAR(5),
    FOREIGN KEY (brand_id) REFERENCES brand (id)
);

INSERT INTO brand (id, name)
SELECT 1, 'ZARA'
    WHERE NOT EXISTS (SELECT 1 FROM brand LIMIT 1);

INSERT INTO catalogue_prices (price_list, brand_id, start_date, end_date, product_id, priority, price, currency)
SELECT 1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR'
    WHERE NOT EXISTS (SELECT 1 FROM catalogue_prices LIMIT 1)
UNION ALL
SELECT 2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR'
    WHERE NOT EXISTS (SELECT 1 FROM catalogue_prices LIMIT 1)
UNION ALL
SELECT 3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR'
    WHERE NOT EXISTS (SELECT 1 FROM catalogue_prices LIMIT 1)
UNION ALL
SELECT 4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR'
    WHERE NOT EXISTS (SELECT 1 FROM catalogue_prices LIMIT 1);