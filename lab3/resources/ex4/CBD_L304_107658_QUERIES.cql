USE gamestore;

-- 2 secondary indexes
DROP INDEX IF EXISTS clients_name;
CREATE INDEX clients_name ON clients(name);

DROP INDEX IF EXISTS consoles_name;
CREATE INDEX consoles_name ON consoles(type);

-- 5 deletes
DELETE FROM consoles where id = 'NGC';
DELETE FROM consoles where id = 'XSX';
DELETE FROM clients where id = 13;
DELETE FROM informatic where model = 'Webcam Logitech 167';
DELETE FROM games where name = 'Assassin s Creed Valhalla';

-- insert console para compensar o delete
INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('PSP2', 'PlayStation', 'PSP Lilas', {'Memory Card', 'PS Camera'}, '109.70');

-- 5 updates
UPDATE clients SET phone = '+351960058403' where id = 14;
UPDATE clients SET shop_list = ['Animal Crossing', 'Nintendo Switch', 'Mario Kart 8 Deluxe'] where id = 5;
UPDATE consoles SET price = '85.90' where id = 'PS2';
UPDATE consoles SET accessories = accessories - {'Memory Card'} where id = 'PSP1';
UPDATE games SET quantity = 5 where name = 'Animal Crossing' and sale_price='49.90';

-- 10 queries
-- 1. listar todos os clientes - done
SELECT * FROM clients;

-- 2. listar um cliente pelo nome - done
select * from clients where name = 'João Silva';

-- 3. listar 3 consolas de um certo tipo - done
SELECT * FROM consoles where type = 'PlayStation' limit 3;

id   | accessories                                  | model         | price  | type
------+----------------------------------------------+---------------+--------+-------------
 PSP1 |                                {'PS Camera'} |           PSP | 279.20 | PlayStation
  PS5 | {'DualSense Controller', 'Pulse 3D Headset'} | PlayStation 5 | 499.70 | PlayStation
  PS2 |    {'DualShock 2 Controller', 'Memory Card'} | PlayStation 2 |  85.90 | PlayStation

(3 rows)

-- 4. listar o preço da Xbox360 - done
SELECT price FROM consoles where id = 'X360';

-- 5. listar os accessórios disponíveis para a consola de id = 'N3DS' - done
SELECT accessories FROM consoles where id = 'N3DS';

-- 6. listar os dados referentes ao jogo 'The Witcher 3' - done
select * from games where name = 'The Witcher 3';

-- 7. listar os diferentes preços relacionados com o jogo 'FIFA 22' - done
select sale_price, buy_price, voucher_price from games where name = 'FIFA 22';

-- 8. listar os jogos cujo preço é 49.90 e a quantidade é menor que 8 - done
select * from games_by_price where sale_price = '49.90' and quantity < 8;

-- 9. listar os de tipo computador ordenados pelo preço, do mais caro para o mais barato - done
select * from informatic_by_type where type = 'Computador' ORDER BY price DESC;

-- 10. listar o produto informático de modelo = 'Laptop Dell XPS 13' - done
SELECT * FROM informatic where model = 'Laptop Dell XPS 13';