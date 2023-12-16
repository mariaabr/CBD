CREATE KEYSPACE IF NOT EXISTS gamestore WITH replication = {'class': 'SimpleStrategy','replication_factor': 1};
USE gamestore;

DROP TABLE IF EXISTS clients;
CREATE TABLE clients(
    id int primary key,
    name text,
    email text,
    phone text,
    voucher_balance text,
    shop_list list<text>
);

DROP TABLE IF EXISTS consoles;
CREATE TABLE consoles(
    id text primary key,
    type text,
    model text,
    accessories set<text>,
    price text
);

DROP TABLE IF EXISTS games;
CREATE TABLE games(
    name text,
    description text,
    tags set<text>,
    console map<text, text>,
    quantity int,
    sale_price text,
    buy_price text,
    voucher_price text,
    primary key (name, sale_price)
);

DROP TABLE IF EXISTS games_by_price;
CREATE TABLE games_by_price(
    name text,
    description text,
    tags set<text>,
    console map<text, text>,
    quantity int,
    sale_price text,
    buy_price text,
    voucher_price text,
    primary key (sale_price, quantity)
);

DROP TABLE IF EXISTS informatic;
CREATE TABLE informatic(
    model text primary key,
    type text,
    brand text,
    price text
);

DROP TABLE IF EXISTS informatic_by_type;
CREATE TABLE informatic_by_type(
    model text,
    type text,
    brand text,
    price text,
    primary key (type, price)
);