CREATE DATABASE IF NOT EXISTS hotelMS;
USE hotelMS;

-- LOGIN TABLES
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(30) NOT NULL PRIMARY KEY,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS login2 (
    username VARCHAR(30) NOT NULL PRIMARY KEY,
    password VARCHAR(30) NOT NULL
);

-- ROOM TABLE
CREATE TABLE IF NOT EXISTS room (
    room_number INT PRIMARY KEY,
    availability VARCHAR(30) NOT NULL,
    cleaning_status VARCHAR(30) NOT NULL,
    bed_type VARCHAR(30) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- DEPARTMENT TABLE
CREATE TABLE IF NOT EXISTS department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(30) NOT NULL,
    budget DECIMAL(10,2) NOT NULL
);

-- EMPLOYEE TABLE
CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(15) NOT NULL,
    email VARCHAR(60) NOT NULL,
    phone VARCHAR(25) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    department_id INT,
    hire_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_emp_dept FOREIGN KEY (department_id)
        REFERENCES department(department_id)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- CUSTOMER TABLE
CREATE TABLE IF NOT EXISTS customer (
    customer_id INT PRIMARY KEY,
    id_type VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(60) NOT NULL,
    phone VARCHAR(25) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    country VARCHAR(30) NOT NULL,
    checkin_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deposit DECIMAL(10,2) NOT NULL,
    room_number INT,
    CONSTRAINT fk_cust_room FOREIGN KEY (room_number)
        REFERENCES room(room_number)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- BOOKINGS TABLE
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT PRIMARY KEY,
    customer_id INT NOT NULL,
    room_number INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    checkin_date TIMESTAMP NOT NULL,
    checkout_date TIMESTAMP NOT NULL,
    booking_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booking_cust FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_booking_room FOREIGN KEY (room_number)
        REFERENCES room(room_number)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- INSERTS FOR DEPARTMENT
INSERT INTO department (department_id, department_name, budget) VALUES
(1, 'Front Desk', 5000),
(2, 'Housekeeping', 6000),
(3, 'Kitchen Staff', 7000),
(4, 'Room Service', 8000),
(5, 'Manager', 9000),
(6, 'Accountant', 10000),
(7, 'Chef', 11000);

-- EXAMPLE INSERTS
INSERT INTO login(username, password) VALUES('pepo123', '123456789');
INSERT INTO login2(username, password) VALUES('pepo123', '123456789');

-- VERIFY
SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM login;
SELECT * FROM room;
SELECT * FROM customer;
SELECT * FROM bookings;




UPDATE room
SET cleaning_status = "Clean";
