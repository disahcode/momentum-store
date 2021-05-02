DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer
(
    id      serial PRIMARY KEY NOT NULL,
    name            character varying,
    active_day_points smallint

);

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product
(
    code          serial PRIMARY KEY NOT NULL,
    name          character varying  NOT NULL,
    cost          smallint           NOT NULL


);
insert into customer
values (1, 'Modisa Mokgethi',800),
       (2, 'Jennifer Luchmia',1600),
       (3, 'Cyril Ramaphosa',0);

insert into product
values (1, 'Product 1', 150),
(2, 'Product 2', 300),
(3, 'Product 3', 450),
(4, 'Product 4', 600),
(5, 'Product 5', 750);

