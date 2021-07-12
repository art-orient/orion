DROP database IF EXISTS orion;
CREATE database orion;
USE orion;

CREATE table users (
username varchar(30) primary key not null,
password varchar(64) not null,
firstname varchar(30),
lastname varchar(30),
email varchar(50),
role int not null,
active boolean
);

CREATE table shoes (
shoes_id int primary key auto_increment,
type_Ru varchar(30),
type_En varchar(30),
brand varchar(30),
model_name varchar(30),
description_RU text,
description_EN text,
image_path varchar(100),
color varchar(30),
cost decimal(6, 2),
active boolean
);

CREATE table shoes_sizes (
shoes_id int,
size double(2, 1),
availability int,
PRIMARY KEY (shoes_id, size),
FOREIGN KEY (shoes_id) REFERENCES shoes (shoes_id)
);

CREATE table shoes_add_photos (
path varchar(100) primary key,
shoes_id int,
FOREIGN KEY (shoes_id) REFERENCES shoes (shoes_id)
);

CREATE table clothing (
clothing_id int primary key auto_increment,
type_Ru varchar(30),
type_En varchar(30),
brand varchar(30),
model_name varchar(30),
description_RU text,
description_EN text,
image_path varchar(100),
color varchar(30),
cost decimal(6, 2),
active boolean
);

CREATE table clothing_sizes (
clothing_id int,
size int,
availability int,
PRIMARY KEY (clothing_id, size),
FOREIGN KEY (clothing_id) REFERENCES clothing (clothing_id)
);

CREATE table accessories (
accessories_id int primary key auto_increment,
type_Ru varchar(30),
type_En varchar(30),
brand varchar(30),
model_name varchar(30),
description_RU text,
description_EN text,
image_path varchar(100),
cost decimal(6, 2),
availability int,
active boolean
);

CREATE table orders (
order_id int primary key auto_increment,
username varchar(30) not null,
order_date date,
order_cost decimal(6, 2) not null,
confirmation_status boolean,
FOREIGN KEY (username) REFERENCES users (username)
);

CREATE table order_details (
order_id int,
product_category int,
product_id int,
number_of_products int not null,
products_cost decimal(6, 2) not null,
PRIMARY KEY (order_id, product_category, product_id),
FOREIGN KEY (order_id) REFERENCES orders (order_id)
);