INSERT INTO orders (created_on, delivered_on)
VALUES ('2023-09-08', null);
UPDATE orders
set delivered_on = '2023-09-10'
where id = 1;

INSERT INTO users (first_name, last_name, telephone, email, order_id)
VALUES ('Pasha', 'Ivanov', '555-555', 'test@test.ru', 1);