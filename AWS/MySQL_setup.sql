/*
Created by: Zachary Young
Updated by: Zachary Young
Created on: 07/01/2021
Last edited: 07/05/2021
Created for: CMSC495


MySQL

Ensure the following are applied:
- AWS account differs from MySQL account and from Application account

Objectives
  - Secure MySQL instance, Databases and Tables
  - Create new users that can query applicaiton database
  - Lock down new user permissions
  - Create new applicaiton database
  - Create 4 tables: Users, Inventory, Order History, and App Passwords
  
*/

/* Setup and harden SQL instance */

-- show current databases and users
SHOW databases;
SELECT user FROM mysql.user;
USE mysql;

-- create database for the app
CREATE DATABASE inventory_app_data;
SHOW databases;

-- create users for database
CREATE USER 'inventory_admin'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'password';
SELECT user FROM mysql.user;

/*
-- remove user ability to query any database
SHOW GRANTS FOR 'inventory_admin'@'localhost'; 
SHOW GRANTS FOR 'inventory_user'@'localhost'; 
REVOKE ALL PRIVILEGES ON %database_name% FROM 'inventory_admin'@'localhost'; 
REVOKE ALL PRIVILEGES ON %database_name% FROM 'inventory_user'@'localhost'; 
SHOW GRANTS FOR 'inventory_admin'@'localhost'; 
SHOW GRANTS FOR 'inventory_user'@'localhost'; 

-- GRANT inventory_admin for the inventory_app_data database
GRANT ALL PRIVILEGES ON %database_name% TO 'inventory_admin'@'localhost';

-- GRANT inventory_user for inventory_app_data database
GRANT ALL PRIVILEGES ON %database_name% TO 'inventory_user'@'localhost';

-- check permission of users
SHOW GRANTS FOR 'inventory_admin'@'localhost';
SHOW GRANTS FOR 'inventory_user'@'localhost';
*/

/* Build tables */

-- switch to table
SHOW databases;
USE inventory_app_data;

-- User application table
-- Role: 0 = admin, 1 = approver, 2 = employee
CREATE TABLE users(
    UserID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Username VARCHAR(60) UNIQUE NOT NULL, 
    Fname VARCHAR(60) NOT NULL, 
    Lname VARCHAR(60) NOT NULL,
    Role INT DEFAULT 2 CHECK (Role = 1 OR Role = 2 OR Role = 0) 
)
COMMENT="The users table is used for managing application users."
;

-- password table referencing user

-- Inventory table
CREATE TABLE inventory(
    InventoryID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Name VARCHAR(60) UNIQUE NOT NULL,  
    Description VARCHAR(120) UNIQUE NOT NULL,
    Category VARCHAR(60) NOT NULL, 
    WholeSalePrice DECIMAL(8,2) NOT NULL,
    RetailPrice DECIMAL(8,2) NOT NULL,
    Quantity INT NOT NULL
)
COMMENT="The inventory table is used for managing inventory items."
;

CREATE TABLE orders(
    OrderID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    EmployeeID INT NOT NULL,
    ItemID INT NOT NULL,
    Description VARCHAR(120) NOT NULL,
    FoodCategory VARCHAR(60) NOT NULL, 
    WholeSaleUnitPrice DECIMAL(8,2) NOT NULL,
    WholeSaleTotalPrice DECIMAL(10,2) NOT NULL,
    Quantity INT NOT NULL, 
    OrderDate DATETIME NOT NULL,
    Status INT DEFAULT 0 CHECK (Status = 0 OR Status = 1),
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The orders table is used for managing whole sale orders placed."
;

-- check those tables
SELECT TABLE_COMMENT,CREATE_TIME,UPDATE_TIME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='inventory_app_data';
