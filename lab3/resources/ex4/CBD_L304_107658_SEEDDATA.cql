USE gamestore;

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (1, 'João Silva', 'joao.silva@gmail.com', '+351912345678', '20.00', ['PlayStation 5', 'Headset Gamer', 'FIFA 22']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (2, 'Maria Santos', 'maria.santos@gmail.com', '+351923456789', '15.00', ['Nintendo Switch', 'Animal Crossing']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (3, 'Pedro Costa', 'pedro.costa@gmail.com', '+351934567890', '10.00', ['Xbox Series X', 'Halo Infinite']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (4, 'Marta Oliveira', 'marta.oliveira@gmail.com', '+351915678901', '30.00', ['PlayStation 4', 'The Last of Us Part II']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (5, 'Manuel Rodrigues', 'manuel.rodrigues@gmail.com', '+351916789012', '25.00', ['Nintendo Switch', 'Mario Kart 8 Deluxe']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (6, 'Ana Silva', 'ana.silva@gmail.com', '+351967890123', '12.00', ['Xbox One', 'FIFA 21']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (7, 'Carlos Pereira', 'carlos.pereira@gmail.com', '+351968901234', '18.00', ['PC Gamer', 'Cyberpunk 2077']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (8, 'Sofia Ferreira', 'sofia.ferreira@gmail.com', '+351919012345', '20.00', ['Nintendo Switch', 'Zelda: Breath of the Wild']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (9, 'Hugo Santos', 'hugo.santos@gmail.com', '+351920123456', '22.00', ['Xbox Series S', 'Assassin s Creed Valhalla']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (10, 'Ines Costa', 'ines.costa@gmail.com', '+351911234567', '8.00', ['PlayStation 5', 'Demon’s Souls']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (11, 'Rui Oliveira', 'rui.oliveira@gmail.com', '+351922345678', '28.00', ['PC Gamer', 'The Witcher 3']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (12, 'Catarina Rodrigues', 'catarina.rodrigues@gmail.com', '+351933456789', '16.00', ['Nintendo Switch', 'Super Smash Bros. Ultimate']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (13, 'Gonçalo Silva', 'goncalo.silva@gmail.com', '+351964567890', '18.00', ['Xbox One', 'Forza Horizon 4']);

INSERT INTO clients (id, name, email, phone, voucher_balance, shop_list) 
VALUES (14, 'Lúcia Pereira', 'lucia.pereira@gmail.com', '+351915678901', '23.00', ['God of War', 'PlayStation 4', 'God of War']);



INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('PS5', 'PlayStation', 'PlayStation 5', {'DualSense Controller', 'VR Camera'}, '599.99');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('NS', 'Nintendo', 'Nintendo Switch', {'Joy-Con Controllers', 'Dock Set'}, '324.80');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('PSP1', 'PlayStation', 'PSP', {'Memory Card', 'PS Camera'}, '279.20');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('XBO', 'Xbox', 'Xbox One', {'Xbox Wireless Controller', 'Play & Charge Kit'}, '245.10');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('PS5', 'PlayStation', 'PlayStation 5', {'DualSense Controller', 'Pulse 3D Headset'}, '499.70');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('XSX', 'Xbox', 'Xbox Series X', {'Xbox Wireless Controller', 'Xbox Play & Charge Kit'}, '496.90');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('XSS', 'Xbox', 'Xbox Series S', {'Xbox Wireless Controller', 'Digital Game Code'}, '297.30');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('N3DS', 'Nintendo', 'Nintendo 3DS', {'Stylus Pen', 'SD Card'}, '194.35');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('WiV2', 'Wii', 'Wii V2 Branco', {'Wii Official Wheel', 'Wii Official Remote'}, '149.60');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('N64', 'Nintendo', 'Nintendo 64', {'Expansion Pak', 'Controller Pak'}, '49.75');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('NGC', 'Nintendo', 'Nintendo GameCube', {'Memory Card', 'Game Boy Player'}, '96.40');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('X360', 'Xbox', 'Xbox 360', {'Wireless Controller', 'Headset'}, '125.85');

INSERT INTO consoles (id, type, model, accessories, price) 
VALUES ('PS2', 'PlayStation', 'PlayStation 2', {'DualShock 2 Controller', 'Memory Card'}, '92.10');



INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('FIFA 22', 'Simulador de futebol', {'esporte', 'futebol'}, {'PlayStation 5': 'PlayStation'}, 50, '69.99', '49.99', '30.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Animal Crossing', 'Simulador de vida', {'simulação', 'vida'}, {'Nintendo Switch': 'Nintendo'}, 8, '49.90', '39.90', '20.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Halo Infinite', 'Jogo de tiro em primeira pessoa', {'ação', 'tiro'}, {'Xbox Series X': 'Xbox'}, 10, '54.90', '44.90', '25.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('The Last of Us Part II', 'Jogo de aventura e sobrevivência', {'aventura', 'sobrevivência'}, {'PlayStation 4': 'PlayStation'}, 7, '49.90', '39.90', '20.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Mario Kart 8 Deluxe', 'Jogo de corrida com personagens da Nintendo', {'corrida', 'multiplayer'}, {'Nintendo Switch': 'Nintendo'}, 12, '44.90', '34.90', '15.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('FIFA 21', 'Jogo de futebol', {'esporte', 'futebol'}, {'Xbox One': 'Xbox'}, 9, '49.90', '39.90', '20.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Cyberpunk 2077', 'RPG de ação futurista', {'ação', 'futurista'}, {'PC Gamer': 'PC'}, 6, '54.90', '44.90', '25.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Zelda: Breath of the Wild', 'Aventura épica da Nintendo', {'aventura', 'fantasia'}, {'Nintendo Switch': 'Nintendo'}, 11, '49.90', '39.90', '20.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Assassin s Creed Valhalla', 'Aventura na era dos vikings', {'aventura', 'historico'}, {'Xbox Series S': 'Xbox'}, 8, '54.90', '44.90', '25.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Demon’s Souls', 'Jogo de RPG de ação', {'ação', 'fantasia'}, {'PlayStation 5': 'PlayStation'}, 6, '59.90', '49.90', '30.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('The Witcher 3', 'RPG de ação com mundo aberto', {'ação', 'mundo aberto'}, {'PC Gamer': 'PC'}, 7, '54.90', '44.90', '25.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Super Smash Bros. Ultimate', 'Jogo de luta com personagens da Nintendo', {'luta', 'multiplayer'}, {'Nintendo Switch': 'Nintendo'}, 9, '49.90', '39.90', '20.00');

INSERT INTO games (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Forza Horizon 4', 'Jogo de corrida', {'corrida', 'simulação'}, {'Xbox One': 'Xbox'}, 5, '54.90', '44.90', '25.00');



INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('FIFA 22', 'Simulador de futebol', {'esporte', 'futebol'}, {'PlayStation 5': 'PlayStation'}, 50, '69.99', '49.99', '30.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Animal Crossing', 'Simulador de vida', {'simulação', 'vida'}, {'Nintendo Switch': 'Nintendo'}, 8, '49.90', '39.90', '20.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Halo Infinite', 'Jogo de tiro em primeira pessoa', {'ação', 'tiro'}, {'Xbox Series X': 'Xbox'}, 10, '54.90', '44.90', '25.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('The Last of Us Part II', 'Jogo de aventura e sobrevivência', {'aventura', 'sobrevivência'}, {'PlayStation 4': 'PlayStation'}, 7, '49.90', '39.90', '20.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Mario Kart 8 Deluxe', 'Jogo de corrida com personagens da Nintendo', {'corrida', 'multiplayer'}, {'Nintendo Switch': 'Nintendo'}, 12, '44.90', '34.90', '15.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('FIFA 21', 'Jogo de futebol', {'esporte', 'futebol'}, {'Xbox One': 'Xbox'}, 9, '49.90', '39.90', '20.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Cyberpunk 2077', 'RPG de ação futurista', {'ação', 'futurista'}, {'PC Gamer': 'PC'}, 6, '54.90', '44.90', '25.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Zelda: Breath of the Wild', 'Aventura épica da Nintendo', {'aventura', 'fantasia'}, {'Nintendo Switch': 'Nintendo'}, 11, '49.90', '39.90', '20.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Assassin s Creed Valhalla', 'Aventura na era dos vikings', {'aventura', 'historico'}, {'Xbox Series S': 'Xbox'}, 8, '54.90', '44.90', '25.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Demon’s Souls', 'Jogo de RPG de ação', {'ação', 'fantasia'}, {'PlayStation 5': 'PlayStation'}, 6, '59.90', '49.90', '30.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('The Witcher 3', 'RPG de ação com mundo aberto', {'ação', 'mundo aberto'}, {'PC Gamer': 'PC'}, 7, '54.90', '44.90', '25.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Super Smash Bros. Ultimate', 'Jogo de luta com personagens da Nintendo', {'luta', 'multiplayer'}, {'Nintendo Switch': 'Nintendo'}, 9, '49.90', '39.90', '20.00');

INSERT INTO games_by_price (name, description, tags, console, quantity, sale_price, buy_price, voucher_price) 
VALUES ('Forza Horizon 4', 'Jogo de corrida', {'corrida', 'simulação'}, {'Xbox One': 'Xbox'}, 5, '54.90', '44.90', '25.00');



INSERT INTO informatic (model, type, brand, price) 
VALUES ('Mouse Gamer', 'Rato', 'Razer', '49.99');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Gaming Headset', 'Auscultadores', 'Razer', '49.95');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Mechanical Keyboard', 'Teclado', 'Corsair', '69.50');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Gaming Mouse Pad', 'Tapete', 'SteelSeries', '29.75');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('External Hard Drive', 'Disco', 'Seagate', '49.25');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Wi-Fi Router', 'Router', 'TP-Link', '39.60');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Monitor LG 27UK850-W', 'Monitor', 'LG', '239.75');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Laptop Dell XPS 13', 'Computador', 'Dell', '689.50');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Desktop Acer Aspire TC', 'Computador', 'Acer', '619.25');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Tablet Asus ZenPad 10', 'Computador', 'Asus', '289.70');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Printer', 'Periférico', 'Epson', '139.80');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('Webcam Logitech 167', 'Periférico', 'Logitech', '52.60');

INSERT INTO informatic (model, type, brand, price) 
VALUES ('External SSD', 'Disco', 'SanDisk', '84.40');



INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Mouse Gamer', 'Rato', 'Razer', '49.99');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Gaming Headset', 'Auscultadores', 'Razer', '49.95');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Mechanical Keyboard', 'Teclado', 'Corsair', '69.50');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Gaming Mouse Pad', 'Tapete', 'SteelSeries', '29.75');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('External Hard Drive', 'Disco', 'Seagate', '49.25');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Wi-Fi Router', 'Router', 'TP-Link', '39.60');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Monitor LG 27UK850-W', 'Monitor', 'LG', '239.75');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Laptop Dell XPS 13', 'Computador', 'Dell', '689.50');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Desktop Acer Aspire TC', 'Computador', 'Acer', '619.25');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Tablet Asus ZenPad 10', 'Computador', 'Asus', '289.70');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Printer', 'Periférico', 'Epson', '139.80');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('Webcam Logitech 167', 'Periférico', 'Logitech', '52.60');

INSERT INTO informatic_by_type (model, type, brand, price) 
VALUES ('External SSD', 'Disco', 'SanDisk', '84.40');