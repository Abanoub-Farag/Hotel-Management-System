-- Creating the database 
create database if not exists hotelMS; 

use hotelMS;

-- Employees login
create table if not exists login(
	username varchar(25),
	password varchar(25)
);
-- 	
-- insert into login (username, password)
-- values ("user", "123456789"), ("a", "a");

-- select * from login;


-- Admins login
create table if not exists login2(
	user_name varchar(25),
	password varchar(25)
);
-- 
-- insert into login2 (user_name, password)
-- values ("user", "123456789"), ("a", "a");

-- select * from login2;

-- Room table
create table if not exists room (
    roomnumber int primary key,
    availability varchar(20),
    cleaning_status varchar(20),
    price int,
    bed_type varchar(20)
);

-- customers table
-- drop table customer;
create table if not exists customer (
    id_type varchar(50),
    id_number varchar(100),
    name varchar(100),
    gender varchar(10),
    country varchar(100),
    roomnumber int,
    checkin_time datetime,
    deposit int
);

-- Employees table
create table if not exists employee (
    name varchar(100),
    age int,
    gender varchar(10),
    job varchar(50),
    salary int,
    phone varchar(20),
    email varchar(100),
    id_number varchar(100)
);

-- select * from employee;

-- select * from customer;

-- Depratments table
-- drop table department;
create table if not exists department(
	department_id int primary key,
	department varchar(25),
	budget int
);


-- insert into department (department_id, department, budget)
-- values (1, "Front Desk", 5000),
-- (2, "HouseKeeping", 6000),
-- (3, "Kitchen Staff", 7000),
-- (4, "Room Service", 8000),
-- (5, "Manager", 9000),
-- (6, "Accountant", 10000),
-- (7, "Chef", 12000);

-- select * from department






