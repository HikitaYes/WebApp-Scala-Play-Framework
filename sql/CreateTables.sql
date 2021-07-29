CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone_number VARCHAR(10)
);

CREATE TABLE dishes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    image_src VARCHAR(50) NOT NULL,
    gram INT NOT NULL,
    price INT NOT NULL
);

CREATE TABLE booking (
    id SMALLINT PRIMARY KEY,
    id_user INT REFERENCES users
);

INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Нью-Йорк', 'Стейк из филе тонкого края', 'images/dishes/1.jpg', 300, 580);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Рибай', 'Стейк из толстого края бычка', 'images/dishes/2.jpg', 350, 790);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Тибон', 'Стейк на Т-образной кости', 'images/dishes/9.jpg', 550, 1480);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Вырезка ягненка гриль', 'Нежное, диетическое мясо', 'images/dishes/6.jpg', 250, 790);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Фирменный Перечный', 'Стейк из толстого края бычка в 5 видах перца', 'images/dishes/7.jpg', 350, 1650);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Белый стейк из мяса индейки', 'Филе индейки, маринованное в свежевыжатом апельсиновом соке', 'images/dishes/5.jpg', 250, 420);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Миньон из мраморной говяжей вырезки', 'Стейк из центральной части говяжьей вырезки', 'images/dishes/4.jpg', 250, 1030);
INSERT INTO dishes (name, description, image_src, gram, price)
VALUES ('Стейк Шатобриан', 'Стейк из толстой центральной части говяжьей вырезки', 'images/dishes/8.jpg', 550, 1950);

INSERT INTO booking SELECT generate_series(1, 6);
