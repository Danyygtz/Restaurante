-- MYSQL_ROOT_PASSWORD=mysecret MYSQL_DATABASE=restaurante MYSQL_USER=topicos MYSQL_USER=1234
create database restaurante;
use restaurante;
create table payments(id int primary key auto_increment, payment char(25) not null, status boolean not null default 0);
create table categories(id int primary key auto_increment, category char(100) not null, img char(25) not null);
create table food_items(id integer primary key auto_increment, food char(100) not null, price decimal(10,2) not null, img char(25) not null, check(price > 0), id_category int, foreign key (id_category) references categories(id));
create table orders(id int primary key auto_increment, date_order datetime not null default now(), amount decimal(10,2) not null, check(amount >= 0), id_payment int, foreign key (id_payment) references payments(id));
create table orders_details(order_id int, food_item_id int, quantity decimal(10,0) not null, check(quantity > 0), price decimal(10,2) not null, primary key(order_id, food_item_id), foreign key (order_id) references orders(id), foreign key (food_item_id) references food_items(id));
